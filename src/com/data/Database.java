package com.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

	public Database(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "create table inout(id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL,sort CHAR NOT NULL,money VARCHAR NOT NULL"
				+ ",tag INTEGER NOT NULL,time VARCHAR NOT NULL,owner VARCHAR)";
		String sql2="create table msort(sort CHAR PRIMARY KEY NOT NULL,"
				+ "allmoney VARCHAR NOT NULL)";
		String sql3="create table plan(id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL,title VARCHAR NOT NULL,"
				+ "plan VARCHAR NOT NULL,time VARCHAR NOT NULL)";
		db.execSQL(sql);
		db.execSQL(sql2);
		db.execSQL(sql3);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.i("db", "update");
	}

}
