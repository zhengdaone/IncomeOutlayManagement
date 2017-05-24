package com.data;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.incomeoutlaymanagement.InOutDis;
import com.incomeoutlaymanagement.R;

/**
 * Created by zhengda on 2017/5/13.
 */
public class CheckVersionInfoTask extends Thread{
    private Context mContext;
    private Handler handler;
    public CheckVersionInfoTask(Context context, Handler handler) {
        this.mContext = context;
        this.handler=handler;
    }
    @Override
   public void run(){
        handler.sendEmptyMessage(0);
    }

}
