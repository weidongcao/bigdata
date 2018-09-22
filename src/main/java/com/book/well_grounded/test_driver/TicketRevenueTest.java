package com.book.well_grounded.test_driver;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * 《Java程序员的修炼之道》第11章 测试驱动开发
 * 11.1.2 多个测试用例
 * Page:281
 * 很多开发人员都知道JUnit，也会在开发时不定期用到家。但他们一般是写完实现代码之后
 * 才编写测试代码，因此体会不到TDD的益处。
 * 尽管TDD的概念看起来非常普及，但实际上很多开发人员并不清楚为什么要采用TDD。对于
 * 很多开发人员来说，“为什么要写测试驱动代码以及有什么好处”一直是个问题。
 *
 * 我们认为消除恐惧和不确定编写测试驱动代码的重要原因。
 * 恐惧会让你小心试探
 * 恐惧会让你尽量减少沟通
 * 恐惧会让你羞于得到反馈
 * 恐惧会让你脾气暴躁
 * TDD可以祛除恐惧，让优秀的Java开发者变得更加自信、关于沟通、乐于接受并更加快乐。
 * 换句话说，TDD能摆脱下面这种心态：
 * 1. 在开始新工作时，“我不知道从哪里开始，所以只好效应着做一些修改”
 * 2. 在修改已有代码时，“我不知道现有代码怎么运行，所以我私下认为不能碰它们”
 *
 * TDD带来的好得多好处并不会马上显现：
 * 1. 更清晰的代码——只写需要的代码
 * 2. 更好的设计——有些开发人员管TDD叫测试驱动的设计
 * 3. 更出色的灵活性——TDD鼓励按接口编码
 * 4. 更快速的反馈——不会直到系统上线才知道Bug的存在
 *
 * TDD背后的基本思想：红——绿——重构循环
 * 红：失败测试
 * 绿：通过测试
 * 重构循环：重构测试
 *
 * 技术债务：
 * 指我们现在临时凑合出来的设计或代码将来会让我们付出更多的成本
 * 有了通过测试，可以放心大胆地重构。应该实现的业务逻辑不可能会被忽视
 *
 * 编写最初的通过测试代码的另一个好处是开发进度可以更快。团队中的其他人可以马上用
 * 第一版代码跟更大的代码库一直测试（集成测试及更大范围的测试）
 *
 * Created by Cao Wei Dong on 2018-09-12 05:57.
 */
public class TicketRevenueTest {
    private TicketRevenue venueRevenue;
    private BigDecimal expectedRevenue;

    @Before
    public void setUp() {
        venueRevenue = new TicketRevenue();
    }

    @Test(expected=IllegalArgumentException.class)
    public void failIfLessThanZeroTicketsAreSold() {
        assertEquals(BigDecimal.ZERO, venueRevenue.estimateTotalRevenue(0));
    }

    @Test
    public void oneTicketSoldIsThirtyInRevenue() {
        expectedRevenue = new BigDecimal("30");
        assertEquals(expectedRevenue, venueRevenue.estimateTotalRevenue(1));
    }

    @Test
    public void tenTicketsSoldIsThreeHundredInRevenue() {
        expectedRevenue = new BigDecimal("300");
        assertEquals(expectedRevenue, venueRevenue.estimateTotalRevenue(10));
    }

    @Test
    public void failIfMoreThanOneHundredTicketsAreSold() {
        venueRevenue.estimateTotalRevenue(101);
    }

}
