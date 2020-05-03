package it.allshop.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.allshop.webapp.entity.BarcodeEntity;

public interface BarcodeRepository extends JpaRepository<BarcodeEntity, String>{

	public BarcodeEntity findByBarcode(String barcode);

}
