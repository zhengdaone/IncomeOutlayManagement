package com.incomeoutlaymanagement;

import java.util.Random;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class FraIOPA extends Fragment{
	private int type;
	private TextView textView;
	private View view = null;
	private double float1;
	private TextView food,cloth,netshopping,topup,traffic,intercourse,daily,house,gift,inother;
	private TextView standtreat,entertainment,other,salary,rent,ptjob,reward,redpk,invest,discount;
	public FraIOPA(int type,TextView textView){
		this.type=type;
		this.textView=textView;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub	
		if(type==0){
			view=inflater.inflate(R.layout.frainoutput, container,false);	
			bindView(view);
		}else {
			view=inflater.inflate(R.layout.frainoutput2, container,false);
			bindView2(view);
		}		
		
		return view;
	}

	public void bindView(View view) {
		inother=(TextView) view.findViewById(R.id.inother);
		cloth=(TextView) view.findViewById(R.id.cloth);
		netshopping=(TextView) view.findViewById(R.id.netshopping);
		topup=(TextView) view.findViewById(R.id.topup);
		traffic=(TextView) view.findViewById(R.id.traffic);
		intercourse=(TextView) view.findViewById(R.id.intercourse);
		daily=(TextView) view.findViewById(R.id.daily);
		house=(TextView) view.findViewById(R.id.house);
		gift=(TextView) view.findViewById(R.id.gift);
		food=(TextView) view.findViewById(R.id.food);
		standtreat=(TextView) view.findViewById(R.id.standtreat);
		entertainment=(TextView) view.findViewById(R.id.entertainment);
	
		food.setOnClickListener(listenType);
		cloth.setOnClickListener(listenType);
		netshopping.setOnClickListener(listenType);
		topup.setOnClickListener(listenType);
		traffic.setOnClickListener(listenType);
		intercourse.setOnClickListener(listenType);
		daily.setOnClickListener(listenType);
		house.setOnClickListener(listenType);
		gift.setOnClickListener(listenType);
		inother.setOnClickListener(listenType);
		standtreat.setOnClickListener(listenType);
		entertainment.setOnClickListener(listenType);
	}
	
	public void bindView2(View view) {
		salary=(TextView) view.findViewById(R.id.salary);
		rent=(TextView) view.findViewById(R.id.rent);
		ptjob=(TextView) view.findViewById(R.id.ptjob);
		reward=(TextView) view.findViewById(R.id.reward);
		redpk=(TextView) view.findViewById(R.id.redpk);
		invest=(TextView) view.findViewById(R.id.invest);
		discount=(TextView) view.findViewById(R.id.discount);
		other=(TextView) view.findViewById(R.id.other);
		
		salary.setOnClickListener(listenType);
		rent.setOnClickListener(listenType);
		reward.setOnClickListener(listenType);
		ptjob.setOnClickListener(listenType);
		redpk.setOnClickListener(listenType);
		invest.setOnClickListener(listenType);
		discount.setOnClickListener(listenType);
		other.setOnClickListener(listenType);
	}
	
public OnClickListener listenType=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id=v.getId();
			TextView view2=(TextView) view.findViewById(id);
			textView.setText(view2.getText());
			Log.d("aa", (String) view2.getText());
			float1=Math.random();
			textView.setTextSize((float) (float1*1+28));
		}
	};
}

