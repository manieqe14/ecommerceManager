package com.ecommerceManager.data.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Fakturownia {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int fakturowniaId;
	
	private String token;
	
	@OneToOne
	@JoinColumn(name="shopId")
	@JsonIgnore
	private Shop shop;

	public int getFakturowniaId() {
		return fakturowniaId;
	}

	public void setFakturowniaId(int fakturowniaId) {
		this.fakturowniaId = fakturowniaId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
	

}
