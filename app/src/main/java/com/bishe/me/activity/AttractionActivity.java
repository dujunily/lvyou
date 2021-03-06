package com.bishe.me.activity;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bishe.me.BaseActivity;
import com.bishe.me.R;
import com.bishe.me.SPUtils;
import com.bishe.me.bean.Attraction;
import com.bishe.me.bean.Bill;
import com.bishe.me.bean.User;
import com.bishe.me.util.DrawableUtil;
import com.bishe.me.util.GlideImageLoader;
import com.bishe.me.util.StringUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.litepal.LitePal;

/**
 * @author luoxin
 * @since 2021-04-12 22:06
 */

public class AttractionActivity extends BaseActivity {

    @BindView(R.id.tv_page_title)
    TextView tvPageTitle;
    @BindView(R.id.img_operate)
    ImageView imgOperate;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_attraction_content)
    TextView tvAttractionContent;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.et_send_num)
    EditText etSendNum;

    Attraction attraction;

    int num = 1;

    List<Integer> imgs = new ArrayList<>();


    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.attraction_activity);
        ButterKnife.bind(this);

        // ????????????????????????
        if (isSetStatusBar) {
            steepStatusBar();
        }

        tvPageTitle.setText("????????????");
        imgOperate.setVisibility(View.GONE);

        etSendNum.setEnabled(false);

        initData();
        initView();
    }

    private void initView() {
        if (attraction != null) {
            tvPageTitle.setText(attraction.getTitle());

            tvTitle.setText(attraction.getTitle());
            tvAttractionContent.setText(attraction.getContent());
            tvPrice.setText(attraction.getPrice() == 0 ? "0???/???" : (attraction.getPrice() + "???/???"));
            tvTotal.setText(attraction.getPrice() == 0 ? "0???" : (attraction.getPrice() * num + "???"));

            String[] imgList = attraction.getImgRes().split(",");
            for (String s : imgList) {
                int drawableByName = DrawableUtil.getDrawableByName(s);
                imgs.add(drawableByName);
            }

            etSendNum.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    // xml ??????????????????????????????9???
                    if (count >= before && start == 5) {
                        showTost("???????????????????????????9 ????????????????????????????????????????????????");
                    }

                    etSendNum.setSelection(s.length());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        //??????banner??????
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //?????????????????????
        banner.setImageLoader(new GlideImageLoader());
        //??????????????????
        banner.setImages(imgs);
        //??????banner????????????
        banner.setBannerAnimation(Transformer.Default);
        //??????????????????????????????true
        banner.isAutoPlay(true);
        //??????????????????
        banner.setDelayTime(1500);
        //???????????????????????????banner???????????????????????????
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner?????????????????????????????????????????????
        banner.start();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            attraction = (Attraction) bundle.getSerializable("data");
        }
    }

    public static void startToAttractionActivity(Context context, Attraction attraction) {
        Intent intent = new Intent(context, AttractionActivity.class);
        Bundle bundle = new Bundle();
        if (attraction != null) {
            bundle.putSerializable("data", attraction);
        }
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @OnClick({R.id.btn_add_attraction,R.id.img_num_reduce, R.id.img_num_add})
    public void onClick(@NonNull View view) {
        switch (view.getId()) {
            case R.id.img_num_add:
                updateCount(1);
                break;
            case R.id.img_num_reduce:
                updateCount(-1);
                break;
            case R.id.btn_add_attraction:
                Builder builder = new Builder(this);
                builder.setTitle("??????????????????")
                    .setMessage("????????????????????????" + attraction.getTitle() + "????????????" + num + "???????????? " + tvTotal.getText().toString() +"???")
                    .setPositiveButton("??????",
                        (dialog, which) -> {
                            Bill order = new Bill();
                            List<User> userList = LitePal.where("userName = ?", SPUtils.getUserName(AttractionActivity.this)).limit(1)
                                .find(User.class);
                            order.setAuthorID(userList.get(0).getId());
                            order.setAuthorName(userList.get(0).getUserName());
                            order.setOrderCode(StringUtil.getOrderCode());
                            order.setAttractionName(attraction.getTitle());
                            order.setImg(attraction.getFirstImg());
                            order.setAttractionID(attraction.getId());
                            order.setAttractionContent(attraction.getContent());
                            order.setPrice(attraction.getPrice());
                            order.setStatus(2);
                            order.setNum(num);
                            order.setShould(attraction.getPrice() * num);
                            order.setPaid(attraction.getPrice() * num);
                            order.setCreateTime(simpleDateFormat.format(new Date()));
                            order.save();
                        }).setNegativeButton("??????", null).setNeutralButton("????????????",
                    (dialog, which) -> {
                        Bill order = new Bill();
                        List<User> userList = LitePal.where("userName = ?", SPUtils.getUserName(AttractionActivity.this)).limit(1)
                            .find(User.class);
                        order.setAuthorID(userList.get(0).getId());
                        order.setOrderCode(StringUtil.getOrderCode());
                        order.setAuthorName(userList.get(0).getUserName());
                        order.setAttractionName(attraction.getTitle());
                        order.setImg(attraction.getFirstImg());
                        order.setAttractionID(attraction.getId());
                        order.setStatus(1);
                        order.setNum(num);
                        order.setShould(attraction.getPrice() * num);
                        order.setCreateTime(simpleDateFormat.format(new Date()));
                        order.setPaid(0);
                        order.save();
                    })
                    .show();
                break;
        }
    }

    private void updateCount(int step) {
        num = Integer.parseInt(etSendNum.getText().toString().trim());
        num += step;
        if (num < 1) {
            showTost("????????????????????????1");
            return;
        }if (num > 9){
            showTost("???????????????????????????9????????????????????????????????????????????????");
            return;
        }
        etSendNum.setText(String.format("%d", num));

        tvTotal.setText(attraction.getPrice() == 0 ? "0???" : (attraction.getPrice() * num + "???"));
    }
}
