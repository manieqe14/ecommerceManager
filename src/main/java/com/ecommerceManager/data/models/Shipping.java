package com.ecommerceManager.data.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Shipping {
	
	@Id
	@GeneratedValue
	private long shipping_id;
	
	private String first_name;
	private String last_name;
	private String company;
	private String address_1;
	private String address_2;
	private String city;
	private String state;
	private String postcode;
	private String phone;
	
	@OneToOne(mappedBy="shipping")
	@JsonIgnore
	private Order order;
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAddress_1() {
		return address_1;
	}
	public void setAddress_1(String address_1) {
		this.address_1 = address_1;
	}
	public String getAddress_2() {
		return address_2;
	}
	public void setAddress_2(String address_2) {
		this.address_2 = address_2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Shipping [shipping_id=" + shipping_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", company=" + company + ", address_1=" + address_1 + ", address_2=" + address_2 + ", city=" + city
				+ ", state=" + state + ", postcode=" + postcode + ", phone=" + phone + "]";
	}
	
	

}
