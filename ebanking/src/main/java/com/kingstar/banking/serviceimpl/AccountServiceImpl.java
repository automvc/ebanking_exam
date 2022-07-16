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
import org.teasoft.bee.osql.SuidRich;
import org.teasoft.honey.osql.core.BeeFactoryHelper;

import com.automvc.common.jq.PageWarp;
import com.automvc.common.jq.Result;

import com.kingstar.banking.entity.Account;
import com.kingstar.banking.service.AccountService;

/**
 * @author Kingstar
 * @since  1.0
 * Create on 2022-07-16
 */
@Service
public class AccountServiceImpl  implements AccountService{  

	@Autowired
	private SuidRich suidRich;

	@Override
	public Account process(Account entity) {
		//Add the business logic if need. 放一些公共设置

		return entity;
	}

	@Override
	public boolean isWrongBizForUpdate(Result result, Account entity) {
		//Add the business logic if need.

		return false;
	}

	@Override
	public PageWarp countAndSelect(Account entity, int start, int size) {
		Condition condition = getCondition(entity);

		int total = suidRich.count(entity, condition);

		List<Account> listPage = null;
		if (total == 0) {
			listPage = new ArrayList<>();
		} else {
			condition.start(start);
			condition.size(size);
			listPage = suidRich.select(entity, condition);
		}

		return new PageWarp(total, listPage);
	}

	private Condition getCondition(Account entity) {
		Condition condition = BeeFactoryHelper.getCondition();
		//Add the business logic if need.

		return condition;
	}

}