package com.bishe.me;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import com.bishe.me.bean.TieziBean;
import com.bishe.me.user.LoginActivity;
import com.bishe.me.util.StringUtil;

public class Activity3 extends BaseActivity{
	
	LinearLayout linearLayout;
	 EditText editText,editText2;
	 public static ImageView imageView;
	 public static String url="";
	 private TieziBean mTieziBean;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity3);
		
		linearLayout=(LinearLayout)findViewById(R.id.l333);
		linearLayout.removeAllViews();
		url="";
		mTieziBean=new TieziBean();
		editText=(EditText)findViewById(R.id.editText1);
		editText2=(EditText)findViewById(R.id.editText2);
		imageView=(ImageView)findViewById(R.id.imageView1);
		TextView textView=(TextView)findViewById(R.id.textView1);
		
		
		imageView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ActivityChooseBitmap.into(Activity3.this);

			}
		});
		textView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (url.length()<1) {
					showTost("请选择正确图片");
					return;
					
				}
				
				mTieziBean.setTitle(editText.getText().toString());
				mTieziBean.setNeirong(editText2.getText().toString());
				
				mTieziBean.setUrl(url);
				mTieziBean.setTime(StringUtil.getTimeAll());
				
				mTieziBean.setUser(LoginActivity.user);
				
				showProgressDialog("", "");
				
				mTieziBean.save(new SaveListener<String>() {
				     @Override
				     public void done(String objectId,BmobException e) {
				         if(e==null){
				             showTost("发帖成功");
				             dissProgressDialog();
				             finish();
				         }else{
				        	 showTost("发帖失败：" + e.getMessage());
				        	  dissProgressDialog();
				         }
				     }

				
				 });
				
				
			}
		});
		
		
	}
	
	
	
	 
	 public static void into(Context context){
	        Intent intent =new Intent(context,Activity3.class);
	       
	        context.startActivity(intent);
	    }

}
