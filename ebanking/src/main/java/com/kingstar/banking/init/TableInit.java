/*
 * Copyright 2016-2022 the original author.All rights reserved.
 * Kingstar(honeysoft@126.com)
 * The license,see the LICENSE file.
 */

package com.kingstar.banking.init;

import org.teasoft.bee.osql.PreparedSql;
import org.teasoft.honey.osql.autogen.Ddl;
import org.teasoft.honey.osql.core.HoneyUtil;
import org.teasoft.honey.osql.shortcut.BF;

import com.kingstar.banking.entity.Account;

/**
 * @author Kingstar
 * @since  1.0
 */
public class TableInit {
	
	private TableInit() {}

	public static void createTable() {
		if (HoneyUtil.isMysql()) {
			createTableForMysql();
		} else {
			Ddl.createTable(new Account(), false);
		}
	}

	private static void createTableForMysql() {
		String createSql = "CREATE TABLE `account` ("
				+ "  `id` bigint(20) NOT NULL AUTO_INCREMENT,"
				+ "  `uuid` varchar(64) COLLATE utf8_bin DEFAULT NULL,"
				+ "  `client_id` varchar(200) COLLATE utf8_bin DEFAULT NULL,"
				+ "  `client_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'client name',"
				+ "  `iban` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'IBAN NO.',"
				+ "  `amount` varchar(30) COLLATE utf8_bin DEFAULT NULL,"
				+ "  `currency_home` varchar(20) COLLATE utf8_bin DEFAULT NULL,"
				+ "  `currency_type` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'currency type of transaction',"
				+ "  `date` varchar(30) COLLATE utf8_bin DEFAULT NULL,"
				+ "  `month_year` varchar(8) COLLATE utf8_bin DEFAULT NULL,"
				+ "  `flag` bit(1) DEFAULT NULL,"
				+ "  `descriptioin` varchar(255) COLLATE utf8_bin DEFAULT NULL,"
				+ "  PRIMARY KEY (`id`),"
				+ "  KEY `idx_account_client_month` (`client_id`,`month_year`) USING BTREE"
				+ ") ENGINE=InnoDB AUTO_INCREMENT=10003 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='账户表';";
		
		PreparedSql pre=BF.getPreparedSql();
		
		try {
			pre.modify(createSql);
		} catch (Exception e) {
		   //ignore
		}
		
	}

}
