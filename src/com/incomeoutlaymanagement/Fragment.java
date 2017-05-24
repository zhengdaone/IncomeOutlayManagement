package com.incomeoutlaymanagement;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment extends android.app.Fragment {

	private int width;
	private int height;

	public Fragment(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public View course(View cFragment) {
		// 找到周几的线性布局
		LinearLayout course_linearLayout = (LinearLayout) cFragment.findViewById(R.id.tab_menu);
		// 创建button
		Button b = new Button(course_linearLayout.getContext());
		// 设置高度
		b.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height / 5));
		// 设置文字 内容与颜色
		b.setText("test");
		b.setTextColor(Color.parseColor("#F5F5DC"));
		// 设置背景色
		b.setBackgroundColor(Color.parseColor("#FF4081"));
		// 设置button所属的位置
		ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(b.getLayoutParams());
		margin.setMargins(margin.leftMargin, margin.width, margin.rightMargin, margin.height);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(margin);
		b.setLayoutParams(layoutParams);
		// 添加到线性布局
		course_linearLayout.addView(b);
		return cFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View cFragment = inflater.inflate(R.layout.fragment, container, false);
		cFragment = course(cFragment);
		return cFragment;
	}
}
