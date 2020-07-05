package it.allshop.webapp.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "IVA")
public class IvaEntity {
	@Id
	@Column(name = "IDIVA")
	private int idIva;
	
	@Column(name = "DESCRIZIONE")
	private String descrizione;
	
	@Column(name = "ALIQUOTA")
	private int aliquota;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "iva")
	@JsonBackReference
	private Set<ArticoliEntity> articoli = new HashSet<>();

	public int getIdIva() {
		return idIva;
	}

	public void setIdIva(int idIva) {
		this.idIva = idIva;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getAliquota() {
		return aliquota;
	}

	public void setAliquota(int aliquota) {
		this.aliquota = aliquota;
	}

	public Set<ArticoliEntity> getArticoli() {
		return articoli;
	}

	public void setArticoli(Set<ArticoliEntity> articoli) {
		this.articoli = articoli;
	}
}
