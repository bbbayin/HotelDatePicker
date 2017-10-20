package com.fly.caldroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.wz.caldroid.CalendarCellDecorator;
import com.wz.caldroid.CalendarPickerView;
import com.wz.caldroid.Constants;
import com.wz.caldroid.bean.PriceDescriptor;
import com.wz.caldroid.listener.OnStarAndEndSelectedListener;
import com.wz.caldroid.util.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        ArrayList<Date> dates = new ArrayList<Date>();

        //设置已租日期
        try {
            JSONObject oJson = new JSONObject(Constants.TestJson);
            JSONArray arrOrderedDate = oJson.getJSONArray("hotel_time");
            if (arrOrderedDate != null && arrOrderedDate.length() > 0) {
                for (int i = 0; i < arrOrderedDate.length(); i++) {
                    JSONObject objDate = (JSONObject) arrOrderedDate.get(i);
                    dates.addAll(Utils.findDates(new Date(Utils.getTimeStemp(objDate.getString("live_time"))),
                            new Date(Utils.getTimeStemp(objDate.getString("end_time")))));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //价格
        PriceDescriptor priceDescriptor = JSON.parseObject(Constants.TestJson, PriceDescriptor.class);

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


        calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
        calendar.init(lastYear.getTime(), nextYear.getTime(),priceDescriptor) //
                .inMode(CalendarPickerView.SelectionMode.RANGE) //
//                .withSelectedDate(dates.get(0))
                .withHighlightedDates(dates)
        ;
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
