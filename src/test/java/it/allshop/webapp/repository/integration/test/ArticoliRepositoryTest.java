package it.allshop.webapp.repository.integration.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import it.allshop.webapp.Application;
import it.allshop.webapp.entity.ArticoliEntity;
import it.allshop.webapp.repository.ArticoliRepository;

@SpringBootTest(classes = Application.class)
public class ArticoliRepositoryTest {

	@Autowired
	private ArticoliRepository articoliRepo;
	
	@Test
	public void selByDescrizioneLikeTest() {
		List<ArticoliEntity> item = articoliRepo.selByDescrizioneLike("ACQUA ULIVETO%");
		assertEquals(2, item.size());
	}
	
	@Test
	public void findByDescrizioneLikeTest() {
		List<ArticoliEntity> item = articoliRepo.findByDescrizioneLike("ACQUA%", PageRequest.of(0, 10));
		assertEquals(10, item.size());
	}
	
	@Test
	public void findByCodArtTest() {
		ArticoliEntity articolo = articoliRepo.findByCodArt("002000301");
		assertThat(articolo).isNotNull();
		assertThat(articoliRepo.findByCodArt("002000301"))
				.extracting(ArticoliEntity::getDescrizione).isEqualTo("ACQUA ULIVETO 15 LT");
	}
	
}
