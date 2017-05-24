package com.incomeoutlaymanagement;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.*;
import com.bean.Inout;
import com.data.Database;
import android.app.DatePickerDialog;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout.LayoutParams;
import com.data.TimeBuilder;

public class InOutDis extends Activity {
    LayoutInflater layoutInflater;
    ViewGroup viewGroup;
    Inout inout;
    TextView cal;
    Intent intent;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private Calendar calendar;
    TimeBuilder timeBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //layoutInflater = LayoutInflater.from(getBaseContext());
        //View layout = layoutInflater.inflate(R.layout.inoutdis, viewGroup, false);
        setContentView(R.layout.inoutdis);
        intent = getIntent();
        Database database = new Database(getBaseContext(), "db", null, 1);
        inout = new Inout(database);
        Cursor cursor = inout.querySo(intent.getStringExtra("payway"), null);
        loadview(cursor);
        cal = (TextView) findViewById(R.id.calendarView);
        bindview();
        calendar = Calendar.getInstance();
        cal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(InOutDis.this, AlertDialog.THEME_HOLO_LIGHT, dplister,
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

        //copyDataBaseToSD();
    }

    public void loadview(Cursor cursor) {
        LinearLayout rLayout = (LinearLayout) findViewById(R.id.inoutdis);
        rLayout.removeAllViews();
        //rLayout.removeAllViews();
        while (cursor.moveToNext()) {
            String money = cursor.getString(cursor.getColumnIndex("money"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String sort = cursor.getString(cursor.getColumnIndex("sort"));
            int tag = cursor.getInt(cursor.getColumnIndex("tag"));
            TableLayout tableLayout = new TableLayout(this);
            tableLayout.setMinimumHeight(160);
            tableLayout.setMinimumWidth(900);
            TableRow tableRow = new TableRow(this);
            TextView tm = new TextView(this);
            tm.setWidth(250);
            tm.setHeight(160);
            tm.setGravity(Gravity.CENTER);
            tm.setTextColor(getResources().getColor(R.drawable.black));
            //tm.setPadding(1,1, 1,1);
            if (tag == 1) {
                tm.setText("+" + money);
            } else {
                tm.setText("-" + money);
            }
            tm.setTextSize(20);
            //tm.setBackgroundColor(R.drawable.white);
            TextView tt = new TextView(this);
            tt.setWidth(500);
            tt.setHeight(160);
            tt.setGravity(Gravity.CENTER);
            tt.setPadding(1, 1, 1, 1);
            tt.setText(time);
            tt.setTextSize(20);
            tt.setTextColor(getResources().getColor(R.drawable.black));
            //tt.setBackgroundColor(R.drawable.white);

            TextView ts = new TextView(this);
            ts.setWidth(300);
            ts.setHeight(160);
            ts.setGravity(Gravity.CENTER);
            ts.setPadding(1, 1, 1, 1);
            ts.setText(sort);
            ts.setTextSize(20);
            ts.setTextColor(getResources().getColor(R.drawable.black));
            //ts.setBackgroundColor(R.drawable.white);
            tableRow.addView(tm);
            tableRow.addView(ts);
            tableRow.addView(tt);
            tableRow.setOnClickListener(tlistener);
            tableLayout.addView(tableRow);
            rLayout.addView(tableLayout);
        }
        // TextView textView=(TextView) layout.findViewById(R.id.aa);
		/*ImageView imageView = new ImageView(this);
		// imageView.setLayoutParams(new
		// RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT/5));
		imageView.setImageResource(R.drawable.plus);
		Button button = new Button(this);
		button.setText("aa");
		WindowManager manager = this.getWindowManager();
		DisplayMetrics outMetrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(outMetrics);
		int height = outMetrics.heightPixels;
		// RelativeLayout.LayoutParams layoutParams=new
		// RelativeLayout.LayoutParams(100, 100);
		// layoutParams.setMargins(10, 10, 10, 10);
		//RelativeLayout rLayout = (RelativeLayout) findViewById(R.id.inoutdis);
		imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height / 9));
		// ImageView imageView2=new ImageView(this);
		// imageView.setBackgroundColor(0xffffff);
		// imageView2.setLayoutParams(new
		// LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,height/9));
		//rLayout.removeAllViews();
		rLayout.addView(imageView);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Intent intent2=getIntent();
				intent.putExtra("payway", intent2.getStringExtra("payway"));
				intent.setClass(getBaseContext(), InOutPutAct.class);
				startActivity(intent);
			}
		});*/
        ImageView add = (ImageView) findViewById(R.id.addm);
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Intent intent2 = getIntent();
                intent.putExtra("payway", intent2.getStringExtra("payway"));
                intent.setClass(getBaseContext(), InOutPutAct.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        menu.add(Menu.NONE, Menu.NONE, 101, "添加").setIcon(
                android.R.drawable.ic_menu_add);
        return true;
    }

    /* @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         // TODO Auto-generated method stub
         switch (item.getItemId()) {
             case 0:
                 Intent intent = new Intent();
                 Intent intent2=getIntent();
                 intent.putExtra("payway", intent2.getStringExtra("payway"));
                 intent.setClass(getBaseContext(), InOutPutAct.class);

                 startActivity(intent);
                 break;
             default:
                 break;
         }
         return true;
     }*/
	/*public void copyDataBaseToSD(){
	     if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
	            return ;
	         }
	     File dbFile = new File(getApplicationContext().getDatabasePath("db").getAbsolutePath());
	     File file  = new File(Environment.getExternalStorageDirectory(), "seeker.db");
	    // System.out.println(getApplicationContext().getDatabasePath("db").getAbsolutePath());
	     //File dbFile = new File(Environment.getExternalStorageDirectory()+"/seeker.db");
	     //File file  = new File("/data/data/com.incomeoutlaymanagement/databases/", "db");
	    // Toast.makeText(getBaseContext(), getApplicationContext().getDatabasePath("db").getAbsolutePath(), Toast.LENGTH_LONG).show();
	    // Toast.makeText(getBaseContext(), Environment.getExternalStorageDirectory(), Toast.LENGTH_LONG).show();
	     FileChannel inChannel = null,outChannel = null;
	     try {
	        file.createNewFile();
	        inChannel = new FileInputStream(dbFile).getChannel();
	        outChannel = new FileOutputStream(file).getChannel();
	        inChannel.transferTo(0, inChannel.size(), outChannel);
	    } catch (Exception e) {
	       // Log.e(TAG, "copy dataBase to SD error.");
	        e.printStackTrace();
	    }finally{
	        try {
	            if (inChannel != null) {
	                inChannel.close();
	                inChannel = null;
	            }
	            if(outChannel != null){
	                outChannel.close();
	                outChannel = null;
	            }
	        } catch (IOException e) {
	            //LogUtils.e(TAG, "file close error.");
	            e.printStackTrace();
	        }
	    }
	}  */
    private DatePickerDialog.OnDateSetListener dplister = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            //Toast.makeText(getBaseContext(),"aa",Toast.LENGTH_LONG).show();
            StringBuilder t = new StringBuilder()
                    .append(year)
                    .append("-")
                    .append((monthOfYear + 1) < 10 ? "0"
                            + (monthOfYear + 1) : (monthOfYear + 1))
                    .append("-")
                    .append((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth);
            cal.setText(t);
            Intent intent = getIntent();
            Database database = new Database(getBaseContext(), "db", null, 1);
            Inout inout = new Inout(database);
            Cursor cursor = inout.querySo(intent.getStringExtra("payway"), t.toString());
            // if(cursor!=null){
            loadview(cursor);
            // }
        }
    };

    private void bindview() {
        ImageView pre = (ImageView) findViewById(R.id.pre);
        ImageView next = (ImageView) findViewById(R.id.next);
        timeBuilder = new TimeBuilder(cal.getText().toString());
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = simpleDateFormat.format(System.currentTimeMillis());
        cal.setText(time);

        pre.setOnClickListener(listener);
        next.setOnClickListener(listener);
    }

    private OnClickListener listener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            //TextView editText=(TextView) findViewById(R.id.calendarView);
            Intent intent = getIntent();
            Database database = new Database(getBaseContext(), "db", null, 1);
            Inout inout = new Inout(database);
            Cursor cursor = null;
            timeBuilder.setTime(cal.getText().toString());
            switch (v.getId()) {
                case R.id.pre:
                    //Toast.makeText(getBaseContext(),"aa",Toast.LENGTH_LONG).show();
                    cursor = inout.querySo(intent.getStringExtra("payway"), timeBuilder.changeTimep());
                    cal.setText(timeBuilder.changeTimep());
                    break;
                case R.id.next:
                    //Toast.makeText(getBaseContext(),"bb",Toast.LENGTH_LONG).show();
                    cursor = inout.querySo(intent.getStringExtra("payway"), timeBuilder.changeTimen());
                    cal.setText(timeBuilder.changeTimen());
                    break;
            }
            loadview(cursor);
        }
    };
    public OnClickListener tlistener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            final TextView textView = (TextView) (((TableRow) v).getChildAt(1));
            final TextView textView2 = (TextView) (((TableRow) v).getChildAt(2));
            Dialog dialog = new AlertDialog.Builder(InOutDis.this)
                    .setTitle("删除对话框")
                    .setIcon(R.drawable.ic_launcher)
                    .setMessage("确认删除吗？")
//相当于点击确认按钮
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            inout.delete(textView.getText().toString(), textView2.getText().toString());
                            Cursor cursor = inout.querySo(intent.getStringExtra("payway"), null);
                            loadview(cursor);
                        }
                    })
//相当于点击取消按钮
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
// TODO Auto-generated method stub

                        }
                    })
                    .create();
            dialog.show();
        }
    };
}
