package com.incomeoutlaymanagement;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.io.File;

public class User extends Fragment{

	//SharedPreferences sh;
	String path;
	public User() {
	}
	public User(String path) {
		this.path = path;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View cFragment=null;
		final SharedPreferences sh=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
		final String name=sh.getString("user", "null");
		if(name.equals("null")){
			cFragment = inflater.inflate(R.layout.user, container, false);
			TextView log=(TextView) cFragment.findViewById(R.id.logg);
			TextView reg=(TextView) cFragment.findViewById(R.id.regg);
			log.setOnClickListener(lr);
			reg.setOnClickListener(lr);
		}else{
			cFragment=inflater.inflate(R.layout.useron, container, false);
			TextView textView=(TextView) cFragment.findViewById(R.id.user);
			textView.setText(name);
			Button button=(Button) cFragment.findViewById(R.id.backup);
			button.setOnClickListener(listener);
			Button button2=(Button) cFragment.findViewById(R.id.recovery);
			button2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//xUtilsHttpUtilDonLoadFile("http://120.25.99.50/ADbackup/Download", Environment.getExternalStorageDirectory()+"/see.db");
					SharedPreferences sh2=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
					download(name,sh2.getString("pwd", "null"));
				}
			});
			TextView louout=(TextView) cFragment.findViewById(R.id.logout);
			louout.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					  if(sh!=null){
					        sh.edit().clear().commit();
					        Intent intent=new Intent();
					        intent.putExtra("show", "f4");
							intent.setClass(getActivity(), MainActivity.class);
							startActivity(intent);       
				}
				}
			});
		}
	bindview(cFragment);
		//inoutData(cFragment);
		//bindView(cFragment);
		return cFragment;
	}
	
	public OnClickListener listener=new OnClickListener() {		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			            //URL url = new URL("http://10.18.161.244:8080/ADbackup/Upload");
			SharedPreferences sh3=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
			String name=sh3.getString("user", "null");
			String pwd=sh3.getString("pwd", "null");
			        xUtilsUpLoadFile( "http://120.25.99.50:8080/ADbackup/Upload?user="+name+"&pwd="+pwd+"",path);
			        System.out.print(path);
		}
	};
	public  void  xUtilsUpLoadFile(String url,String filePath){
        //RequestParams锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
        RequestParams params = new RequestParams();
		//params.addBodyParameter("pwd",pass);
       // params.addBodyParameter("user",user);//锟斤拷锟斤拷锟斤拷一锟斤拷牟锟斤拷锟�
        params.addBodyParameter("file",new File(filePath));//锟斤拷锟斤拷锟斤拷锟斤拷氐锟斤拷洗锟斤拷募锟斤拷牟锟斤拷锟�
        //HttpUtils锟斤拷锟斤拷锟斤拷锟斤拷
        HttpUtils http = new HttpUtils();
        //锟斤拷锟斤拷锟斤拷锟斤拷
        /**
         * 锟斤拷一锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷式
         *锟节讹拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟街�
         *锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷携锟斤拷锟侥诧拷锟斤拷锟斤拷
         *锟斤拷锟侥革拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷募锟斤拷锟�
         */
        http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {
			ProgressDialog progressDialog;
			@Override
			public void onStart() {
				//tv.setText("锟斤拷锟斤拷锟斤拷锟斤拷");
				progressDialog= ProgressDialog.show(getActivity(), "wait...", "	upload...	", true);
			}
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //Log.i("a", "onSucceed:锟较达拷锟斤拷锟� "+responseInfo.result);
				progressDialog.dismiss();
				Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(HttpException e, String s) {
                Log.i("b", "onFailure: 锟较达拷失锟斤拷"+e.toString());
				progressDialog.dismiss();
				Toast.makeText(getActivity(), "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }
	
	/*public void xUtilsHttpUtilDonLoadFile(String downLoadUrl, String savePath) {
        //锟斤拷锟斤拷位锟斤拷
        //锟斤拷锟斤拷锟铰边碉拷File.separator原锟斤拷The system-dependent string used to separate components in filenames ('/').
        //锟斤拷实锟斤拷锟角达拷锟斤拷锟斤拷一锟斤拷斜锟斤拷
        final File filepath = new File(savePath);//锟斤拷锟斤拷锟斤拷路锟斤拷锟斤拷File锟斤拷锟斤拷
        if (!filepath.exists()) {
            filepath.mkdir();//锟斤拷锟铰凤拷锟斤拷锟斤拷锟斤拷诰锟斤拷却锟斤拷锟铰凤拷锟�
        }
        // 准锟斤拷锟斤拷锟斤拷锟斤拷Progress锟斤拷锟斤拷
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setCancelable(true);// 锟斤拷锟斤拷锟角凤拷锟斤拷锟酵拷锟斤拷锟斤拷Back锟斤拷取锟斤拷
        dialog.setTitle("锟斤拷锟斤拷锟斤拷");
        //Progress锟斤拷锟斤拷锟斤拷锟斤拷为水平锟斤拷锟斤拷锟斤拷
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 锟斤拷锟斤拷水平锟斤拷锟斤拷锟斤拷
        //httputils锟斤拷锟斤拷锟斤拷锟襟工撅拷
        HttpUtils http = new HttpUtils();
        //锟斤拷锟斤拷锟斤拷锟斤拷
        
             锟斤拷一锟斤拷锟斤拷锟斤拷downLoadUrl锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷位锟斤拷
             锟节讹拷锟斤拷锟斤拷锟斤拷filepath.getPath()锟斤拷锟斤拷锟截憋拷锟斤拷位锟斤拷
            锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟侥匡拷锟斤拷募锟斤拷锟斤拷冢锟斤拷锟斤拷锟轿达拷锟缴的诧拷锟街硷拷锟斤拷锟斤拷锟截★拷锟斤拷锟斤拷锟斤拷锟斤拷支锟斤拷RANGE时锟斤拷锟斤拷锟斤拷锟斤拷锟截★拷
            锟斤拷锟侥革拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷蠓祷锟斤拷锟较拷谢锟饺★拷锟斤拷募锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟缴猴拷锟皆讹拷锟斤拷锟斤拷锟斤拷锟斤拷
            锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷氐募锟斤拷锟�
         
        HttpHandler handler = http.download(downLoadUrl, filepath.getPath(), true, true, new RequestCallBack<File>() {
            @Override
            public void onStart() {
                dialog.show();//展示锟截闭碉拷锟斤拷
                Log.i("锟斤拷", "onStart: 锟斤拷始锟斤拷锟斤拷");
            }
            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                Log.i("锟斤拷锟斤拷锟斤拷", "onLoading:锟杰癸拷锟斤拷 " + total + "锟斤拷锟斤拷" + current);
                dialog.setMax((int) total);
                dialog.setProgress((int) current);
            }
            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                dialog.dismiss();//锟截闭碉拷锟斤拷
                //锟斤拷装锟斤拷锟截碉拷锟侥硷拷锟斤拷锟斤拷锟斤拷锟斤拷锟絘pk锟斤拷锟斤拷锟斤拷圆锟斤拷冒锟阶帮拷锟斤拷锟斤拷锟斤拷锟缴猴拷锟皆硷拷锟斤拷锟脚帮拷桑锟�
               // Intent intent = new Intent(Intent.ACTION_VIEW);
                //intent.setDataAndType(Uri.fromFile(new File(filepath.getPath().toString())), "application/vnd.android.package-archive");
                //startActivity(intent);
                //卸锟斤拷
                      Uri packageURI = Uri.parse("package:com.demo.CanavaCancel");//package:com.demo.CanavaCancel应锟矫的帮拷锟斤拷
                        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
                         startActivity(uninstallIntent);
                Log.i("锟斤拷锟�", "onStart: 锟斤拷锟斤拷锟斤拷锟�");
                Toast.makeText(getActivity(), "ss", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(HttpException error, String msg) {
                dialog.dismiss();//锟截闭碉拷锟斤拷
                Log.i("取锟斤拷", "onStart: 锟斤拷锟斤拷失锟斤拷" + error.toString());
                Toast.makeText(getActivity(), "ff", Toast.LENGTH_LONG).show();
            }
        });
    }*/
	 public void download(String user,String pwd) {
		 HttpUtils http = new HttpUtils();  
		 HttpHandler handler = http.download("http://120.25.99.50:8080/ADbackup/Download?user="+user+"&pwd="+pwd+"","/data/data/com.incomeoutlaymanagement/databases/db", true, false,
                 new RequestCallBack<File>() {
					 ProgressDialog progressDialog;
                     @SuppressWarnings("deprecation")  
                     @Override  
                     public void onStart() {  
                         //tv.setText("锟斤拷锟斤拷锟斤拷锟斤拷");  
						 progressDialog= ProgressDialog.show(getActivity(), "wait...", "	download...	", true);
                     }

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						progressDialog.dismiss();
						Toast.makeText(getActivity(), "Failure", Toast.LENGTH_LONG).show();
					}

					@Override
					public void onSuccess(ResponseInfo<File> arg0) {
						// TODO Auto-generated method stub
						progressDialog.dismiss();
						Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
					}});
		 }
	 public OnClickListener lr=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			if(v.getId()==R.id.regg) {
			intent.putExtra("lr", 0);
			}else if(v.getId()==R.id.logg){
				intent.putExtra("lr", 1);
			}
			intent.setClass(getActivity(), LogReg.class);
			startActivity(intent);
		}
	};
	public void bindview(View fra){
		TextView plan= (TextView) fra.findViewById(R.id.plan);
		plan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(getActivity(),PlanShow.class);
				startActivity(intent);
			}
		});
	}
}

                    /* @Override  
                     public void onLoading(long total, long current,  
                             boolean isUploading) {  
                         super.onLoading(total, current, isUploading);  
                         //btn_down.setText("锟斤拷锟斤拷锟斤拷锟斤拷");  
                         //download_pb.setProgress((int) ((double) current  
                                 / (double) total * 100));  
                        // tv.setText((int) (current * 100 / total) + "%");  
                     } */

                    // @Override  
                     /*public void onSuccess(ResponseInfo<File> responseInfo) {  
                         //tv.setText(responseInfo.result.getPath());  
                     }  
                     @Override  
                     public void onFailure(HttpException error, String msg) {  
                         //tv.setText(msg);  
                        // btn_down.setText("锟斤拷停<span style="font-family: Arial, Helvetica, sans-serif;">");</span>  
                     } 
                 }); */
