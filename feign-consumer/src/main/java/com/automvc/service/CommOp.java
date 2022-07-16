/*
 * Copyright 2016-2022 the original author.All rights reserved.
 * Kingstar(honeysoft@126.com)
 * The license,see the LICENSE file.
 */

package com.automvc.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Kingstar
 * @since 1.0
 */
public interface CommOp {

	@RequestMapping("/{clientId}/{monthYear}")
	public Object list(@PathVariable String clientId, @PathVariable String monthYear,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "rows", defaultValue = "20", required = false) int rows);
}
