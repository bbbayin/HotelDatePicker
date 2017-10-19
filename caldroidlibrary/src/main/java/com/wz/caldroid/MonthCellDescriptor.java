// Copyright 2012 Square, Inc.

package com.wz.caldroid;

import java.util.Date;

/**
 * Describes the state of a particular date cell in a {@link MonthView}.
 */
class MonthCellDescriptor {
    public enum RangeState {
        NONE, FIRST, MIDDLE, LAST
    }

    private final Date date;
    private final int value;
    private final boolean isCurrentMonth;
    private boolean isSelected;
    private final boolean isToday;
    private final boolean isSelectable;
    private boolean isHighlighted;
    private RangeState rangeState;

    private boolean isBooked;//该天的状态，是否被预订
    private float price;//价格

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * 构造函数 当天的各种参数
     *
     * @param date
     * @param currentMonth
     * @param selectable
     * @param selected
     * @param today
     * @param highlighted
     * @param value
     * @param rangeState
     * @param isBooked
     * @param price
     */
    MonthCellDescriptor(Date date, boolean currentMonth, boolean selectable, boolean selected,
                        boolean today, boolean highlighted, int value, RangeState rangeState, boolean isBooked, float price) {
        this.date = date;
        isCurrentMonth = currentMonth;
        isSelectable = selectable;
        isHighlighted = highlighted;
        isSelected = selected;
        isToday = today;
        this.value = value;
        this.rangeState = rangeState;
        this.isBooked = isBooked;
        this.price = price;
    }

    MonthCellDescriptor(Date date, boolean currentMonth, boolean selectable, boolean selected,
                        boolean today, boolean highlighted, int value, RangeState rangeState) {
        this.date = date;
        isCurrentMonth = currentMonth;
        isSelectable = selectable;
        isHighlighted = highlighted;
        isSelected = selected;
        isToday = today;
        this.value = value;
        this.rangeState = rangeState;
    }

    public Date getDate() {
        return date;
    }

    public boolean isCurrentMonth() {
        return isCurrentMonth;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    boolean isHighlighted() {
        return isHighlighted;
    }

    void setHighlighted(boolean highlighted) {
        isHighlighted = highlighted;
    }

    public boolean isToday() {
        return isToday;
    }

    public RangeState getRangeState() {
        return rangeState;
    }

    public void setRangeState(RangeState rangeState) {
        this.rangeState = rangeState;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "MonthCellDescriptor{"
                + "date="
                + date
                + ", value="
                + value
                + ", isCurrentMonth="
                + isCurrentMonth
                + ", isSelected="
                + isSelected
                + ", isToday="
                + isToday
                + ", isSelectable="
                + isSelectable
                + ", isHighlighted="
                + isHighlighted
                + ", rangeState="
                + rangeState
                + '}';
    }
}
