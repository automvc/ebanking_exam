/*
 * Copyright 2016-2022 the original author.All rights reserved.
 * Kingstar(honeysoft@126.com)
 * The license,see the LICENSE file.
 */

package com.kingstar.exchange.service;

import java.util.List;

import com.kingstar.exchange.entity.Exchange;

/**
 * @author Kingstar
 * @since  1.0
 */
public interface ExchangeService {

	public List<Exchange> getRateListByDate(String date);
	
	public String getCurrentRate(String currencyFrom, String currencyTo);

}
