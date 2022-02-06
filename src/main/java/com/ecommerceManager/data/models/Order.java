package com.ecommerceManager.data.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderId;
	
	private long wp_id;
	
	private int parent_id;
	private String status;
	private String date_created;
	private String date_modified;
	private double discount_total;
	private double total;
	private String currency;
	
	@OneToOne
	@JoinColumn(name="billing_id")
	private Billing billing;
	
	@OneToOne
	@JoinColumn(name="shipping_id")
	private Shipping shipping;
	
	private String payment_method;
	private String payment_method_title;
	private String customer_note;
		
	@ManyToOne
	@JsonIgnore
	private Shop shop;
	
	@ManyToMany
	private Set<LineItem> line_items;
	
	@OneToMany(mappedBy="order")
	private Set<MetaData> meta_data;
	
	@Embedded
	private Invoice invoice;	
	
	public long getOrderId() {
		return orderId;
	}


	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	

	public Invoice getInvoice() {
		return invoice == null ? new Invoice() : invoice;
	}


	public void setInvoice(Invoice invoice) {
		
		this.invoice = (invoice == null) ? new Invoice() : invoice ;
	}



	public long getWp_id() {
		return wp_id;
	}


	public void setWp_id(long wp_id) {
		this.wp_id = wp_id;
	}


	public int getParent_id() {
		return parent_id;
	}


	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDate_created() {
		return date_created;
	}


	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}


	public String getDate_modified() {
		return date_modified;
	}


	public void setDate_modified(String date_modified) {
		this.date_modified = date_modified;
	}


	public double getDiscount_total() {
		return discount_total;
	}


	public void setDiscount_total(double discount_total) {
		this.discount_total = discount_total;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public Billing getBilling() {
		return billing;
	}


	public void setBilling(Billing billing) {
		this.billing = billing;
	}


	public Shipping getShipping() {
		return shipping;
	}


	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}


	public String getPayment_method() {
		return payment_method;
	}


	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}


	public String getPayment_method_title() {
		return payment_method_title;
	}


	public void setPayment_method_title(String payment_method_title) {
		this.payment_method_title = payment_method_title;
	}


	public String getCustomer_note() {
		return customer_note;
	}


	public void setCustomer_note(String customer_note) {
		this.customer_note = customer_note;
	}


	public Shop getShop() {
		return shop;
	}


	public void setShop(Shop shop) {
		this.shop = shop;
	}


	public Set<LineItem> getLine_items() {
		return line_items;
	}


	public void setLine_items(Set<LineItem> line_items) {
		this.line_items = line_items;
	}
	
	
	
	public Set<MetaData> getMeta_data() {
		return meta_data;
	}


	public void setMeta_data(Set<MetaData> meta_data) {
		this.meta_data = meta_data;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	@Override
	public String toString() {
		return "Order [wp_id=" + wp_id + ", parent_id=" + parent_id + ", status=" + status + ", date_created=" + date_created
				+ ", date_modified=" + date_modified + ", discount_total=" + discount_total + ", total=" + total
				+ ", currency=" + currency + ", billing=" + billing + ", shipping=" + shipping + ", payment_method="
				+ payment_method + ", payment_method_title=" + payment_method_title + ", customer_note=" + customer_note
				+ ", shop=" + shop + ", line_items=" + line_items.toString() + ", metaData=" + meta_data.toString() + "]";
	}
	
	

}
