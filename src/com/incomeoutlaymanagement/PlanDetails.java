package com.incomeoutlaymanagement;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bean.Plan;
import com.data.Database;

/**
 * Created by zhengda on 2017/5/5.
 */
public class PlanDetails extends Activity{
    TextView detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plandetails);
        final Intent intent=getIntent();
        detail= (TextView) findViewById(R.id.detali);
        Database database = new Database(getBaseContext(), "db", null, 1);
        final Plan plan = new Plan(database);
        String comment=plan.queryPland(intent.getStringExtra("title"),intent.getStringExtra("time"));
        detail.setText(comment);
        ImageView back= (ImageView) findViewById(R.id.back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView del= (TextView) findViewById(R.id.del);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new AlertDialog.Builder(PlanDetails.this)
                        .setTitle("删除对话框")
                        .setIcon(R.drawable.ic_launcher)
                        .setMessage("确认删除吗？")
//相当于点击确认按钮
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                plan.delete(intent.getStringExtra("title"),intent.getStringExtra("time"));
                                setResult(0);
                                finish();
                            }
                        })
//相当于点击取消按钮
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
// TODO Auto-generated method stub

                            }
                        })
                        .create();
                dialog.show();
            }
        });
        ImageView addm= (ImageView) findViewById(R.id.addm);
        addm.setVisibility(View.INVISIBLE);
    }
}
