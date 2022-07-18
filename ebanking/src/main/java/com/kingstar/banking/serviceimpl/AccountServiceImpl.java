/*
 * Copyright 2016-2022 the original author.All rights reserved.
 * Kingstar(honeysoft@126.com)
 * The license,see the LICENSE file.
 */

package com.kingstar.banking.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teasoft.bee.osql.Condition;
import org.teasoft.bee.osql.OrderType;
import org.teasoft.bee.osql.SuidRich;
import org.teasoft.honey.osql.core.BeeFactoryHelper;
import org.teasoft.honey.util.StringUtils;

import com.automvc.common.jq.PageWarp;
import com.kingstar.banking.entity.Account;
import com.kingstar.banking.entity.AccountResponse;
import com.kingstar.banking.service.AccountService;
import com.kingstar.exchange.service.ExchangeService;
import com.kingstar.util.Calculator;

/**
 * @author Kingstar
 * @since  1.0
 * Create on 2022-07-16
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private SuidRich suidRich;

	@Autowired
	private ExchangeService exchangeService;

	// TODO Next, will get the value from kafka.
	@Override
	public PageWarp countAndSelect(String clientId, String monthYear, int start, int size) {
		Account account = new Account();
		account.setClientId(clientId);
		account.setMonthYear(monthYear);

		Condition condition = getCondition();

		int total = suidRich.count(account, condition);

		List<Account> list = null;
		if (total == 0) {
			list = new ArrayList<>();
		} else {
			condition.start(start);
			condition.size(size);
			list = suidRich.select(account, condition);
		}

		List<AccountResponse> newList = transfer(list);

		return new PageWarp(total, newList);
	}

	private Condition getCondition() {
		Condition condition = BeeFactoryHelper.getCondition();
		// Add the business logic if need.
		condition.orderBy("date", OrderType.DESC);

		return condition;
	}

	private List<AccountResponse> transfer(List<Account> list) {
		List<AccountResponse> newList = null;
		AccountResponse res;
		for (int i = 0; list != null && i < list.size(); i++) {
			if (i == 0) newList = new ArrayList<>();
			res = new AccountResponse();
			res.setClientId(list.get(i).getClientId());
			res.setClientName(list.get(i).getClientName());
			res.setDate(list.get(i).getDate());
			res.setDescriptioin(list.get(i).getDescriptioin());
			res.setIban(list.get(i).getIban());
			res.setUuid(list.get(i).getUuid());

			String currType = list.get(i).getCurrencyType();
			String amount = list.get(i).getAmount();
			String a = currType + " " + amount;

			String currHome = list.get(i).getCurrencyHome();

			if (!currType.equalsIgnoreCase(currHome)) {
				String rate = exchangeService.getCurrentRate(currType, currHome);
				if (StringUtils.isNotBlank(rate)) {
					String eqAmont = Calculator.multiply(amount, rate);
					a+="("+currHome+ " " +eqAmont+")";
				}
			}
			res.setAmount(a);
			newList.add(res);
		}

		return newList;
	}

}