package com.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by zhengda on 2017/4/29.
 */
public class TimeBuilder {
    private  String time;
    public void setTime(String time){
        this.time=time;
    }
    public TimeBuilder(String time){
        this.time=time;
    }
    public  String changeTimen(){
        int day=0,month=0,year=0;
        SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH)+1;
        day=calendar.get(Calendar.DAY_OF_MONTH)+1;
        if((month==1||month==3||month==5||month==7||month==8||month==10||month==12)&&day>31){
            month+=1;
            day=1;
        }else if((month==4||month==6||month==9||month==11)&&day>30){
            month+=1;
            day=1;
        }else if (month==2){
            if((year%100==0||year%4==0)&&day>29){
                month+=1;
                day=1;
            }else if(day>28){
                month+=1;
                day=1;
            }
        }
        if(month>12){
            month=1;
            year+=1;
        }
        StringBuilder t=new StringBuilder()
                .append(year)
                .append("-")
                .append((month+ 1) < 10 ? "0"
                        + (month) : (month ))
                .append("-")
                .append((day < 10) ? "0" + day : day);
        System.out.println(t.toString());
        return t.toString();
    }
    public  String changeTimep(){
        int day=0,month=0,year=0;
        SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH)+1;
        day=calendar.get(Calendar.DAY_OF_MONTH)-1;
        //System.out.println(year+" "+month+" "+day);
        if(day<1) {
            month-=1;
            if(month<1){
                month=12;
                year-=1;
            }
            if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)) {
                //month += 1;
                day = 31;
            } else if ((month == 4 || month == 6 || month == 9 || month == 11)) {
                //month += 1;
                day = 30;
            } else if (month == 2) {
                if ((year % 100 == 0 || year % 4 == 0) ) {
                    //month += 1;
                    day = 29;
                } else {
                    //month += 1;
                    day = 28;
                }
            }
        }
        StringBuilder t=new StringBuilder()
                .append(year)
                .append("-")
                .append((month+ 1) < 10 ? "0"
                        + (month) : (month ))
                .append("-")
                .append((day < 10) ? "0" + day : day);
        return t.toString();
    }
}
