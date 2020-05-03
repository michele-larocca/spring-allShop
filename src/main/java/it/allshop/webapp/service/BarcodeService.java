package it.allshop.webapp.service;

import it.allshop.webapp.entity.BarcodeEntity;

public interface BarcodeService {
	public BarcodeEntity selByBarcode(String barcode);
}
