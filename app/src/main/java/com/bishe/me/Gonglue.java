package com.bishe.me;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.bishe.me.bean.Raider;

public class Gonglue extends BaseActivity {


    WebView webView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gong_lue_activity);
        webView= (WebView) this.findViewById(R.id.webview);
        progressBar= (ProgressBar) this.findViewById(R.id.pg);


        Raider foodBean=(Raider)getIntent().getSerializableExtra("gonglue");
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        webView.getSettings().setJavaScriptEnabled(true); //支持JavaScript参数
        webView.getSettings().setSupportZoom(true);  //支持放大缩小
        webView.getSettings().setBuiltInZoomControls(true); //显示缩放按钮

        webView.loadUrl(foodBean.getUrl());

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
