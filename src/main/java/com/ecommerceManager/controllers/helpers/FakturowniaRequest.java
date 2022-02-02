package com.ecommerceManager.controllers.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.ecommerceManager.data.models.LineItem;
import com.ecommerceManager.data.models.Order;
import com.ecommerceManager.data.models.Shop;

public class FakturowniaRequest {
	
	private Order order;
	private Shop shop;
	
	private int department_id;
    private String seller_name;
	private String seller_bank;
	private String seller_bank_account;
	private String seller_post_code;
	private String seller_city;
	private String seller_street;
	
	private String kind;
	private String number;
	private String sell_date;
	private String issue_date;
	private String payment_to;
	private String status;
	
	private String seller_tax_no;
	private String buyer_name;
	private String buyer_tax_no;
	private List<Position> positions;
	
	

	public FakturowniaRequest(Order order) {
		this.order = order;
		this.shop = order.getShop();
		generateInvoice();
	}
	
	public void generateInvoice() {
		department_id = 761451;
	    seller_name = "Good Sound Mariusz Pacyga";
		seller_bank = "mBank";
		seller_bank_account = "24 1140 1977 0000 5921 7200 1001";
		seller_post_code = "34-221";
		seller_city = "Skawica";
		seller_street = "Skawica 592";
		kind = "vat";
		number = null;
		sell_date = formatData(order.getDate_created());
		issue_date = formatData(LocalDateTime.now().toString());
		payment_to = null;
		seller_name = shop.getSiteUrl();
		seller_tax_no = "522732168";
		buyer_name = order.getBilling().getFirst_name() + " " + order.getBilling().getLast_name();
		buyer_tax_no = "0";
		positions = new ArrayList<>();
		for(LineItem item : order.getLine_items()) {
			Position position = new Position(item.getName(), "disabled", item.getTotal(), item.getQuantity());
			positions.add(position);
		}
		
	}

	public class Position{
		private String name;
		private String tax;
		private double total_price_gross;
		private int quantity;
		
		public Position(String name, String tax, double total_price_gross, int quantity) {
			this.name = name;
			this.tax = tax;
			this.total_price_gross = total_price_gross;
			this.quantity = quantity;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTax() {
			return tax;
		}

		public void setTax(String tax) {
			this.tax = tax;
		}

		public double getTotal_price_gross() {
			return total_price_gross;
		}

		public void setTotal_price_gross(double total_price_gross) {
			this.total_price_gross = total_price_gross;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}	
		
		
	}
	
	
	
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSell_date() {
		return sell_date;
	}

	public void setSell_date(String sell_date) {
		this.sell_date = sell_date;
	}

	public String getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(String issue_date) {
		this.issue_date = issue_date;
	}

	public String getPayment_to() {
		return payment_to;
	}

	public void setPayment_to(String payment_to) {
		this.payment_to = payment_to;
	}

	public String getSeller_name() {
		return seller_name;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public String getSeller_tax_no() {
		return seller_tax_no;
	}

	public void setSeller_tax_no(String seller_tax_no) {
		this.seller_tax_no = seller_tax_no;
	}

	public String getBuyer_name() {
		return buyer_name;
	}

	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}

	public String getBuyer_tax_no() {
		return buyer_tax_no;
	}

	public void setBuyer_tax_no(String buyer_tax_no) {
		this.buyer_tax_no = buyer_tax_no;
	}
	
	
	
	
	public List<Position> getPositions() {
		return positions;
	}
	

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	protected String formatData(String data) {
		return LocalDateTime.parse(data, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
	}

}
