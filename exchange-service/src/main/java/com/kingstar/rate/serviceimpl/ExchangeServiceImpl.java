/*
 * Copyright 2016-2022 the original author.All rights reserved.
 * Kingstar(honeysoft@126.com)
 * The license,see the LICENSE file.
 */

package com.kingstar.rate.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teasoft.bee.osql.Condition;
import org.teasoft.bee.osql.SuidRich;
import org.teasoft.honey.osql.core.BeeFactoryHelper;

import com.automvc.common.jq.PageWarp;
import com.kingstar.rate.entity.Exchange;
import com.kingstar.rate.service.ExchangeService;

/**
 * @author Kingstar
 * @since  1.0
 * Create on 2022-07-16
 */
@Service
public class ExchangeServiceImpl implements ExchangeService {

	@Autowired
	private SuidRich suidRich;

	@Override
	public PageWarp countAndSelect(String date) {

		Exchange entity = new Exchange();
		entity.setDate(date);

		Condition condition = getCondition(entity);

		int total = suidRich.count(entity, condition);

		List<Exchange> listPage = null;
		if (total == 0) {
			listPage = new ArrayList<>();
		} else {
//			condition.start(start);
//			condition.size(size);
			listPage = suidRich.select(entity, condition);
		}

		return new PageWarp(total, listPage);
	}

	private Condition getCondition(Exchange entity) {
		Condition condition = BeeFactoryHelper.getCondition();
		// Add the business logic if need.

		return condition;
	}

}