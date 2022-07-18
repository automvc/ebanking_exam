/*
 * Copyright 2016-2022 the original author.All rights reserved.
 * Kingstar(honeysoft@126.com)
 * The license,see the LICENSE file.
 */

package com.kingstar.util;

import java.math.BigDecimal;

/**
 * @author Kingstar
 * @since  1.17
 */
public class Calculator {

	/**
	 * a*b
	 * @param a
	 * @param b
	 * @return the result of a*b
	 */
	public static String multiply(String a, String b) {
		BigDecimal one = new BigDecimal(a);
		BigDecimal two = new BigDecimal(b);

		BigDecimal r = one.multiply(two);

		return r.toPlainString();
	}
}
