/*
 * Copyright 2016-2022 the original author.All rights reserved.
 * Kingstar(aiteasoft@163.com)
 * The license,see the LICENSE file.
 */

package com.kingstar.rate.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.automvc.common.jq.PageWarp;
import com.automvc.common.jq.Result;
import com.kingstar.rate.service.ExchangeService;

/**
 * @author Kingstar
 * @since  1.0
 * Create on 2022-07-16
 */
@RestController("rate")
@RequestMapping("rate")
public class ExchangeRest {

	@Autowired
	private ExchangeService ExchangeService;

	@RequestMapping("/{date}")
	public Result list(@PathVariable String date) {

		Result result = new Result();

		PageWarp pageWarp = ExchangeService.countAndSelect(date);
		result.setTotal(pageWarp.getTotal());
		result.setRows(pageWarp.getList());

		return result;
	}
}