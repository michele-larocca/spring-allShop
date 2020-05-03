package it.allshop.webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.allshop.webapp.entity.BarcodeEntity;
import it.allshop.webapp.repository.BarcodeRepository;
import it.allshop.webapp.service.BarcodeService;

@Service
@Transactional(readOnly = true)
public class BarcodeServiceImpl implements BarcodeService {
	
	@Autowired
	BarcodeRepository barcodeRepository;

	@Override
	public BarcodeEntity selByBarcode(String barcode) {
		return barcodeRepository.findByBarcode(barcode);
	}
}
