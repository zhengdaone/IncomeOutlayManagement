package com.bean;

import com.data.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PaySort {

	private Database database;
	private String sort;
	private String allmoney;
	public PaySort(Database database, String sort, String allmoney) {
		this.database = database;
		this.sort = sort;
		this.allmoney = allmoney;
	}
	public PaySort(Database database) {
		this.database = database;
	}
	public PaySort() {
	}
	
	public Database getDatabase() {
		return database;
	}
	public void setDatabase(Database database) {
		this.database = database;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getAllmoney() {
		return allmoney;
	}
	public void setAllmoney(String allmoney) {
		this.allmoney = allmoney;
	}
	
	public String querymoney(String sort) {
		String value=null;
		SQLiteDatabase wr=database.getWritableDatabase();
		Cursor cursor=wr.query("msort",new String[]{"allmoney"}, "sort=?", new String[]{sort}, null, null, null);
		while(cursor.moveToNext()){
			value=cursor.getString(cursor.getColumnIndex("allmoney"));
			//Toast.makeText(getBaseContext(), user, Toast.LENGTH_LONG).show();
		}
		if (value==null) {
			return null;
		}else {
			return value;
		}
	}
}
