package com.incomeoutlaymanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import static java.lang.Thread.sleep;

/**
 * Created by zhengda on 2017/5/16.
 */
public class LanuchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lanuch);
        //加载启动图片
        //Toast.makeText(getBaseContext(),"aa",Toast.LENGTH_LONG).show();
        //后台处理耗时任务
        new Thread(new Runnable() {
            @Override
            public void run() {
                        //跳转至 MainActivity
                        try {
                            sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(LanuchActivity.this, MainActivity.class);
                        startActivity(intent);
                        //结束当前的 Activity
                        LanuchActivity.this.finish();
                    }
                }).start();
    }
}
