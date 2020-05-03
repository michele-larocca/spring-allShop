package it.allshop.webapp.controller;

import static java.lang.String.format;

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
import it.allshop.webapp.service.BarcodeService;

@RestController
@RequestMapping("/api/articoli")
public class ArticleWebService {

	private Logger logger = Logger.getLogger(ArticleWebService.class);
	
	@Autowired
	private BarcodeService barcodeService;
	
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
	
}
