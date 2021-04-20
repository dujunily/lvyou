package com.bishe.me;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import com.bishe.me.bean.TieziBean;
import com.bumptech.glide.Glide;
import java.util.Collections;
import java.util.List;

public class ListActivity extends BaseActivity{
	
	
	LinearLayout linearLayout;
	public static List<TieziBean> tiezis;
	public static TieziBean tiezi;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_activity);
	
		linearLayout=(LinearLayout)findViewById(R.id.l333);
		linearLayout.removeAllViews();
		
//		findViewById(R.id.textView2).setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//
//			}
//		});
		
		denglu(linearLayout);
	}
	
	
	 private void addTypeView(final String name,int i,String url) {

	        View view = LayoutInflater.from(this).inflate(R.layout.texttiezi, null);
	        TextView tvName = (TextView) view.findViewById(R.id.b1);
	        ImageView touxiang=(ImageView)view.findViewById(R.id.s1);
	        tvName.setText(name);
	        Glide.with(this).load(url)
            
            .centerCrop()
            .into(touxiang);

	        
	        view.setTag(i);
	        view.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	            	
	            	int i=Integer.parseInt(
	            			
	            			view.getTag().toString());
	            	//showDialog(i);
	            	tiezi=tiezis.get(i);
	            	
	            	Activity2.into(ListActivity.this);
	               
	            }
	        });
	       
	        linearLayout.addView(view);


	    }
	 
	 public void denglu(View v) {
			
		 tiezis=null;
			showProgressDialog("查询中", "请稍后");
			BmobQuery<TieziBean> query = new BmobQuery<TieziBean>();
			//query.addWhereEqualTo("name", ""+editText.getText());
			//查询playerName叫“比目”的数据
			//query.addWhereEqualTo("playerName", "比目");
			//返回50条数据，如果不加上这条语句，默认返回10条数据
			query.setLimit(500);
			//执行查询方法
			query.findObjects(new FindListener<TieziBean>() {
			    @Override
			    public void done(List<TieziBean> object, BmobException e) {
			        if(e==null){
			           // toast("查询成功：共"+object.size()+"条数据。");
			           
			               //获得playerName的信息
			            //   gameScore.getPlayerName();
			               //获得数据的objectId信息
			            	//showTost("查询成功");
			            	 dissProgressDialog();
			            	 tiezis=object;
			            	 Collections.reverse(tiezis);
			            	 for (int j = 0; j < tiezis.size(); j++) {
			            		 addTypeView(
			            				 tiezis.get(j).getTitle()
			            				 
			            				 , j,tiezis.get(j).getUrl());
							}
			            	
			            	
			           
			              
			            
			       
			        }else{
			        	 showTost("查询失败");
			            Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
			            dissProgressDialog();
			        }
			        dissProgressDialog();
			    }
			
			});
			
		}

	 
	 public static void into(Context context){
	        Intent intent =new Intent(context,ListActivity.class);
	       
	        context.startActivity(intent);
	    }
	 
	 @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
	}

}
