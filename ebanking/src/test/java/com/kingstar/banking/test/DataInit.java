/*
 * Copyright 2016-2022 the original author.All rights reserved.
 * Kingstar(honeysoft@126.com)
 * The license,see the LICENSE file.
 */

package com.kingstar.banking.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.teasoft.bee.osql.SuidRich;
import org.teasoft.beex.poi.ExcelReader;
import org.teasoft.honey.osql.autogen.Ddl;
import org.teasoft.honey.osql.core.Logger;
import org.teasoft.honey.osql.shortcut.BF;
import org.teasoft.honey.osql.util.DateUtil;
import org.teasoft.honey.util.SuidHelper;

import com.kingstar.banking.entity.Account;
import com.kingstar.banking.init.TableInit;
import com.kingstar.exchange.entity.Exchange;

/**
 * @author Kingstar
 * @since  1.0
 */
public class DataInit {
	private static SuidRich suidRich = BF.getSuidRich();
	private static boolean inited = false;

	public static void main(String[] args) {
		init();
	}

//	@Test
	public static void init() {
		if (!inited) {
			inited = true;
			initExchangeData();
			initAccountData();
		}
	}

	public static void initExchangeData() {

		try {
			Ddl.createTable(new Exchange(), false);
		} catch (Exception e) {
			// ignore
		}

		try {

			String baseDir = System.getProperty("user.dir") + "\\src\\main\\resources\\";
			String fullPath = baseDir + "exchange_rate.xlsx";

			String[] checkTitles = { "currency_from", "currency_to", "rate" };
			List<String[]> list = ExcelReader.checkAndReadExcel(fullPath, checkTitles, 0); // 标题在第0行.
			String fieldNames = "currencyFrom,currencyTo,rate"; // 每列对应的字段名
			if (list != null) {
				List<Exchange> exchangeList = SuidHelper.parseToEntity(list, 1, list.size() - 1,
						fieldNames, new Exchange());
				setDate(exchangeList);
				int total = suidRich.insert(exchangeList);
				Logger.info("insert record number: " + total);

				testSelectExchange();
			}

		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
		}

	}

	private static void testSelectExchange() {
		List<Exchange> list = suidRich.select(new Exchange(), 1);
		if (list != null && list.size() > 0) {
			Logger.info(list.get(0).toString());
			Logger.info(list.get(0).getCurrencyFrom());
			Logger.info(list.get(0).getCurrencyTo());
			Logger.info(list.get(0).getDate());
			Logger.info(list.get(0).getRate());
		}
	}

	private static final String DATE_FORMATTER = "dd-MM-yyyy";

	private static void setDate(List<Exchange> exchangeList) {
		if (exchangeList == null) return;
		String today = DateUtil.currentDate(DATE_FORMATTER);
		for (int i = 0; i < exchangeList.size(); i++) {
			exchangeList.get(i).setDate(today);
		}

	}

	private static String clientNames[] = { "Kingstar", "Pter", "Mark", "White", "Sun",
			"client6", "client7" };
	private static String currencyHomes[] = { "CNY", "USD", "GBP", "USD", "HKD", "USD", "CAD",
			"USD", "EUR", "USD", "KRW", "USD", "AUD", "USD", "JPY", "USD", "CHF", "USD", "RUB",
			"USD", "CHF", "GBP", "HKD", "GBP", "CNY", "GBP", "EUR", "GBP", "HKD", "CNY", "EUR",
			"CNY", "AUD", "CNY", "USD", "CNY" };
	private static String currencyTypes[] = { "USD", "CNY", "USD", "GBP", "USD", "HKD", "USD",
			"CAD", "USD", "EUR", "USD", "KRW", "USD", "AUD", "USD", "JPY", "USD", "CHF", "USD",
			"RUB", "GBP", "CHF", "GBP", "HKD", "GBP", "CNY", "GBP", "EUR", "CNY", "HKD", "CNY",
			"EUR", "CNY", "AUD", "USD", "CNY" };

	private static int RECORDS_SIZE = 100000;

	private static void initAccountData() {
		try {
			TableInit.createTable();
		} catch (Exception e) {
			// ignore
		}

		List<Account> list = null;
		for (int i = 0; i < RECORDS_SIZE; i++) {
			if (i % 5000 == 0) {
				if (i != 0) suidRich.insert(list);
				list = new ArrayList<>();
			}
			Account account = new Account();
			java.util.Random random = new java.util.Random();
			int clientIndex = random.nextInt(clientNames.length);
			String name = clientNames[clientIndex];
			account.setClientName(name);
			account.setClientId(name);
			boolean flag = random.nextBoolean();
			account.setFlag(flag);
			String amount = random.nextInt(1000) + "";
			if (!flag) {
				amount = "-" + amount; // false is negative
			}
			account.setAmount(amount);
			int currencyIndex = random.nextInt(currencyTypes.length);
			account.setCurrencyHome(currencyHomes[currencyIndex]);
			account.setCurrencyType(currencyTypes[currencyIndex]);

			if (i < RECORDS_SIZE / 20) {
				// 07-2022
				account.setDate(getDay(19) + "-07-2022");
				account.setMonthYear("07-2022");
			} else {
				// just test 01-2022 to 06-2022
				int month = random.nextInt(6)+1;
				account.setDate(getDay() + "-0" + month + "-2022");
				account.setMonthYear("0" +month + "-2022");
			}

			account.setIban("CH93-0000-0000-0000-0000-" + clientIndex);
			account.setUuid(UUID.randomUUID().toString());
			if (!flag) {
				account.setDescriptioin("Online payment " + currencyTypes[currencyIndex]);
			}else {
				account.setDescriptioin("Deposit " + currencyTypes[currencyIndex]);
			}

			// just test
			if (i == 0) {
				Logger.info(account.toString());
				Logger.info(account.getClientId());
				Logger.info(account.getClientName());
				Logger.info(account.getCurrencyHome());
				Logger.info(account.getCurrencyType());
				Logger.info(account.getDate());
			}

			list.add(account);

			if (i == RECORDS_SIZE - 1) suidRich.insert(list); // last batch

		}

	}

	private static String getDay() {
		java.util.Random random = new java.util.Random();
		int d = random.nextInt(28);
		d = d + 1;
		if (d < 10)
			return "0" + d;
		else
			return d + "";
	}

	private static String getDay(int maxDay) {
		java.util.Random random = new java.util.Random();
		int d = random.nextInt(maxDay);
		d = d + 1;
		if (d < 10)
			return "0" + d;
		else
			return d + "";
	}

}
