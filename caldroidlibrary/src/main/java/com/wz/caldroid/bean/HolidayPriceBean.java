package com.wz.caldroid.bean;

import java.util.Date;

/****************************************
 * 功能说明:  节假日的价格
 *
 * Author: Created by bayin on 2017/10/23.
 ****************************************/

public class HolidayPriceBean {
    private Date holiday;
    private String holidayPrice;

    public HolidayPriceBean(Date holiday, String holidayPrice) {
        this.holiday = holiday;
        this.holidayPrice = holidayPrice;
    }

    public Date getHoliday() {
        return holiday;
    }

    public void setHoliday(Date holiday) {
        this.holiday = holiday;
    }

    public String getHolidayPrice() {
        return holidayPrice;
    }

    public void setHolidayPrice(String holidayPrice) {
        this.holidayPrice = holidayPrice;
    }

    @Override
    public String toString() {
        return "假期：" + holiday + "----[价格：" + holidayPrice+"]";
    }
}
