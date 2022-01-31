package com.ecommerceManager.data.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="LineItems")
public class LineItem {
	
	@Id
	private long id;
	private String name;
	private int product_id;
	private int variation_id;
	private int quantity;
	private double subtotal;
	private double total;
	
	@ManyToMany(mappedBy="line_items")
	@JsonIgnore
	private List<Order> orders;
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getVariation_id() {
		return variation_id;
	}

	public void setVariation_id(int variation_id) {
		this.variation_id = variation_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "LineItem [id=" + id + ", name=" + name + ", product_id=" + product_id + ", variation_id=" + variation_id
				+ ", quantity=" + quantity + ", subtotal=" + subtotal + ", total=" + total + "]";
	}
	
	
	
	

}
