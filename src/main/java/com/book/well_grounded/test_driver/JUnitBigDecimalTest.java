package com.book.well_grounded.test_driver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * 《Java程序员的修炼之道》第11章 测试驱动开发
 * 11.1.4 JUnit
 * Page：284
 * JUnit有三个主要特性：
 * 1. 用于测试预期结果和异常的断言，比如assertEquals()
 * 2. 设置和拆卸通用测试数据的能力，比如@Before和@After
 * 运行测试套件的测试运行器
 * <p>
 * 一个基本的JUnit测试包含下面这些元素：
 * 1. 用@Before标记设置方法，在每个测试运行前准备测试数据
 * 2. 用@After标记拆卸方法，在每个测试运行完成后拆卸测试数据
 * 3. 测试方法本身（用@Test注解标记）
 * <p>
 * Created by Cao Wei Dong on 2018-09-12 07:41.
 */
public class JUnitBigDecimalTest {
    private BigDecimal x;

    @Before
    public void setUp() {
        x = new BigDecimal(1.5);
    }

    @After
    public void tearDown() {
        x = null;
    }

    @Test
    public void addingTwoBigDecimals() {
        assertEquals(new BigDecimal("3.0"), x.add(x));
    }

    @Test(expected = NumberFormatException.class)
    public void numberFormatExceptionIfNotANumber() {
        x = new BigDecimal("Not a number");
    }
}
