package com.incomeoutlaymanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.app.ProgressDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by zhengda on 2017/3/24.
 */
public class LogReg extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		if (intent.getIntExtra("lr", 110) == 0) {
			setContentView(R.layout.reg);
			Button button = (Button) findViewById(R.id.reg);
			button.setOnClickListener(reg);
			EditText user = (EditText) findViewById(R.id.txt_rusername);
			EditText pass = (EditText) findViewById(R.id.txt_rpassword);
			user.addTextChangedListener(new TextWatcher() {
				CharSequence temp;
				int editStart;
				int editEnd;
				String text;
				EditText mEditText=(EditText) findViewById(R.id.txt_rusername);
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {
					temp = s;
				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					text=s.toString();
					if(!text.matches("[0-9A-Za-z]*")){
						Toast.makeText(getBaseContext(),
								"only num and alpha！", Toast.LENGTH_SHORT)
								.show();
					}
				}

				@Override
				public void afterTextChanged(Editable s) {
					editStart = mEditText.getSelectionStart();
					editEnd = mEditText.getSelectionEnd();
					if (temp.length() > 10) {//限制长度
						Toast.makeText(getBaseContext(),
								"not exceed 20 words！", Toast.LENGTH_SHORT)
								.show();
						s.delete(editStart - 1, editEnd);
						int tempSelection = editStart;
						mEditText.setText(s);
						mEditText.setSelection(tempSelection);
					}
				}
			});
			pass.addTextChangedListener(new TextWatcher() {
				CharSequence temp;
				int editStart;
				int editEnd;
				String text;
				EditText mEditText=(EditText) findViewById(R.id.txt_rpassword);
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {
					temp = s;
				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					text=s.toString();
					if(!text.matches("[0-9A-Za-z]*")){
						Toast.makeText(getBaseContext(),
								"only num and alpha！", Toast.LENGTH_SHORT)
								.show();
					}
				}

				@Override
				public void afterTextChanged(Editable s) {
					editStart = mEditText.getSelectionStart();
					editEnd = mEditText.getSelectionEnd();
					if (temp.length() > 10) {//限制长度
						Toast.makeText(getBaseContext(),
								"not exceed 20 words！", Toast.LENGTH_SHORT)
								.show();
						s.delete(editStart - 1, editEnd);
						int tempSelection = editStart;
						mEditText.setText(s);
						mEditText.setSelection(tempSelection);
					}
				}
			});
		} else if (intent.getIntExtra("lr", 110) == 1) {
			setContentView(R.layout.log);
			Button button = (Button) findViewById(R.id.log);
			button.setOnClickListener(log);
			EditText user = (EditText) findViewById(R.id.txt_username);
			EditText pass = (EditText) findViewById(R.id.txt_password);
			user.addTextChangedListener(new TextWatcher() {
				CharSequence temp;
				int editStart;
				int editEnd;
				String text;
				EditText mEditText=(EditText) findViewById(R.id.txt_username);
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {
					temp = s;
				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					text=s.toString();
					if(!text.matches("[0-9A-Za-z]*")){
						Toast.makeText(getBaseContext(),
								"only num and alpha！", Toast.LENGTH_SHORT)
								.show();
					}
				}

				@Override
				public void afterTextChanged(Editable s) {
					editStart = mEditText.getSelectionStart();
					editEnd = mEditText.getSelectionEnd();
					if (temp.length() > 20) {//限制长度
						Toast.makeText(getBaseContext(),
								"not exceed 20 words！", Toast.LENGTH_SHORT)
								.show();
						s.delete(editStart - 1, editEnd);
						int tempSelection = editStart;
						mEditText.setText(s);
						mEditText.setSelection(tempSelection);
					}
				}
			});
			pass.addTextChangedListener(new TextWatcher() {
				CharSequence temp;
				int editStart;
				int editEnd;
				String text;
				EditText mEditText=(EditText) findViewById(R.id.txt_password);
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {
					temp = s;
				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					text=s.toString();
					if(!text.matches("[0-9A-Za-z]*")){
						Toast.makeText(getBaseContext(), "only num and alpha！", Toast.LENGTH_LONG)
								.show();
					}
				}

				@Override
				public void afterTextChanged(Editable s) {
					editStart = mEditText.getSelectionStart();
					editEnd = mEditText.getSelectionEnd();
					if (temp.length() > 20) {//限制长度
						Toast.makeText(getBaseContext(),
								"not exceed 20 words！！", Toast.LENGTH_SHORT)
								.show();
						s.delete(editStart - 1, editEnd);
						int tempSelection = editStart;
						mEditText.setText(s);
						mEditText.setSelection(tempSelection);
					}
				}
			});
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
			ImageView back= (ImageView) findViewById(R.id.back);
			back.setVisibility(View.VISIBLE);
			back.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}

	}

	public View.OnClickListener reg = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			EditText user = (EditText) findViewById(R.id.txt_rusername);
			EditText pass = (EditText) findViewById(R.id.txt_rpassword);
			xUtilsUpLoadFile("http://120.25.99.50:8080/ADbackup/Reg", user.getText().toString(), pass.getText().toString());
		}
	};

	public OnClickListener log = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EditText user = (EditText) findViewById(R.id.txt_username);
			EditText pass = (EditText) findViewById(R.id.txt_password);
			xUtilsUpLoadFile("http://120.25.99.50:8080/ADbackup/Log", user.getText().toString(), pass.getText().toString());
		}
	};

	public void xUtilsUpLoadFile(String url, final String user, final String pass) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("user", user);
		params.addBodyParameter("pass", pass);
		HttpUtils http = new HttpUtils();
		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {
			ProgressDialog progressDialog;
			@Override
			public void onStart() {
				progressDialog=ProgressDialog.show(LogReg.this, "wait...", "	log in...	", true);
			}
			/*public void onLoading(long total, long current, boolean isUploading) {
				  ProgressDialog.show(getBaseContext(), "请稍等...", "获取数据中...", true);

			}*/
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				progressDialog.dismiss();
				String result = responseInfo.result;
				if (result.equals("su")) {
					SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
					Editor editor = sharedPreferences.edit();
					editor.putString("user", user);
					editor.putString("pwd", pass);
					editor.commit();
					Intent intent = new Intent();
					intent.putExtra("show", "f4");
					intent.setClass(getBaseContext(), MainActivity.class);
					startActivity(intent);
					//finish();
				} else if (result.equals("fa")) {
					TextView textView = (TextView) findViewById(R.id.txt_regerror);
					textView.setVisibility(View.VISIBLE);
				} else if (result.equals("lsu")) {
					SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
					Editor editor = sharedPreferences.edit();
					editor.putString("user", user);
					editor.putString("pwd", pass);
					editor.commit();
					Intent intent = new Intent();
					intent.putExtra("show", "f4");
					intent.setClass(getBaseContext(), MainActivity.class);
					startActivity(intent);
				} else if (result.equals("lfa")) {
					TextView textView = (TextView) findViewById(R.id.txt_loginerror);
					textView.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onFailure(HttpException e, String s) {
				progressDialog.dismiss();
				Toast.makeText(getBaseContext(), "error", Toast.LENGTH_LONG).show();
			}
		});
	}


	/*TextWatcher textWatcher=new TextWatcher() {
			CharSequence temp;
			int editStart;
			int editEnd;
			String text;
			EditText mEditText;
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				temp = s;
				mEditText = s.toString();
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				text=s.toString();
				if(!text.matches("[0-9A-Za-z]*")){
					Toast.makeText(getBaseContext(),
							"只能输入数字和字母！", Toast.LENGTH_SHORT)
							.show();
				}
			}

			@Override
			public void afterTextChanged(Editable s) {
				editStart = mEditText.getSelectionStart();
				editEnd = mEditText.getSelectionEnd();
				if (temp.length() > 10) {//限制长度
					Toast.makeText(getBaseContext(),
							"输入的字数不超过10！", Toast.LENGTH_SHORT)
							.show();
					s.delete(editStart - 1, editEnd);
					int tempSelection = editStart;
					mEditText.setText(s);
					mEditText.setSelection(tempSelection);
				}
			}
		};*/
}

