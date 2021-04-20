package com.bishe.me;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.bishe.me.bean.User;
import java.util.List;
import org.litepal.LitePal;

public class zhuceActivity extends BaseActivity{
	
	EditText editText;//用户名
	EditText editText2;//密码
	String pass;//密码
	String name;
	
	RadioGroup radioGroup;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhuce_activity);
		
		 editText=(EditText)findViewById(R.id.editText1);
		 editText2=(EditText)findViewById(R.id.editText2);
		 
		 radioGroup=(RadioGroup)findViewById(R.id.rgStorageWay);
		 
		 
		 radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { 

		     @Override 
		     public void onCheckedChanged(RadioGroup group, int checkedId) { 
		       
		       
		       RadioButton  checkRadioButton = (RadioButton)  radioGroup.
                       findViewById(checkedId);  
		       
		       showTost(checkRadioButton.getText()+"");
		        }
		     });
		 }
	
		
	
	
	
	public void zhuce(View v) {

		if(!TextUtils.isEmpty(editText.getText())||!TextUtils.isEmpty(editText2.getText())){
            List<User> customerList = LitePal.where("userName=?", editText.getText().toString()).find(User.class);

            if (customerList.size() > 0) {
                Toast.makeText(zhuceActivity.this,"该用户名已经注册",Toast.LENGTH_LONG).show();
                return ;
            }

			User user=new User();
			user.setPassword(editText2.getText().toString());
			user.setUserName(editText.getText().toString());
			user.save();

			if(user.save()){
				finish();
				Toast.makeText(zhuceActivity.this,"注册成功",Toast.LENGTH_LONG).show();

			}else {
				Toast.makeText(zhuceActivity.this,"注册失败",Toast.LENGTH_LONG).show();
			}

		}else {
			Toast.makeText(zhuceActivity.this,"输入用户名和密码再注册",Toast.LENGTH_LONG).show();

		}


	}
	 
	 
	 
	 public static void into(Context context){
	        Intent intent =new Intent(context,zhuceActivity.class);
	       
	        context.startActivity(intent);
	    }

}
