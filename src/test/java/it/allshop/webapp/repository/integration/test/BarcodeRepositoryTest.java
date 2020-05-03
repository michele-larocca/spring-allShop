package it.allshop.webapp.repository.integration.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.allshop.webapp.Application;
import it.allshop.webapp.entity.BarcodeEntity;
import it.allshop.webapp.repository.BarcodeRepository;

@SpringBootTest(classes = Application.class)
public class BarcodeRepositoryTest {

	@Autowired
	private BarcodeRepository barcodeRepo;
	
	@Test
	public void findeByBarcode() {
		assertThat(barcodeRepo.findByBarcode("8008490000021"))
		.extracting(BarcodeEntity::getBarcode)
		.isEqualTo("8008490000021");
	}
}
