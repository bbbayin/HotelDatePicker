package com.fly.caldroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.wz.caldroid.CalendarCellDecorator;
import com.wz.caldroid.CalendarPickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class CaldroidActivity extends Activity {
    private CalendarPickerView calendar;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.calendar_activity);

        Bundle myBundle = getIntent().getExtras();
        long seleteTime  = myBundle.getLong("selete_time");
        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.MONTH, 3);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.MONTH, 0);

        calendar = (CalendarPickerView) findViewById(R.id.calendar_view);

        Calendar today = Calendar.getInstance();
        ArrayList<Date> dates = new ArrayList<Date>();
        if (seleteTime>0){
            Date d1=new Date(seleteTime);
            dates.add(d1);
        }else{
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
//                finish();
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });

        View titlebar_img_back=findViewById(R.id.titlebar_img_back);
        titlebar_img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



    @Override public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
