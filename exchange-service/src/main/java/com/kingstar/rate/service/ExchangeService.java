/*
 * Copyright 2016-2022 the original author.All rights reserved.
 * Kingstar(honeysoft@126.com)
 * The license,see the LICENSE file.
 */

package com.kingstar.rate.service;

import com.automvc.common.jq.PageWarp;

/**
 * @author Kingstar
 * @since  1.0
 * Create on 2022-07-16
 */
public interface ExchangeService {  

	public PageWarp countAndSelect(String date);

}