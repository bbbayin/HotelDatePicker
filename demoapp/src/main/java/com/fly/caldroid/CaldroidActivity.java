package com.fly.caldroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wz.caldroid.CalendarCellDecorator;
import com.wz.caldroid.CalendarPickerView;
import com.wz.caldroid.listener.OnStarAndEndSelectedListener;
import com.wz.caldroid.util.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class CaldroidActivity extends Activity {
    private CalendarPickerView calendar;
    private TextView mTvTotal;
    private TextView mTvEndDay;
    private TextView mTvStartDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.calendar_activity);

        Bundle myBundle = getIntent().getExtras();
        long seleteTime = myBundle.getLong("selete_time");
        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.MONTH, 3);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.MONTH, 0);

        calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        mTvStartDay = (TextView) findViewById(R.id.list_header_tv_order_day);
        mTvEndDay = (TextView) findViewById(R.id.list_header_tv_leave_day);
        mTvTotal = (TextView) findViewById(R.id.list_header_tv_total);

        Calendar today = Calendar.getInstance();
        ArrayList<Date> dates = new ArrayList<Date>();
        if (seleteTime > 0) {
            Date d1 = new Date(seleteTime);
            dates.add(d1);
        } else {
            dates.add(today.getTime());
        }

        calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
        calendar.init(lastYear.getTime(), nextYear.getTime()) //
                .inMode(CalendarPickerView.SelectionMode.RANGE) //
                .withSelectedDate(dates.get(0));
        initButtonListeners();
    }


    private void initButtonListeners() {
        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                Intent intent = new Intent();
                intent.putExtra("SELETE_DATA_TIME", calendar.getSelectedDate().getTime());
                setResult(2, intent);
            }

            @Override
            public void onDateUnselected(Date date) {
                clearOrderInfo();
                Log.d("---onDateUnselected---", "清除日期:" + date.toString());
            }
        });

        calendar.setOnStarAndEndSelectedListener(new OnStarAndEndSelectedListener() {
            @Override
            public void onFirstDateSelected(Date date) {
                mTvStartDay.setText(Utils.formatDateToMMdd(date));

            }

            @Override
            public void onEndDateSelected(Date date) {
                mTvEndDay.setText(Utils.formatDateToMMdd(date));

            }

            @Override
            public void onDateSelectedFinish() {
                mTvTotal.setText("共" + calendar.getSelectedDates().size() + "天");
            }
        });

        View titlebar_img_back = findViewById(R.id.titlebar_img_back);
        titlebar_img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void clearOrderInfo() {
        mTvStartDay.setText(R.string.instead_char);
        mTvEndDay.setText(R.string.instead_char);
        mTvTotal.setText(R.string.instead_char);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
