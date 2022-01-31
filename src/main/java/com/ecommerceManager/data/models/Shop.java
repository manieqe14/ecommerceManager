package com.ecommerceManager.data.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ecommerceManager.data.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="shops")
public class Shop {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long shopId;
	
	@Column(nullable = false, unique = true)
	private String siteUrl;
	private String username;
	private String password;
	
	@OneToMany(mappedBy="shop")
	private List<Order> orders;
	
	@ManyToOne
	@JoinColumn(name="userId")
	@JsonIgnore
	private User user;

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Shop [shopId=" + shopId + ", siteUrl=" + siteUrl + ", username=" + username + ", password=" + password
				+ "]";
	}
	
	
	
	
}
