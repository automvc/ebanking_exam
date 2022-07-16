/*
 * Copyright 2016-2022 the original author.All rights reserved.
 * Kingstar(honeysoft@126.com)
 * The license,see the LICENSE file.
 */

package com.kingstar.banking.service;

import com.automvc.common.jq.PageWarp;
import com.automvc.common.jq.Result;

import com.kingstar.banking.entity.Account;

/**
 * @author Kingstar
 * @since  1.0
 * Create on 2022-07-16
 */
public interface AccountService {  

	public boolean isWrongBizForUpdate(Result result, Account entity);

	public Account process(Account entity);

	public PageWarp countAndSelect(Account entity, int start, int size);

}