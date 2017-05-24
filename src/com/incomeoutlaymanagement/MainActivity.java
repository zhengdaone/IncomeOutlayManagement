package com.incomeoutlaymanagement;


import android.app.*;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.widget.*;
import com.data.CheckVersionInfoTask;
import com.data.Database;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity implements OnClickListener {

	private TextView menu;
	private TextView menu2;
	private TextView menu3;
	private TextView menu4;
	private AllList f3;
	private Note f1;
	private InOut f2;
	private User f4;
	private Database database;
	boolean flag=true;
	/*Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			Dialog dialog = new AlertDialog.Builder(MainActivity.this)
					.setTitle("update")
					.setIcon(R.drawable.ic_launcher)
					.setMessage("updating now?")
//�൱�ڵ��ȷ�ϰ�ť
					.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {

						}
					})
//�൱�ڵ��ȡ����ť
					.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
// TODO Auto-generated method stub

						}
					})
					.create();
			dialog.show();
			return;
		}
	};*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Dialog dialog = new AlertDialog.Builder(MainActivity.this)
				.setTitle("update")
				.setIcon(R.drawable.ic_launcher)
				.setMessage("updating now?")
//�൱�ڵ��ȷ�ϰ�ť
				.setPositiveButton("ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog1, int which) {
						final ProgressDialog dialog;
						//�̵߳ı�ʶ��
						//���������ֵ
						final int PROGRESS_MAX=100;
						dialog = new ProgressDialog(MainActivity.this);
						// ���ý���������ʽ
						dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// ������ʽ
						//�Ի��� ����ֹ�Ի���(�����Ϊtrue �����������ֵ��0)
						dialog.setIndeterminate(false);
						//ʧȥ�����ʱ�򣬲���ʧ�Ի���
						dialog.setCancelable(false);
						// ������Ϣ
						dialog.setMessage("updating...");
						// ���ñ���
						dialog.setTitle("update");
						// �������ܴ�С
						dialog.setMax(PROGRESS_MAX);
						// ��ʾ����
						dialog.show();
						// ���õ�ǰ�Ľ���
						dialog.setProgress(1); //Ҫ����show()����֮��,�����ȡ����progress��ֵ

						new Thread() { public void run() {
							while (flag) {
								try {
									// 400�����ý�����ˢ��
									Thread.sleep(100);
									//��ȡ��ǰ����
									int progress = dialog.getProgress();
									//��������
									progress++;  //Ҳ��������dialog.incrementProgressBy(5);
									//��������
									dialog.setProgress(progress);
									//�ж��Ƿ�ﵽ���ֵ
									if (dialog.getProgress() >= PROGRESS_MAX) {
										//��ʧ
										dialog.dismiss();
										//�̱߳�ʶ��
										flag=false;
									}

								} catch (InterruptedException e) {
									e.printStackTrace();
								}

							}
						};
						}.start();
					}
				})
//�൱�ڵ��ȡ����ť
				.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
// TODO Auto-generated method stub

					}
				})
				.create();
		dialog.show();
		//new CheckVersionInfoTask(MainActivity.this, handler).start();
		// requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		// getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
		bindView();
		//if(getIntent()!=null) {
			Intent intent = getIntent();
			if (intent.getStringExtra("show")!=null&&intent.getStringExtra("show").equals("f4")){
				show();
			}else{
				show2();
			}

	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		menu.add(Menu.NONE, Menu.FIRST + 1, 5, "ɾ��").setIcon(

				android.R.drawable.ic_menu_delete);

		// setIcon()����Ϊ�˵�����ͼ�꣬����ʹ�õ���ϵͳ�Դ���ͼ�꣬ͬѧ������һ��,��

		// android.R��ͷ����Դ��ϵͳ�ṩ�ģ������Լ��ṩ����Դ����R��ͷ��

		menu.add(Menu.NONE, Menu.FIRST + 2, 2, "����").setIcon(

				android.R.drawable.ic_menu_edit);

		menu.add(Menu.NONE, Menu.FIRST + 3, 6, "����").setIcon(

				android.R.drawable.ic_menu_help);

		menu.add(Menu.NONE, Menu.FIRST + 4, 1, "���").setIcon(

				android.R.drawable.ic_menu_add);

		menu.add(Menu.NONE, Menu.FIRST + 5, 4, "��ϸ").setIcon(

				android.R.drawable.ic_menu_info_details);

		menu.add(Menu.NONE, Menu.FIRST + 6, 3, "����").setIcon(

				android.R.drawable.ic_menu_send);

		return true;

	}*/

	private void bindView() {
		menu = (TextView) findViewById(R.id.menu1);
		menu2 = (TextView) findViewById(R.id.menu2);
		menu3 = (TextView) findViewById(R.id.menu3);
		menu4 = (TextView) findViewById(R.id.menu4);

		menu.setOnClickListener(this);
		menu2.setOnClickListener(this);
		menu3.setOnClickListener(this);
		menu4.setOnClickListener(this);

		ImageView add=(ImageView)findViewById(R.id.addm);
		add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				Intent intent2=getIntent();
				//intent.putExtra("payway", intent2.getStringExtra("payway"));
				intent.setClass(getBaseContext(), InOutPutAct.class);
				startActivity(intent);
			}
		});
	}

	public void selected() {
		menu.setSelected(false);
		menu2.setSelected(false);
		menu3.setSelected(false);
		menu4.setSelected(false);
	}

	public void hideAllFragment(FragmentTransaction transaction) {
		if (f1 != null) {
			transaction.hide(f1);
		}
		if (f2 != null) {
			transaction.hide(f2);
		}
		if (f3 != null) {
			transaction.hide(f3);
		}
		if (f4 != null) {
			transaction.hide(f4);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		hideAllFragment(transaction);
		WindowManager manager = this.getWindowManager();
		DisplayMetrics outMetrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(outMetrics);
		int width2 = outMetrics.widthPixels;
		int height2 = outMetrics.heightPixels;
		switch (v.getId()) {
		case R.id.menu1:
			selected();
			menu.setSelected(true);
			//if (f1 == null) {
				f1 = new Note();
				transaction.add(R.id.fragment_container, f1);
			/*} else {
				transaction.show(f1);
			}*/
			break;

		case R.id.menu2:
			database = new Database(getBaseContext(), "db", null, 1);
			selected();
			menu2.setSelected(true);
			//if (f2 == null) {
				f2 = new InOut(database);
				transaction.add(R.id.fragment_container, f2);
			/*} else {
				transaction.show(f2);
			}*/
			break;

		case R.id.menu3:
			selected();
			menu3.setSelected(true);
			//if (f3 == null) {
				f3 = new AllList();
				transaction.add(R.id.fragment_container, f3);
			/*} else {
				transaction.show(f3);
			}*/
			break;

		case R.id.menu4:
			selected();
			menu4.setSelected(true);
		//if (f4 == null) {
				//SharedPreferences sh=getSharedPreferences("user", MODE_PRIVATE);
				f4 = new User(getApplicationContext().getDatabasePath("db").getAbsolutePath());
			System.out.println(getApplicationContext().getDatabasePath("db").getAbsolutePath());
				transaction.add(R.id.fragment_container, f4);
			/*} else {
				transaction.show(f4);
			}*/
			break;
		}

		transaction.commit();
	}
	public void show(){
		selected();
		menu4.setSelected(true);
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		hideAllFragment(transaction);
		f4 = new User(getApplicationContext().getDatabasePath("db").getAbsolutePath());
		transaction.add(R.id.fragment_container, f4);
		transaction.commit();
	}
	public void show2(){
		selected();
		menu.setSelected(true);
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		hideAllFragment(transaction);
		f1 = new Note();
		transaction.add(R.id.fragment_container, f1);
		transaction.commit();
	}
}
