package com.ecommerceManager.data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ecommerceManager.data.models.Shop;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	private String username;
	private String password;
		
	@Column(name="is_non_locked")
	private boolean isNonLocked;
	
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Shop> shops;
	
	@ManyToOne
	@JoinColumn(name="role")
	private Role role;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isNonLocked() {
		return isNonLocked;
	}
	public void setNonLocked(boolean isNonLocked) {
		this.isNonLocked = isNonLocked;
	}
	
	public List<Shop> getShops() {
		return shops;
	}
	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}
	
	
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", isNonLocked=" + isNonLocked + ", shops="
				+ shops + "]";
	}
	
	

}
