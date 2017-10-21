package com.fly.caldroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private long startTime = 0;
    private long endTime = 0;
    private Button time_choice_view;
    private TextView tvStartTime;
    private TextView tvEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time_choice_view = (Button) findViewById(R.id.time_choice_view);
        tvStartTime = (TextView) findViewById(R.id.tv_start_time);
        tvEndTime = (TextView) findViewById(R.id.tv_end_time);
        time_choice_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putLong("selete_time", startTime);
                intent.putExtras(bundle);
                intent.setClass(MainActivity.this, CaldroidActivity.class);
                startActivityForResult(intent, 5);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 5) {
            if (resultCode == 2) {
                startTime = data.getLongExtra("START_DATA_TIME", 0);
                endTime = data.getLongExtra("END_DATA_TIME", 0);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date d1 = new Date(startTime);
                Date d2 = new Date(endTime);
                String t1 = format.format(d1);
                String t2 = format.format(d2);
                if (startTime > 0 && endTime>0) {
                    tvStartTime.setText(t1);
                    tvEndTime.setText(t2);
                } else {
                    return;
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
