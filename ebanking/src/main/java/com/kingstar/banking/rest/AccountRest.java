/*
 * Copyright 2016-2022 the original author.All rights reserved.
 * Kingstar(aiteasoft@163.com)
 * The license,see the LICENSE file.
 */

package com.kingstar.banking.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.automvc.common.jq.PageWarp;
import com.automvc.common.jq.Result;
import com.kingstar.banking.service.AccountService;

/**
 * @author Kingstar
 * @since  1.0
 * Create on 2022-07-16
 */
@RestController("account")
@RequestMapping("account")
public class AccountRest {

	@Autowired
	private AccountService AccountService;

	/**
	 * fetch one page transaction records by client Id and an arbitrary calendar month.
	 * @param clientId client Id
	 * @param monthYear date,format:MM-yyyy
	 * @param page  page number
	 * @param rows  number of records on one page.
	 * @return
	 */
	@RequestMapping("/{clientId}/{monthYear}")
	public Result list(@PathVariable String clientId, @PathVariable String monthYear,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "rows", defaultValue = "20", required = false) int rows) {

		Result result = new Result();
		// TODO Next, will get the value from kafka.
		PageWarp pageWarp = AccountService.countAndSelect(clientId, monthYear,
				(page - 1) * rows, rows);
		result.setTotal(pageWarp.getTotal());
		result.setRows(pageWarp.getList());

		return result;
	}

}