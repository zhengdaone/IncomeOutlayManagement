package com.incomeoutlaymanagement;


import java.text.SimpleDateFormat;

import android.widget.ImageView;
import com.bean.Inout;
import com.bean.PaySort;
import com.data.Database;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InOutPutAct extends Activity implements OnClickListener{

    FraIOPA f1,f2;
    Button in,out;
    double float1;
    int count=0,x=0;
    TextView outlaytype,inouttype,incomenum;
    TextView zero,one,two,three,four,five,six,seven,eight,nine;
    TextView clean,del,point,confirm;
    CharSequence[] arraynum;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inoutput);
        bindView();
        f2 = new FraIOPA(1,inouttype);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        transaction.add(R.id.inout_container, f2);
        transaction.show(f2);
        transaction.commit();
        arraynum=new CharSequence[20];
        database=new Database(getBaseContext(), "db", null, 1);
        Intent intent=getIntent();
        TextView payway=(TextView) findViewById(R.id.payway);
        if(intent.getStringExtra("payway")!=null) {
            payway.setText(intent.getStringExtra("payway"));
        }
    }

    public void bindView() {
        in=(Button) findViewById(R.id.inbutton);
        out=(Button) findViewById(R.id.outbutton);
        inouttype=(TextView) findViewById(R.id.inouttype);
        incomenum=(TextView) findViewById(R.id.incomenum);
        zero=(TextView) findViewById(R.id.zero);
        one=(TextView) findViewById(R.id.one);
        two=(TextView) findViewById(R.id.two);
        three=(TextView) findViewById(R.id.three);
        four=(TextView) findViewById(R.id.four);
        five=(TextView) findViewById(R.id.five);
        six=(TextView) findViewById(R.id.six);
        seven=(TextView) findViewById(R.id.seven);
        eight=(TextView) findViewById(R.id.eight);
        nine=(TextView) findViewById(R.id.nine);
        clean=(TextView) findViewById(R.id.clean);
        del=(TextView) findViewById(R.id.del);
        point=(TextView) findViewById(R.id.point);
        confirm=(TextView) findViewById(R.id.confirm);
        ImageView add= (ImageView) findViewById(R.id.addm);
        add.setVisibility(View.INVISIBLE);
        ImageView back= (ImageView) findViewById(R.id.back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        in.setOnClickListener(this);
        in.setEnabled(false);
        out.setOnClickListener(this);
        out.setEnabled(true);
        zero.setOnClickListener(listenekeyb);
        one.setOnClickListener(listenekeyb);
        two.setOnClickListener(listenekeyb);
        three.setOnClickListener(listenekeyb);
        four.setOnClickListener(listenekeyb);
        five.setOnClickListener(listenekeyb);
        six.setOnClickListener(listenekeyb);
        seven.setOnClickListener(listenekeyb);
        eight.setOnClickListener(listenekeyb);
        nine.setOnClickListener(listenekeyb);
        point.setOnClickListener(listenekeyb);

        del.setOnClickListener(delete);
        clean.setOnClickListener(cleanAll);
        confirm.setOnClickListener(confirmNum);
    }

    public void hideAllFragment(FragmentTransaction transaction) {
        if (f1 != null) {
            transaction.hide(f1);
        }
        if (f2 != null) {
            transaction.hide(f2);
        }
    }



    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        TextView cashview=(TextView) findViewById(R.id.cashtext);
        TextView inorout=(TextView) findViewById(R.id.inorout);
        TextView inoroutnum=(TextView) findViewById(R.id.inoroutnum);
        switch (v.getId()) {
                case R.id.outbutton:
                in.setEnabled(true);
                out.setEnabled(false);
                inorout.setText("消费方式：");
                inorout.setTextSize(30);
                inoroutnum.setText("消费金额：");
                if (f1 == null) {
                    f1 = new FraIOPA(0,inouttype);
                    transaction.add(R.id.inout_container, f1);
                    cashview.setText("支出");
                } else {
                    cashview.setText("支出");
                    transaction.show(f1);

                }
                break;
            case R.id.inbutton:
                out.setEnabled(true);
                in.setEnabled(false);
                inorout.setText("收入方式：");
                inoroutnum.setText("收入金额：");
                inorout.setTextSize((float) 29.9);
                if (f2 == null) {
                    f2 = new FraIOPA(1,inouttype);
                    transaction.add(R.id.inout_container, f2);
                    cashview.setText("收入");
                } else {
                    cashview.setText("收入");
                    transaction.show(f2);
                }
                //Toast.makeText(getBaseContext(), inorout.getText(), Toast.LENGTH_LONG).show();
                break;
        }
        transaction.commit();
    }

    private OnClickListener listenekeyb=new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            int id=v.getId();
            TextView view2=(TextView)findViewById(id);
            CharSequence num=incomenum.getText();
            //Toast.makeText(getBaseContext(), "a"+x, Toast.LENGTH_LONG).show();
            if(!((String)view2.getText()).equals(".")&&num.toString().equals("0")){
                incomenum.setText(null);
                num=view2.getText();
                incomenum.setText(num);
                arraynum[count]=num;
                //Toast.makeText(getBaseContext(), "av", Toast.LENGTH_LONG).show();
                if(count<9){
                    count++;
                }
                //Log.d("aa", (String) view2.getText()+"a"+num);
            }else{
                arraynum[count]=num;
                num=num+(String)view2.getText();
                int j=0;
                if(num.toString().indexOf(".")!=-1){
                    int i=num.toString().indexOf(".");
                    if (num.toString().length()>i) {
                        if (num.toString().indexOf(".", i+1)!=-1) {
                            j=1;
                        }
                    }
                }
                if(j==0){
                    incomenum.setText(num);
                    if(count<9){
                        count++;
                    }
                }
                //Toast.makeText(getBaseContext(), "a"+num, Toast.LENGTH_LONG).show();
                //Log.d("aa", (String) view2.getText()+"b"+num);
            }
            float1=Math.random();
            incomenum.setTextSize((float) (float1*0.01+28));
        }
    };

    private OnClickListener delete=new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (count>0) {
                incomenum.setText(arraynum[--count]);
            }
        }
    };

    private OnClickListener cleanAll=new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            incomenum.setText("0");
            count=0;
        }
    };

    private OnClickListener confirmNum=new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            SQLiteDatabase write=database.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            //Inout inout=new Inout(database);
            //String value=inout.queryMon((String)((TextView)findViewById(R.id.inouttype)).getText());
			/*//CharSequence aCharSequence=((TextView)findViewById(R.id.inouttype)).getText();
			//Cursor cursor=write.query("inout",new String[]{"money"}, null, null, null, null, null);
			Cursor cursor=write.query("inout",new String[]{"money"}, "class=?", new String[]{"衣物"}, null, null, null);
			while(cursor.moveToNext()){
				value=cursor.getString(cursor.getColumnIndex("money"));
				//Toast.makeText(getBaseContext(), user, Toast.LENGTH_LONG).show();
			}
			//String value=(String) contentValues.get((String)((TextView)findViewById(R.id.inouttype)).getText());
*/			//if (value==null) {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            String time=simpleDateFormat.format(System.currentTimeMillis());
            contentValues.put("sort",(String)((TextView)findViewById(R.id.inouttype)).getText());
            contentValues.put("money",(String)((TextView)findViewById(R.id.incomenum)).getText());
            contentValues.put("time", time);
            if (in.isEnabled()) {
                contentValues.put("tag", 0);
                insertSort(database, (String)((TextView)findViewById(R.id.payway)).getText(), 0);
            }else {
                contentValues.put("tag", 1);
                insertSort(database, (String)((TextView)findViewById(R.id.payway)).getText(), 1);
            }
				/*SharedPreferences sharedPreferences=getSharedPreferences("user",Context.MODE_PRIVATE );
				if (sharedPreferences!=null) {
					contentValues.put("owner", sharedPreferences.getString("username", ""));
				}*/
            contentValues.put("owner", (String)((TextView)findViewById(R.id.payway)).getText());

            write.insert("inout", null, contentValues);
            Toast.makeText(getBaseContext(), "add success", Toast.LENGTH_LONG).show();
			/*}
			else {
				//Toast.makeText(getBaseContext(), value, Toast.LENGTH_LONG).show();
				int a=Integer.parseInt((String)((TextView)findViewById(R.id.incomenum)).getText());
				a=Integer.parseInt(value)+a;
				contentValues.put("money", a);
				write.update("inout", contentValues, "class=?", new String[]{(String)((TextView)findViewById(R.id.inouttype)).getText()});
			}	*/
        }
    };

    private void insertSort(Database database,String sort,int tag){
        int previous=0;
        ContentValues contentValues=new ContentValues();
        SQLiteDatabase wrDatabase=database.getWritableDatabase();
        PaySort paySort=new PaySort(database);
        int present=Integer.parseInt((String)((TextView)findViewById(R.id.incomenum)).getText());
        if (paySort.querymoney(sort)!=null) {
            previous=Integer.parseInt(paySort.querymoney(sort));
        }
        if(tag==0){
            present=previous-present;
        }else if (tag==1){
            present+=previous;
        }
        if ((paySort.querymoney(sort)==null)) {
            contentValues.put("sort", sort);
            contentValues.put("allmoney", present);
            wrDatabase.insert("msort", null, contentValues);
        }else{
            contentValues.put("allmoney", present);
            wrDatabase.update("msort", contentValues, "sort=?", new String[]{sort});
        }
    }

}


