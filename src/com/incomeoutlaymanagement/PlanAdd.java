package com.incomeoutlaymanagement;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.bean.Plan;
import com.data.Database;
import com.data.TimeBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by zhengda on 2017/5/1.
 */
public class PlanAdd extends Activity {
    private EditText title;
    private EditText comment;
    private Button confirm;
    private TextView cal;
    private Calendar calendar;
    private TimeBuilder timeBuilder;
    private ImageView pre, next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planadd);
        calendar = Calendar.getInstance();
        pre = (ImageView) findViewById(R.id.pre);
        next = (ImageView) findViewById(R.id.next);
        pre.setOnClickListener(listener);
        next.setOnClickListener(listener);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        cal = (TextView) findViewById(R.id.calendarView);
        cal.setText(simpleDateFormat.format(System.currentTimeMillis()));
        timeBuilder=new TimeBuilder(cal.getText().toString());
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PlanAdd.this, AlertDialog.THEME_HOLO_LIGHT, dplister,
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
        confirm= (Button) findViewById(R.id.ok);
        title= (EditText) findViewById(R.id.title2);
        comment= (EditText) findViewById(R.id.comment);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title= (EditText) findViewById(R.id.title2);
                comment= (EditText) findViewById(R.id.comment);
                Database database=new Database(getBaseContext(), "db", null, 1);
                Plan plan=new Plan(database);
                plan.insert(title.getText().toString(),comment.getText().toString(),cal.getText().toString());
               // Toast.makeText(getBaseContext(),title.getText(),Toast.LENGTH_LONG).show();
               /* Intent intent=new Intent();
                intent.setClass(getBaseContext(),PlanShow.class);
                startActivity(intent);*/
                setResult(0);
                finish();
            }
        });
        ImageView add= (ImageView) findViewById(R.id.addm);
        add.setVisibility(View.INVISIBLE);
        ImageView back= (ImageView) findViewById(R.id.back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
            // }
        }
    };
    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            timeBuilder.setTime(cal.getText().toString());
            switch (v.getId()) {
                case R.id.pre:
                    cal.setText(timeBuilder.changeTimep());
                    break;
                case R.id.next:
                    cal.setText(timeBuilder.changeTimen());
                    break;
            }
        }
    };
}
