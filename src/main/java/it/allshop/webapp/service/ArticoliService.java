package it.allshop.webapp.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import it.allshop.webapp.entity.ArticoliEntity;

public interface ArticoliService {
	
	public Iterable<ArticoliEntity> selAll();
	
	public List<ArticoliEntity> selByDescrizione(String descrizione);
		
	public List<ArticoliEntity> selByDescrizione(String descrizione, Pageable pageable);
	
	public ArticoliEntity selByCodArt(String codArt);
	
	public void delArticolo(ArticoliEntity articolo);
	
	public void insArticolo(ArticoliEntity articolo);
}
