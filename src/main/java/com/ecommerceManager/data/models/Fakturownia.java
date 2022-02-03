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
	
	private int department_id;
    private String seller_name;
	private String seller_bank;
	private String seller_bank_account;
	private String seller_post_code;
	private String seller_city;
	private String seller_street;
	
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

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public String getSeller_name() {
		return seller_name;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public String getSeller_bank() {
		return seller_bank;
	}

	public void setSeller_bank(String seller_bank) {
		this.seller_bank = seller_bank;
	}

	public String getSeller_bank_account() {
		return seller_bank_account;
	}

	public void setSeller_bank_account(String seller_bank_account) {
		this.seller_bank_account = seller_bank_account;
	}

	public String getSeller_post_code() {
		return seller_post_code;
	}

	public void setSeller_post_code(String seller_post_code) {
		this.seller_post_code = seller_post_code;
	}

	public String getSeller_city() {
		return seller_city;
	}

	public void setSeller_city(String seller_city) {
		this.seller_city = seller_city;
	}

	public String getSeller_street() {
		return seller_street;
	}

	public void setSeller_street(String seller_street) {
		this.seller_street = seller_street;
	}
	
	
	
	

}
