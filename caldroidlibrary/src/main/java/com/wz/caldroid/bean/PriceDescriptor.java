package com.wz.caldroid.bean;

import java.util.List;

/****************************************
 * 功能说明:  价格实体
 *
 * Author: Created by bayin on 2017/10/20.
 ****************************************/

public class PriceDescriptor {

    /**
     * workdayPrice : 200
     * weekendPrice : 300
     * legalHolidayPrice : 400
     * hotel_time : [{"live_time":"2017-10-20","end_time":"2017-10-21"},{"live_time":"2017-10-22","end_time":"2017-10-27"}]
     */

    private String workdayPrice;
    private String weekendPrice;
    private String legalHolidayPrice;
    private List<HotelTimeBean> hotel_time;

    public String getWorkdayPrice() {
        return workdayPrice;
    }

    public void setWorkdayPrice(String workdayPrice) {
        this.workdayPrice = workdayPrice;
    }

    public String getWeekendPrice() {
        return weekendPrice;
    }

    public void setWeekendPrice(String weekendPrice) {
        this.weekendPrice = weekendPrice;
    }

    public String getLegalHolidayPrice() {
        return legalHolidayPrice;
    }

    public void setLegalHolidayPrice(String legalHolidayPrice) {
        this.legalHolidayPrice = legalHolidayPrice;
    }

    public List<HotelTimeBean> getHotel_time() {
        return hotel_time;
    }

    public void setHotel_time(List<HotelTimeBean> hotel_time) {
        this.hotel_time = hotel_time;
    }

    public static class HotelTimeBean {
        /**
         * live_time : 2017-10-20
         * end_time : 2017-10-21
         */

        private String live_time;
        private String end_time;

        public String getLive_time() {
            return live_time;
        }

        public void setLive_time(String live_time) {
            this.live_time = live_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }
    }
}
