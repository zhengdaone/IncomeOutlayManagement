package com.bean;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.data.Database;

import java.text.SimpleDateFormat;

/**
 * Created by zhengda on 2017/5/1.
 */
public class Plan {
    private String title;
    private String comment;
    private String time;
    private Database database;

    public Plan(Database database) {
        this.database = database;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public void insert(String title,String comment,String time){
        SQLiteDatabase write=database.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("title",title);
        contentValues.put("plan",comment);
        contentValues.put("time",time);
        write.insert("plan",null,contentValues);
    }

    public void delete(String title,String time){
        SQLiteDatabase write=database.getWritableDatabase();
        write.delete("plan","title=? and time=? ",new String[]{title,time});
    }

    public Cursor queryPlan(String time){
        SQLiteDatabase wr=database.getWritableDatabase();
        Cursor cursor;
        //Cursor cursor=wr.query("inout",null, "owner=?", new String[]{sort}, null, null, null);
        String sql;
        if(time==null){
            //SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            //time=simpleDateFormat.format(System.currentTimeMillis());
            sql="select * from plan limit 0,10 ";
            cursor=wr.rawQuery(sql, null);
        }else {
            sql="select * from plan where time=?";
            cursor = wr.rawQuery(sql, new String[]{time});
        }
        return cursor;
    }
    public String queryPland(String title,String time){
        SQLiteDatabase wr=database.getWritableDatabase();
        Cursor cursor;
        String plan=null;
        //Cursor cursor=wr.query("inout",null, "owner=?", new String[]{sort}, null, null, null);
        String sql ="select plan from plan where title=? and time=?";
        cursor = wr.rawQuery(sql, new String[]{title,time});
        while (cursor.moveToNext()){
            plan=cursor.getString(cursor.getColumnIndex("plan"));
        }
        return plan;
    }
}
