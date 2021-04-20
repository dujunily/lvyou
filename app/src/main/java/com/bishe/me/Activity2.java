package com.bishe.me;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import com.bishe.me.bean.PinglunBean;
import com.bishe.me.bean.TieziBean;
import com.bishe.me.user.LoginActivity;
import com.bumptech.glide.Glide;
import java.util.List;

public class Activity2 extends BaseActivity {


    LinearLayout linearLayout;
    List<PinglunBean> pinglunBeans;

    TextView t1, t2, t3;


    EditText editTexts;

    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        String s = getIntent().getStringExtra("name");
        editTexts = (EditText) findViewById(R.id.editText1);

        t1 = (TextView) findViewById(R.id.textView1);
        t2 = (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView3);
        button1 = (Button) findViewById(R.id.button1);
        ImageView imageView = (ImageView) findViewById(R.id.img11);

        linearLayout = (LinearLayout) findViewById(R.id.l3334);

        TieziBean tieziBean = ListActivity.tiezi;
        t1.setText(tieziBean.getTitle());

        t2.setText(tieziBean.getUser() + "    \n\n\n" + tieziBean.getTime());

        t3.setText(tieziBean.getNeirong());

        Glide.with(this).load(tieziBean.getUrl())

            .centerCrop()
            .into(imageView);

        linearLayout = (LinearLayout) findViewById(R.id.l3334);

        //linearLayout.removeAllViews();

        denglu();

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                PinglunBean mPinglunBean = new PinglunBean();
                mPinglunBean.setUser(LoginActivity.user);
                mPinglunBean.setName(editTexts.getText().toString());
                mPinglunBean.setLike(ListActivity.tiezi.getCreatedAt());

                showProgressDialog("评论中", "请稍后");
                mPinglunBean.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId, BmobException e) {
                        if (e == null) {
                            showTost("评论成功");
                            dissProgressDialog();
                            linearLayout.removeAllViews();
                            denglu();

                        } else {
                            showTost("评论失败：" + e.getMessage());
                            dissProgressDialog();
                        }
                    }


                });

                //addTypeView(LoginActivity.user+"\n"+editTexts.getText().toString(),"2");
                //LoginActivity.pingluns.add(bookBean.title+"\n"+StringUtil.getTimeAll()+"\n"+StringUtil.getTimeAll()+"\n"+editTexts.getText().toString());
            }
        });
    }


    private void addTypeView(String name, String s) {

        View view = LayoutInflater.from(this).inflate(R.layout.item_text, null);
        TextView tvName = (TextView) view.findViewById(R.id.t1);
        tvName.setText(name + "");

        view.setTag(s);
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //xijshowTost(v.getTag()+"");

            }
        });

        linearLayout.addView(view);


    }


    public void denglu() {

        pinglunBeans = null;
        showProgressDialog("查询中", "请稍后");
        BmobQuery<PinglunBean> query = new BmobQuery<PinglunBean>();
        query.addWhereEqualTo("like", "" + ListActivity.tiezi.getCreatedAt());
        //查询playerName叫“比目”的数据
        //query.addWhereEqualTo("playerName", "比目");
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(500);
        //执行查询方法
        query.findObjects(new FindListener<PinglunBean>() {
            @Override
            public void done(List<PinglunBean> object, BmobException e) {
                if (e == null) {
                    // toast("查询成功：共"+object.size()+"条数据。");

                    //获得playerName的信息
                    //   gameScore.getPlayerName();
                    //获得数据的objectId信息
                    //showTost("查询成功");
                    dissProgressDialog();
                    pinglunBeans = object;

                    for (int j = 0; j < pinglunBeans.size(); j++) {
                        addTypeView(
                            pinglunBeans.get(j).getUser() + "\n" + pinglunBeans.get(j)
                                .getCreatedAt() + "\n" + pinglunBeans.get(j).getName()

                            , "");
                    }


                } else {
                    showTost("查询失败");
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                    dissProgressDialog();
                }
                dissProgressDialog();
            }

        });

    }


    public static void into(Context context) {
        Intent intent = new Intent(context, Activity2.class);
        //

        context.startActivity(intent);
    }

}
