package com.bishe.me;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bishe.me.bean.Raider;

public class GongLueDetail extends BaseActivity {

    TextView tvtitle;
    ImageView ivPic;
    TextView tvDes;
    TextView tvTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gonglue_detail);

        tvtitle = (TextView) this.findViewById(R.id.title);
         tvDes= (TextView) this.findViewById(R.id.des);
        tvTime= this.findViewById(R.id.tv_time);

        Raider foodBean=(Raider)getIntent().getSerializableExtra("gonglue");
        tvtitle.setText(foodBean.getName());
        tvDes.setText(foodBean.getContent());
        tvTime.setText(foodBean.getTime());
    }
}
