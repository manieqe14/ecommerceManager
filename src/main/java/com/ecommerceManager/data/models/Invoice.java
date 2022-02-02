package com.ecommerceManager.data.models;

import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class Invoice {
	
	private long invoiceId;
	private String invoiceNumber;
		

	public long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	@Override
	public String toString() {
		return "Invoice [invoiceId=" + invoiceId + ", invoiceNumber=" + invoiceNumber + "]";
	}
	
	
	
	

}
