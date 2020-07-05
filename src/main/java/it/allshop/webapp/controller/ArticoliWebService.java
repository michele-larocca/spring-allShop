package it.allshop.webapp.controller;

import static java.lang.String.format;

import java.util.List;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import it.allshop.webapp.entity.ArticoliEntity;
import it.allshop.webapp.entity.BarcodeEntity;
import it.allshop.webapp.exception.BindingException;
import it.allshop.webapp.exception.DuplicateException;
import it.allshop.webapp.exception.NotFoundException;
import it.allshop.webapp.service.ArticoliService;
import it.allshop.webapp.service.BarcodeService;

@RestController
@RequestMapping("/api/articoli")
@CrossOrigin(origins="http://localhost:4200")
public class ArticoliWebService {

	private Logger logger = Logger.getLogger(ArticoliWebService.class);
	
	@Autowired
	private BarcodeService barcodeService;
	
	@Autowired
	private ArticoliService articoliService;
	
	@Autowired
	private ResourceBundleMessageSource errMessage;
	
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
	
	@PostMapping(value="/inserisci")
	public ResponseEntity<ArticoliEntity> createArt(@Valid @RequestBody ArticoliEntity articolo, BindingResult bindResult) throws BindingException, DuplicateException{
		
		logger.info("Salviamo l'articolo con codice " + articolo.getCodArt());
		
		if(bindResult.hasErrors()) {
			String erroMsg = errMessage.getMessage(bindResult.getFieldError(), LocaleContextHolder.getLocale());
			logger.warn(erroMsg);
			throw new BindingException(erroMsg);
		}
		
		ArticoliEntity articoloAlreadySaved = articoliService.selByCodArt(articolo.getCodArt());
		
		if(articoloAlreadySaved != null) {
			String erroMsg = format("Articolo %s già presente in anagrafica! "
					+ "Impossibile utilizzare metodo inserisci", articolo.getCodArt());
			logger.warn(erroMsg);
			throw new DuplicateException(erroMsg);
		}
		
		articoliService.insArticolo(articolo);
		
		return new ResponseEntity<ArticoliEntity>(new HttpHeaders(), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/modifica", method = RequestMethod.PUT)
	public ResponseEntity<ArticoliEntity> modificaArt(@Valid @RequestBody ArticoliEntity articolo, BindingResult bindResult) throws BindingException, NotFoundException {
		
		logger.info("Modifica dell'articolo con codice " + articolo.getCodArt());
		
		if(bindResult.hasErrors()) {
			String erroMsg = errMessage.getMessage(bindResult.getFieldError(), LocaleContextHolder.getLocale());
			logger.warn(erroMsg);
			throw new BindingException(erroMsg);
		}
		
		ArticoliEntity articoloSaved = articoliService.selByCodArt(articolo.getCodArt());
		
		if(articoloSaved == null) {
			String erroMsg = format("Articolo %s non è presente in anagrafica! "
					+ "Impossibile utilizzare metodo modifica", articolo.getCodArt());
			logger.warn(erroMsg);
			throw new NotFoundException(erroMsg);
		}
		
		articoliService.insArticolo(articolo);
		
		return new ResponseEntity<ArticoliEntity>(new HttpHeaders(), HttpStatus.CREATED);
	}

	@RequestMapping(value="/elimina/{codArt}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminaArt(@PathVariable String codArt) throws NotFoundException {
		
		logger.info("Elimina dell'articolo con codice " + codArt);
		
		ArticoliEntity articoloSaved = articoliService.selByCodArt(codArt);
		
		if(articoloSaved == null) {
			String erroMsg = format("Articolo %s non è presente in anagrafica! "
					+ "Impossibile utilizzare metodo elimina", codArt);
			logger.warn(erroMsg);
			throw new NotFoundException(erroMsg);
		}
		
		articoliService.delArticolo(articoloSaved);
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responseNode = mapper.createObjectNode();
		responseNode.put("code",  HttpStatus.OK.toString());
		responseNode.put("message", format("Eliminazione Articolo %s Eseguita Con Successo", codArt));
		
		return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);
	}
}
