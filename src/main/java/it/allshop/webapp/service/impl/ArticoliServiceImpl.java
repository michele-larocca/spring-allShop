package it.allshop.webapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.allshop.webapp.entity.ArticoliEntity;
import it.allshop.webapp.repository.ArticoliRepository;
import it.allshop.webapp.service.ArticoliService;

@Service
@Transactional(readOnly = true)
public class ArticoliServiceImpl implements ArticoliService {

	@Autowired
	private ArticoliRepository articoliRepository;

	@Override
	public Iterable<ArticoliEntity> selAll() {
		return articoliRepository.findAll();
	}

	@Override
	public List<ArticoliEntity> selByDescrizione(String descrizione, Pageable pageable) {
		return articoliRepository.findByDescrizioneLike(descrizione, pageable);
	}

	@Override
	public List<ArticoliEntity> selByDescrizione(String descrizione) {
		return articoliRepository.selByDescrizioneLike(descrizione);
	}

	@Override
	public ArticoliEntity selByCodArt(String codArt) {
		return articoliRepository.findByCodArt(codArt);
	}

	@Override
	@Transactional
	public void delArticolo(ArticoliEntity articolo) {
		articoliRepository.delete(articolo);
	}

	@Override
	@Transactional
	public void insArticolo(ArticoliEntity articolo) {
		articoliRepository.save(articolo);
	}
}
