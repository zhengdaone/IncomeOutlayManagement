package com.incomeoutlaymanagement;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import com.bean.Inout;
import com.bean.Plan;
import com.data.Database;
import com.data.TimeBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by zhengda on 2017/5/1.
 */
public class PlanShow extends Activity {
    private TextView cal;
    private ImageView pre, next,addm;
    private Calendar calendar;
    private TimeBuilder timeBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planshow);
        calendar = Calendar.getInstance();
        cal = (TextView) findViewById(R.id.calendarView);
        Database database = new Database(getBaseContext(), "db", null, 1);
        Plan plan = new Plan(database);
        bindview();
        Cursor cursor = plan.queryPlan(null);
        loadview(cursor);
    }

    private void bindview() {
        pre = (ImageView) findViewById(R.id.pre);
        next = (ImageView) findViewById(R.id.next);
        addm= (ImageView) findViewById(R.id.addm);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        cal.setText(simpleDateFormat.format(System.currentTimeMillis()));
        timeBuilder=new TimeBuilder(cal.getText().toString());

        pre.setOnClickListener(listener);
        next.setOnClickListener(listener);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PlanShow.this, AlertDialog.THEME_HOLO_LIGHT, dplister,
                        /*new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int month, int day) {
                                // TODO Auto-generated method stub
                                mYear = year;
                                mMonth = month;
                                mDay = day;
                                // 更新EditText控件日期 小于10加0
                                cal.setText(new StringBuilder()
                                        .append(mYear)
                                        .append("-")
                                        .append((mMonth + 1) < 10 ? "0"
                                                + (mMonth + 1) : (mMonth + 1))
                                        .append("-")
                                        .append((mDay < 10) ? "0" + mDay : mDay));
                            }
                        }, */calendar.get(Calendar.YEAR), calendar
                        .get(Calendar.MONTH), calendar
                        .get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        addm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), PlanAdd.class);
                startActivityForResult(intent,0);
            }
        });
        ImageView back= (ImageView) findViewById(R.id.back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void loadview(Cursor cursor) {
        LinearLayout rLayout = (LinearLayout) findViewById(R.id.plandis);
        rLayout.removeAllViews();
        //rLayout.removeAllViews();
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            //String comment=cursor.getString(cursor.getColumnIndex("comment"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            TableLayout tableLayout = new TableLayout(this);
            tableLayout.setMinimumHeight(160);
            tableLayout.setMinimumWidth(900);
            TableRow tableRow = new TableRow(this);
            //tm.setBackgroundColor(R.drawable.white);
            TextView tt = new TextView(this);
            tt.setWidth(400);
            tt.setHeight(160);
            tt.setGravity(Gravity.CENTER);
            tt.setPadding(1, 1, 1, 1);
            tt.setText(title);
            tt.setTextSize(20);
            tt.setTextColor(getResources().getColor(R.drawable.black));
            //tt.setBackgroundColor(R.drawable.white);

            TextView ts = new TextView(this);
            ts.setWidth(800);
            ts.setHeight(160);
            ts.setGravity(Gravity.CENTER);
            ts.setPadding(1, 1, 1, 1);
            ts.setText(time);
            ts.setTextSize(20);
            ts.setTextColor(getResources().getColor(R.drawable.black));
            //ts.setBackgroundColor(R.drawable.white);
            // tableRow.addView(tm);
            tableRow.addView(tt);
            tableRow.addView(ts);
            tableRow.setOnClickListener(tlisten);
            tableLayout.addView(tableRow);
            rLayout.addView(tableLayout);
        }
    }
    private DatePickerDialog.OnDateSetListener dplister=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            //Toast.makeText(getBaseContext(),"aa",Toast.LENGTH_LONG).show();
            StringBuilder t=new StringBuilder()
                    .append(year)
                    .append("-")
                    .append((monthOfYear+ 1) < 10 ? "0"
                            + (monthOfYear + 1) : (monthOfYear + 1))
                    .append("-")
                    .append((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth);
            cal.setText(t);
            Database database=new Database(getBaseContext(), "db", null, 1);
            Plan plan = new Plan(database);
            Cursor cursor = plan.queryPlan(cal.getText().toString());
            // if(cursor!=null){
            loadview(cursor);
            // }
        }
    };
    public View.OnClickListener tlisten=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView textView= (TextView) (((TableRow)v).getChildAt(0));
            TextView textView2= (TextView) (((TableRow)v).getChildAt(1));
            Intent intent=new Intent();
            intent.putExtra("title",textView.getText());
            intent.putExtra("time",textView2.getText());
            intent.setClass(getBaseContext(),PlanDetails.class);
            startActivityForResult(intent,0);
        }
    };
    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TextView editText=(TextView) findViewById(R.id.calendarView);
            Database database=new Database(getBaseContext(), "db", null, 1);
            Plan plan=new Plan(database);
            Cursor cursor=null;
            //Toast.makeText(getBaseContext(),cal.getText(),Toast.LENGTH_LONG).show();
            timeBuilder.setTime(cal.getText().toString());
            switch (v.getId()) {
                case R.id.pre:
                    //Toast.makeText(getBaseContext(),"aa",Toast.LENGTH_LONG).show();
                    cursor = plan.queryPlan(timeBuilder.changeTimep());
                    cal.setText(timeBuilder.changeTimep());
                    break;
                case R.id.next:
                    //Toast.makeText(getBaseContext(),"bb",Toast.LENGTH_LONG).show();
                    cursor = plan.queryPlan(timeBuilder.changeTimen());
                    cal.setText(timeBuilder.changeTimen());
                    break;
            }
            loadview(cursor);
        }
    };
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Database database = new Database(getBaseContext(), "db", null, 1);
        Plan plan = new Plan(database);
        Cursor cursor = plan.queryPlan(cal.getText().toString());
        loadview(cursor);
    }
}