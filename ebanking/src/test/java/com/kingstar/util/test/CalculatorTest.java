/*
 * Copyright 2016-2022 the original author.All rights reserved.
 * Kingstar(honeysoft@126.com)
 * The license,see the LICENSE file.
 */

package com.kingstar.util.test;

import org.junit.Assert;
import org.junit.Test;

import com.kingstar.util.Calculator;

/**
 * @author Kingstar
 * @since  1.0
 */
public class CalculatorTest {
	
	@Test
	public void test() {
		
	   String a="0.09";
	   String b="0.09";
	   String result=Calculator.multiply(a, b);
	   Assert.assertEquals(result,"0.0081");
	   
	   System.out.println("test CalculatorTest finished!");
	}

}
