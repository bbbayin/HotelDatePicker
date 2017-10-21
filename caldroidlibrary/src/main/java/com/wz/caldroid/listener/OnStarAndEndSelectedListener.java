package com.wz.caldroid.listener;

import java.util.Date;

/****************************************
 * 功能说明:  
 *
 * Author: Created by bayin on 2017/10/19.
 ****************************************/

public interface OnStarAndEndSelectedListener {
    void onFirstDateSelected(Date date);

    void onEndDateSelected(Date date);

    void onDateSelectedFinish(int totalDays);
}
