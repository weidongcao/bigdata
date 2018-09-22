package com.book.well_grounded.test_driver;

import java.math.BigDecimal;

/**
 * Created by Cao Wei Dong on 2018-09-12 06:39.
 */
public class TicketRevenue {
    public BigDecimal estimateTotalRevenue(int numberOfTicketsSold) throws IllegalArgumentException {
        BigDecimal totalRevenue = null;
        if (numberOfTicketsSold < 0) {
            throw new IllegalArgumentException("Must be > -1");
        }else if (numberOfTicketsSold == 0) {
            totalRevenue = BigDecimal.ZERO;
        }else if (numberOfTicketsSold == 1) {
            totalRevenue = new BigDecimal("30");
        }else if (numberOfTicketsSold == 101) {
            throw new IllegalArgumentException("Must by < 101");
        } else {
            totalRevenue = new BigDecimal(30 * numberOfTicketsSold);
        }
        return totalRevenue;
    }
}
