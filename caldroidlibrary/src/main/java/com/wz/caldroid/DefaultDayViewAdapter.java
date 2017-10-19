package com.wz.caldroid;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class DefaultDayViewAdapter implements DayViewAdapter {
  @Override
  public void makeCellView(CalendarCellView parent) {
//      TextView textView = new TextView(
//              new ContextThemeWrapper(parent.getContext(), R.style.CalendarCell_CalendarDate));
//      textView.setDuplicateParentStateEnabled(true);
//      parent.addView(textView);
//      parent.setDayOfMonthTextView(textView);

      View dayview = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_view, null, false);
      parent.addView(dayview);
      TextView tvDay = (TextView) dayview.findViewById(R.id.day_view_tv_day);
      TextView tvState = (TextView) dayview.findViewById(R.id.day_view_tv_state);
      parent.setDayOfMonthTextView(tvDay);
      parent.setDayStateTextView(tvState);
  }
}
