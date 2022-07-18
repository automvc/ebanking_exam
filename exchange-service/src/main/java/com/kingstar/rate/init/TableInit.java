/*
 * Copyright 2016-2022 the original author.All rights reserved.
 * Kingstar(honeysoft@126.com)
 * The license,see the LICENSE file.
 */

package com.kingstar.rate.init;

import org.teasoft.bee.osql.PreparedSql;
import org.teasoft.honey.osql.autogen.Ddl;
import org.teasoft.honey.osql.core.HoneyUtil;
import org.teasoft.honey.osql.shortcut.BF;

import com.kingstar.rate.entity.Exchange;

/**
 * Table Init(just for test).
 * @author Kingstar
 * @since  1.0
 */
public class TableInit {
	public static void createTable() {
		if (HoneyUtil.isMysql()) {
			createTableForMysql();
		} else {
			Ddl.createTable(new Exchange(), false);
		}
	}

	private static void createTableForMysql() {
		
		String dropSql ="DROP TABLE IF EXISTS `exchange`;";
		
		String createSql="CREATE TABLE `exchange` ("+
						"  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',"+
						"  `currency_from` varchar(20) COLLATE utf8_bin DEFAULT NULL,"+
						"  `currency_to` varchar(20) COLLATE utf8_bin DEFAULT NULL,"+
						"  `date` varchar(30) COLLATE utf8_bin DEFAULT NULL,"+
						"  `rate` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT 'exchange rate',"+
						"  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',"+
						"  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',"+
						"  PRIMARY KEY (`id`),"+
						"  KEY `idx_exchange_date` (`date`) USING BTREE"+
						") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='exchange rate table';";
		
		PreparedSql pre=BF.getPreparedSql();
		pre.modify(dropSql);
		pre.modify(createSql);
	}
}
