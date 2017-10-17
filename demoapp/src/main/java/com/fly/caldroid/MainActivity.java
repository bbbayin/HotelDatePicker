package com.fly.caldroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private long seleteTime = 0;
    private TextView time_choice_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time_choice_view = (TextView) findViewById(R.id.time_choice_view);
        time_choice_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putLong("selete_time", seleteTime);
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
                seleteTime = data.getLongExtra("SELETE_DATA_TIME", 0);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date d1 = new Date(seleteTime);
                String t1 = format.format(d1);
                if (seleteTime > 0) {
                    time_choice_view.setText(t1);
                } else {
                    return;
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
