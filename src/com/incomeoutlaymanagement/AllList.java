package com.incomeoutlaymanagement;

import com.bean.Inout;
import com.data.Database;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class AllList extends Fragment{
	private Inout inout;
	View cFragment=null;
	public AllList() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		cFragment = inflater.inflate(R.layout.alllist, container, false);
		loadview(cFragment);
		return cFragment;
	}
	public void loadview(View cFragment) {
		Database database=new Database(getActivity(), "db", null, 1);
		inout=new Inout(database);
		Cursor cursor=inout.queryall();
		LinearLayout rLayout = (LinearLayout) cFragment.findViewById(R.id.alllist);
		//rLayout.removeAllViews();
		while(cursor.moveToNext()){
			String money=cursor.getString(cursor.getColumnIndex("money"));
			String time=cursor.getString(cursor.getColumnIndex("time"));
			String sort=cursor.getString(cursor.getColumnIndex("sort"));
			int tag=cursor.getInt(cursor.getColumnIndex("tag"));
			TableLayout tableLayout=new TableLayout(getActivity());
			tableLayout.setMinimumHeight(160);
		    tableLayout.setMinimumWidth(900);
			TableRow tableRow=new TableRow(getActivity());
			TextView tm=new TextView(getActivity());
			tm.setWidth(250);
			tm.setHeight(160);
			tm.setGravity(Gravity.CENTER);
			//tm.setPadding(1,1, 1,1);
			if (tag == 1) {
				tm.setText("+"+money);
			}else {
				tm.setText("-"+money);
			}
			tm.setTextSize(20);
			tm.setTextColor(getResources().getColor(R.drawable.black));
			//tm.setBackgroundColor(R.drawable.white);
			TextView tt=new TextView(getActivity());
			tt.setWidth(500);
			tt.setHeight(160);
			tt.setGravity(Gravity.CENTER);
			tt.setPadding(1,1, 1,1);
			tt.setText(time);
			tt.setTextSize(20);
			tt.setTextColor(getResources().getColor(R.drawable.black));
			//tt.setBackgroundColor(R.drawable.white);
			
			TextView ts=new TextView(getActivity());
			ts.setWidth(300);
			ts.setHeight(160);
			ts.setGravity(Gravity.CENTER);
			ts.setPadding(1,1,1,1);
			ts.setText(sort);
			ts.setTextSize(20);
			ts.setTextColor(getResources().getColor(R.drawable.black));
			//ts.setBackgroundColor(R.drawable.white);
			tableRow.addView(tm);
			tableRow.addView(ts);
			tableRow.addView(tt);
			tableLayout.addView(tableRow);
			rLayout.addView(tableLayout);
		}
}
}