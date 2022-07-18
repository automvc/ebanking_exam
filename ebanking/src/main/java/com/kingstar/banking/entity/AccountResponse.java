/*
 * Copyright 2016-2022 the original author.All rights reserved.
 * Kingstar(honeysoft@126.com)
 * The license,see the LICENSE file.
 */

package com.kingstar.banking.entity;

import java.io.Serializable;

/**
 * @author Kingstar
 * @since  1.0
 */
public class AccountResponse implements Serializable {
	
		private static final long serialVersionUID = 1599054859636L;
		
	    private String uuid;
	    private String clientId;
	    private String clientName;
	    private String iban;
	    private String amount;
	    private String date;
	    private String descriptioin;
	    
		public String getUuid() {
			return uuid;
		}
		public void setUuid(String uuid) {
			this.uuid = uuid;
		}
		public String getClientId() {
			return clientId;
		}
		public void setClientId(String clientId) {
			this.clientId = clientId;
		}
		public String getClientName() {
			return clientName;
		}
		public void setClientName(String clientName) {
			this.clientName = clientName;
		}
		public String getIban() {
			return iban;
		}
		public void setIban(String iban) {
			this.iban = iban;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getDescriptioin() {
			return descriptioin;
		}
		public void setDescriptioin(String descriptioin) {
			this.descriptioin = descriptioin;
		}
	    
}
