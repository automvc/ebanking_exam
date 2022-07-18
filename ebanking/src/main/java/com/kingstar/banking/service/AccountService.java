/*
 * Copyright 2016-2022 the original author.All rights reserved.
 * Kingstar(honeysoft@126.com)
 * The license,see the LICENSE file.
 */

package com.kingstar.banking.service;

import com.automvc.common.jq.PageWarp;

/**
 * @author Kingstar
 * @since  1.0
 * Create on 2022-07-16
 */
public interface AccountService {

	/**
	 * fetch one page transaction records by client Id and an arbitrary calendar month.
	 * @param clientId client Id
	 * @param monthYear date,format:MM-yyyy
	 * @param start start index.
	 * @param size number of records on one page.
	 * @return
	 */
	public PageWarp countAndSelect(String clientId, String monthYear, int start, int size);

}