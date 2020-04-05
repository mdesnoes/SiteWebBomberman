package com.projetJEE.beans;

import java.io.Serializable;

public class Objet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String objet;
	private String prix;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getObjet() {
		return objet;
	}
	
	public void setObjet(String objet) {
		this.objet = objet;
	}
	
	public String getPrix() {
		return prix;
	}
	
	public void setPrix(String prix) {
		this.prix = prix;
	}
	
	@Override
	public String toString() {
		return "Objet [objet=" + objet + ", prix=" + prix + "]";
	}

}
