/*
 * Copyright 2016-2022 the original author.All rights reserved.
 * Kingstar(honeysoft@126.com)
 * The license,see the LICENSE file.
 */

package com.kingstar.exchange.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.teasoft.honey.osql.core.Logger;
import org.teasoft.honey.osql.util.DateUtil;
import org.teasoft.honey.util.ObjectUtils;
import org.teasoft.honey.util.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingstar.exchange.entity.Exchange;
import com.kingstar.exchange.service.ExchangeService;
import com.kingstar.util.HttpclientUtil;

/**
 * @author Kingstar
 * @since  1.0
 */
@Service
public class ExchangeServiceImpl implements ExchangeService {

	private static final String DATE_FORMATTER = "dd-MM-yyyy";

	@Value("${baking.exchangerate.url}")
	private String rateUrl;

	private static Map<String, List<Exchange>> cache = new HashMap<>();

	private static Map<String, String> rateMap = new HashMap<>();

	public List<Exchange> getRateListByDate(String date) {

		List<Exchange> list = null;

		list = cache.get(date);
		if (list != null) return list;

		String json = HttpclientUtil.getJson(rateUrl + "/" + date);

		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			JsonNode jsonNode = node.get("rows");
			String recordRows = "";
			if (jsonNode.isTextual()) {
				recordRows = jsonNode.asText();
			} else {
				recordRows = jsonNode.toString();
			}

			Exchange beans[] = null;
			if (StringUtils.isNotBlank(recordRows)) {
				beans = mapper.readValue(recordRows, Exchange[].class);
			}
			list = toList(beans);
			if (ObjectUtils.isNotEmpty(list)) cache.put(date, list);
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
		}

		return list;
	}

	private List<Exchange> toList(Exchange beans[]) {
		if (beans == null) return Collections.emptyList();
		List<Exchange> list = new ArrayList<>();
		for (int i = 0; i < beans.length; i++) {
			list.add(beans[i]);
		}
		return list;
	}

	private static final String SEP = ":"; // separator

	private void initRate() {
		String today = DateUtil.currentDate(DATE_FORMATTER);
		List<Exchange> list = getRateListByDate(today);
		Exchange bean;
		for (int i = 0; list != null && i < list.size(); i++) {
			bean = list.get(i);
			if (today.equals(bean.getDate())) { // double check
				rateMap.put(today + SEP + bean.getCurrencyFrom() + SEP + bean.getCurrencyTo(),
						bean.getRate());
			}
		}

	}

	public String getCurrentRate(String currencyFrom, String currencyTo) {
		String today = DateUtil.currentDate(DATE_FORMATTER);
		String key = today + SEP + currencyFrom + SEP + currencyTo;
		String rate = rateMap.get(key);
		if (rate != null) {
			return rate;
		} else {
			initRate();
			rate = rateMap.get(key);
			return rate;
		}
	}
}
