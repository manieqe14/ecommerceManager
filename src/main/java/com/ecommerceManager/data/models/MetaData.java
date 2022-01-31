package com.ecommerceManager.data.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class MetaData {
	
	@Id
	@Column(name="metaId", nullable=false)
	private long metaId;
	
	@Column(name="meta_value")
	private String value;
	
	@Column(name="meta_key")
	private String key;
		
	@ManyToOne
	@JoinColumn(name="orderId")
	@JsonIgnore
	private Order order;
	
	
	@JsonProperty("id")
	public long getMetaId() {
		return metaId;
	}
	
	@JsonProperty("id")
	public void setMetaId(long metaId) {
		this.metaId = metaId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "MetaData [id=" + metaId + ", value=" + value + ", key=" + key + "]";
	}
	
	
	

}
