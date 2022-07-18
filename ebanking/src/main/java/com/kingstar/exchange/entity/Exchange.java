/*
 * Copyright 2016-2022 the original author.All rights reserved.
 * Kingstar(aiteasoft@163.com)
 * The license,see the LICENSE file.
 */

package com.kingstar.exchange.entity;

import java.io.Serializable;

/**
 * @author Kingstar
 * @since  1.0
 * Create on 2022-07-16
 */
public class Exchange implements Serializable {

	private static final long serialVersionUID = 1599858458822L;
	
    private Long id;
    private String currencyFrom;
    private String currencyTo;
    private String date;
    private String rate;
    private String createtime;
    private String updatetime;

    public Long getId() {
	    return id;
    }

    public void setId(Long id) {
	    this.id = id;
    }
    
    public String getCurrencyFrom() {
	    return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
	    this.currencyFrom = currencyFrom;
    }
    
    public String getCurrencyTo() {
	    return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
	    this.currencyTo = currencyTo;
    }
    
    public String getDate() {
	    return date;
    }

    public void setDate(String date) {
	    this.date = date;
    }
    
    public String getRate() {
	    return rate;
    }

    public void setRate(String rate) {
	    this.rate = rate;
    }
    
    public String getCreatetime() {
	    return createtime;
    }

    public void setCreatetime(String createtime) {
	    this.createtime = createtime;
    }
    
    public String getUpdatetime() {
	    return updatetime;
    }

    public void setUpdatetime(String updatetime) {
	    this.updatetime = updatetime;
    }
    
	 public String toString(){	
		 StringBuilder str=new StringBuilder();	
		 str.append("Exchange[");
		 str.append("id=").append(id);
		 str.append(",currencyFrom=").append(currencyFrom);
		 str.append(",currencyTo=").append(currencyTo);
		 str.append(",date=").append(date);
		 str.append(",rate=").append(rate);
		 str.append(",createtime=").append(createtime);
		 str.append(",updatetime=").append(updatetime);
		 str.append("]");			 
		 return str.toString();	
	 }
}