package com.fly.caldroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.wz.caldroid.CalendarCellDecorator;
import com.wz.caldroid.CalendarPickerView;
import com.wz.caldroid.Constants;
import com.wz.caldroid.bean.HolidayPriceBean;
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
    private Calendar mNextYear;
    private Calendar mLastYear;
    private String totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.calendar_activity);
        //设置窗口参数
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        int width = wm.getDefaultDisplay().getWidth();

        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = width;
        attributes.height = (int) (height * 0.8);
        window.setAttributes(attributes);

        ArrayList<Date> dates = new ArrayList<Date>();
        ArrayList<Date> liveDates = new ArrayList<Date>();
        ArrayList<HolidayPriceBean> holidayList = new ArrayList<>();

        //设置已租日期
        try {
            JSONObject oJson = new JSONObject(Constants.TestJson);
            JSONArray arrOrderedDate = oJson.getJSONArray("hotel_time");
            if (arrOrderedDate != null && arrOrderedDate.length() > 0) {
                //获取入住时间
                for (int i = 0; i < arrOrderedDate.length(); i++) {
                    JSONObject objDate = (JSONObject) arrOrderedDate.get(i);
                    Date live_time = new Date(Utils.getTimeStemp(objDate.getString("live_time")));
                    dates.addAll(Utils.findDates(live_time,
                            new Date(Utils.getTimeStemp(objDate.getString("end_time")))));
                    liveDates.add(live_time);
                }
            }
            JSONArray legalArr = oJson.getJSONArray("legalHolidayPrice");
            if (legalArr != null && legalArr.length() > 0) {
                //获取节假日数据
                for (int i = 0; i < legalArr.length(); i++) {
                    String legalHoliday = legalArr.getJSONObject(i).getString("legalHoliday");
                    String holidayPrice = legalArr.getJSONObject(i).getString("holidayPrice");
                    holidayList.add(new HolidayPriceBean(new Date(Utils.getTimeStemp(legalHoliday)), holidayPrice));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //价格
        PriceDescriptor priceDescriptor = JSON.parseObject(Constants.TestJson, PriceDescriptor.class);

        Bundle myBundle = getIntent().getExtras();
        long seleteTime = myBundle.getLong("selete_time");

        mNextYear = Calendar.getInstance();
        mNextYear.add(Calendar.MONTH, 3);

        mLastYear = Calendar.getInstance();
        mLastYear.add(Calendar.MONTH, 0);

        calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        mTvStartDay = (TextView) findViewById(R.id.list_header_tv_order_day);
        mTvEndDay = (TextView) findViewById(R.id.list_header_tv_leave_day);
        mTvTotal = (TextView) findViewById(R.id.list_header_tv_total);


        calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
        //设置日历的开始时间，结束时间，价格描述
        calendar.init(mLastYear.getTime(), mNextYear.getTime(), priceDescriptor) //
                //可多选的模式
                .inMode(CalendarPickerView.SelectionMode.RANGE) //
                //初始化时带入已经选中的时间
//                .withSelectedDate(dates.get(0))
                //设置已经出租的日期
                .withHighlightedDates(dates)
                //设置入住时间，点击事件判断要用
                .withLiveDates(liveDates)
                //设置节假日价格
                .withHolidayDates(holidayList);
        initButtonListeners();

        //click event
        findViewById(R.id.list_header_tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.list_header_tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (null != calendar.getSelectedDate() && calendar.getSelectedDates().size() >= 2) {
                    intent.putExtra("START_DATA_TIME", calendar.getSelectedDates().get(0).getTime());
                    intent.putExtra("END_DATA_TIME", calendar.getSelectedDates().get(calendar.getSelectedDates().size() - 1).getTime());
                    intent.putExtra("TOTAL_PRICE", totalPrice);
                    setResult(2, intent);
                    finish();
                }
            }
        });
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
            public void onDateSelectedFinish(int total, float price) {
                mTvTotal.setText(total + "晚");
                totalPrice = String.valueOf(price);
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
