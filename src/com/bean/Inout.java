package com.bean;


import com.data.Database;

import android.R.string;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class Inout {

	private Database database;
	private String sort;
	private String money;
	private String owner;
	private int tag;
	private String time;
	private String paysort;
	
	public String getPaysort() {
		return paysort;
	}

	public void setPaysort(String paysort) {
		this.paysort = paysort;
	}
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}
	
	public Inout() {}

	public Inout(Database database, String sort, String money) {
		super();
		this.database = database;
		this.sort = sort;
		this.money = money;
	}

	public Inout(String sort, String money) {
		super();
		this.sort = sort;
		this.money = money;
	}
	
	
	public Inout(Database database) {
		this.database = database;
	}

	public String queryMon(String sort){
		String value=null;
		SQLiteDatabase wr=database.getWritableDatabase();
		Cursor cursor=wr.query("inout",new String[]{"money"}, "sort=?", new String[]{sort}, null, null, null);
		while(cursor.moveToNext()){
			value=cursor.getString(cursor.getColumnIndex("money"));
			//Toast.makeText(getBaseContext(), user, Toast.LENGTH_LONG).show();
		}
		return value;
	}
	
	public Cursor querySo(String sort,String time){
		SQLiteDatabase wr=database.getWritableDatabase();
		Cursor cursor;
		//Cursor cursor=wr.query("inout",null, "owner=?", new String[]{sort}, null, null, null);
		String sql;
		if(time==null){
			sql="select * from inout where owner=? limit 0,10";
			cursor=wr.rawQuery(sql,new String[]{sort});
		}else {
			sql="select * from inout where owner=? and time=?";
			cursor = wr.rawQuery(sql, new String[]{sort, time});
		}
		return cursor;
	}
	public Cursor queryall(){
		SQLiteDatabase wr=database.getWritableDatabase();
		Cursor cursor=wr.query("inout",null,null, null, null, null, null);
		return cursor;
	}
	public void delete(String sort,String time){
		SQLiteDatabase wr=database.getWritableDatabase();
		wr.delete("inout","sort=? and time=? ",new String[]{sort,time});
	}
}
