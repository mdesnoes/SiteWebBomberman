package com.projetJEE.beans;

import java.io.Serializable;

public class Objet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String nom;
	private String type;
	private String prix;
	private String description;
	private String image;
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrix() {
		return prix;
	}
	
	public void setPrix(String prix) {
		this.prix = prix;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "Objet [nom=" + nom + ", type=" + type + ", prix=" + prix + ", description="
	+ description + ", image=" + image + "]";
	}

}
