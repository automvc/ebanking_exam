/*
 * Copyright 2016-2022 the original author.All rights reserved.
 * Kingstar(aiteasoft@163.com)
 * The license,see the LICENSE file.
 */

package com.kingstar.banking.entity;

import java.io.Serializable;

/**
 * @author Kingstar
 * @since 1.0
 * Create on 2022-07-16
 */
public class Account implements Serializable {

	private static final long serialVersionUID = 1595956170360L;
	
    private Long id;
    private String uuid;
    private String clientId;
    private String clientName;
    private String iban;
    private String amount;
    private String currencyType;
    private String date;
    private String monthYear;
    private String descriptioin;

    public Long getId() {
	    return id;
    }

    public void setId(Long id) {
	    this.id = id;
    }
    
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
    
    public String getCurrencyType() {
	    return currencyType;
    }

    public void setCurrencyType(String currencyType) {
	    this.currencyType = currencyType;
    }
    
    public String getDate() {
	    return date;
    }

    public void setDate(String date) {
	    this.date = date;
    }
    
    public String getMonthYear() {
	    return monthYear;
    }

    public void setMonthYear(String monthYear) {
	    this.monthYear = monthYear;
    }
    
    public String getDescriptioin() {
	    return descriptioin;
    }

    public void setDescriptioin(String descriptioin) {
	    this.descriptioin = descriptioin;
    }
    
	 public String toString(){	
		 StringBuilder str=new StringBuilder();	
		 str.append("Account[");
		 str.append("id=").append(id);
		 str.append(",uuid=").append(uuid);
		 str.append(",clientId=").append(clientId);
		 str.append(",clientName=").append(clientName);
		 str.append(",iban=").append(iban);
		 str.append(",amount=").append(amount);
		 str.append(",currencyType=").append(currencyType);
		 str.append(",date=").append(date);
		 str.append(",monthYear=").append(monthYear);
		 str.append(",descriptioin=").append(descriptioin);
		 str.append("]");			 
		 return str.toString();	
	 }
}