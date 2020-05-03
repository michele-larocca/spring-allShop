package it.allshop.webapp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ARTICOLI")
public class ArticoliEntity implements Serializable {

	private static final long serialVersionUID = 8173042270637182962L;

	@Id
	@Column(name = "CODART")
	private String codArt;
	
	@Column(name = "DESCRIZIONE")
	private String descrizione;	
	
	@Column(name = "UM")
	private String um;
	
	@Column(name = "CODSTAT")
	private String codStat;
	
	@Column(name = "PZCART")
	private Integer pzCart;
	
	@Column(name = "PESONETTO")
	private double pesoNetto;
	
	@Column(name = "IDSTATOART")
	private String idStatoArt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATACREAZIONE")
	private Date dataCreaz;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "articolo", orphanRemoval = true)
	@JsonManagedReference
	private Set<BarcodeEntity> barcode = new HashSet<>();
	
	@OneToOne(mappedBy = "articolo", cascade = CascadeType.ALL, orphanRemoval = true)
	private IngredientiEntity ingredienti;
	
	@ManyToOne
	@JoinColumn(name = "IDIVA", referencedColumnName = "idIva")
	private IvaEntity iva;
	
	@ManyToOne
	@JoinColumn(name = "IDFAMASS", referencedColumnName = "ID")
	private FamAssortEntity famAssort;

	public String getCodArt() {
		return codArt;
	}

	public void setCodArt(String codArt) {
		this.codArt = codArt;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getUm() {
		return um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public String getCodStat() {
		return codStat;
	}

	public void setCodStat(String codStat) {
		this.codStat = codStat;
	}

	public Integer getPzCart() {
		return pzCart;
	}

	public void setPzCart(Integer pzCart) {
		this.pzCart = pzCart;
	}

	public double getPesoNetto() {
		return pesoNetto;
	}

	public void setPesoNetto(double pesoNetto) {
		this.pesoNetto = pesoNetto;
	}

	public String getIdStatoArt() {
		return idStatoArt;
	}

	public void setIdStatoArt(String idStatoArt) {
		this.idStatoArt = idStatoArt;
	}

	public Date getDataCreaz() {
		return dataCreaz;
	}

	public void setDataCreaz(Date dataCreaz) {
		this.dataCreaz = dataCreaz;
	}

	public Set<BarcodeEntity> getBarcode() {
		return barcode;
	}

	public void setBarcode(Set<BarcodeEntity> barcode) {
		this.barcode = barcode;
	}

	public IngredientiEntity getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(IngredientiEntity ingredienti) {
		this.ingredienti = ingredienti;
	}

	public IvaEntity getIva() {
		return iva;
	}

	public void setIva(IvaEntity iva) {
		this.iva = iva;
	}

	public FamAssortEntity getFamAssort() {
		return famAssort;
	}

	public void setFamAssort(FamAssortEntity famAssort) {
		this.famAssort = famAssort;
	}
}
