package it.allshop.webapp.controller;

import static java.lang.String.format;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.allshop.webapp.entity.ArticoliEntity;
import it.allshop.webapp.entity.BarcodeEntity;
import it.allshop.webapp.exception.NotFoundException;
import it.allshop.webapp.service.ArticoliService;
import it.allshop.webapp.service.BarcodeService;

@RestController
@RequestMapping("/api/articoli")
public class ArticoliWebService {

	private Logger logger = Logger.getLogger(ArticoliWebService.class);
	
	@Autowired
	private BarcodeService barcodeService;
	
	@Autowired
	private ArticoliService articoliService;
	
	@GetMapping("/test")
	public String getGreating() {
		return "Hello, this is a web service test!";
	}
	
	@GetMapping(value="/find/byBarcode/{barcode}", produces = "application/json")
	public ResponseEntity<ArticoliEntity> getArticleByBarcode(@PathVariable("barcode") String barcode) throws NotFoundException {
		BarcodeEntity barcodeEntity = barcodeService.selByBarcode(barcode);
		
		if(barcodeEntity == null)
			throw new NotFoundException(format("Il barcode %s non è stato trovato!", barcode));
		
		return new ResponseEntity<ArticoliEntity>(barcodeEntity.getArticolo(), HttpStatus.OK);
	}
	
	@GetMapping(value="/find/byCodArt/{codArt}", produces = "application/json")
	public ResponseEntity<ArticoliEntity> getArticleByCodArt(@PathVariable("codArt") String codArt) throws NotFoundException {
		ArticoliEntity articolo = articoliService.selByCodArt(codArt);
		
		if(articolo == null)
			throw new NotFoundException(format("L'articolo con codice %s non è stato trovato", codArt));
		
		return new ResponseEntity<ArticoliEntity>(articolo, HttpStatus.OK);
	}
	
	@GetMapping(value="/find/byDescription/{filter}", produces = "application/json")
	public ResponseEntity<List<ArticoliEntity>> getArticlesByDescription(@PathVariable("filter") String filter) throws NotFoundException {
		List<ArticoliEntity> articoli = articoliService.selByDescrizione("%" + filter + "%");
		
		if(articoli == null || articoli.isEmpty())
			throw new NotFoundException(format("Non è stato trovato nessun articolo con descrizione %s", filter));
		
		return new ResponseEntity<List<ArticoliEntity>>(articoli, HttpStatus.OK);
	}
	
}
