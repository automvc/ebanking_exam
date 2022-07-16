package com.kingstar.banking.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.automvc.service.Service;

@RestController
@RequestMapping("account")
public class AccountConsumerRest {

	@Autowired
	Service service;

	@RequestMapping(value = "/{clientId}/{monthYear}", method = RequestMethod.GET)
	public Object list(@PathVariable String clientId, @PathVariable String monthYear,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "rows", defaultValue = "20", required = false) int rows) {

		// 在调用服务之前,可以添加逻辑
		Object obj = null;
		obj = service.list(clientId, monthYear, page, rows);
		// 在调用服务之后,也可以添加逻辑

		return obj;
	}

}