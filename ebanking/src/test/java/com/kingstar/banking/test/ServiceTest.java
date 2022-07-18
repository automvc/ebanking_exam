/*
 * Copyright 2016-2022 the original author.All rights reserved.
 * Kingstar(honeysoft@126.com)
 * The license,see the LICENSE file.
 */

package com.kingstar.banking.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.teasoft.honey.osql.core.Logger;

import com.kingstar.ServiceApplication;
import com.kingstar.banking.service.AccountService;

/**
 * @author Kingstar
 * @since  1.0
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { ServiceApplication.class })
public class ServiceTest {
	@Autowired
	private AccountService accountService;

	@Test
	public void test() {
		boolean result = false;
		try {
			String clientId = "kingstar";
			String monthYear = "07-2022";
			this.accountService.countAndSelect(clientId, monthYear, 0, 10);
			
			result = true;
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			result = false;
		} finally {
			Assert.assertEquals(result, true);
		}

		System.out.println("test ServiceTest finished!");
	}
}