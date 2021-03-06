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
			showProgressDialog("?????????", "?????????");
			BmobQuery<TieziBean> query = new BmobQuery<TieziBean>();
			//query.addWhereEqualTo("name", ""+editText.getText());
			//??????playerName????????????????????????
			//query.addWhereEqualTo("playerName", "??????");
			//??????50??????????????????????????????????????????????????????10?????????
			query.setLimit(500);
			//??????????????????
			query.findObjects(new FindListener<TieziBean>() {
			    @Override
			    public void done(List<TieziBean> object, BmobException e) {
			        if(e==null){
			           // toast("??????????????????"+object.size()+"????????????");
			           
			               //??????playerName?????????
			            //   gameScore.getPlayerName();
			               //???????????????objectId??????
			            	//showTost("????????????");
			            	 dissProgressDialog();
			            	 tiezis=object;
			            	 Collections.reverse(tiezis);
			            	 for (int j = 0; j < tiezis.size(); j++) {
			            		 addTypeView(
			            				 tiezis.get(j).getTitle()
			            				 
			            				 , j,tiezis.get(j).getUrl());
							}
			            	
			            	
			           
			              
			            
			       
			        }else{
			        	 showTost("????????????");
			            Log.i("bmob","?????????"+e.getMessage()+","+e.getErrorCode());
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
