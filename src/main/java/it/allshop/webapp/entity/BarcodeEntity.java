package it.allshop.webapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.EqualsAndHashCode;

@Entity
@Table(name = "BARCODE")
public class BarcodeEntity implements Serializable
{ 
	private static final long serialVersionUID = 1853763261962860635L;
	
	@Id
	@Column(name = "BARCODE")
	@Size(min = 8, max = 13, message = "{Size.Barcode.barcode.Validation}")
	@NotNull(message = "{NotNull.Barcode.barcode.Validation}")
	private String barcode;
	
	@Column(name = "IDTIPOART")
	@NotNull(message = "{NotNull.Barcode.idTipoArt.Validation}")
	private String idTipoArt;
	
	@ManyToOne
	@EqualsAndHashCode.Exclude
	@JoinColumn(name = "CODART", referencedColumnName = "codArt")
	@JsonBackReference
	private ArticoliEntity articolo;

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getIdTipoArt() {
		return idTipoArt;
	}

	public void setIdTipoArt(String idTipoArt) {
		this.idTipoArt = idTipoArt;
	}

	public ArticoliEntity getArticolo() {
		return articolo;
	}

	public void setArticolo(ArticoliEntity articolo) {
		this.articolo = articolo;
	}
}
