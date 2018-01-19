package com.weisj.pj.base.activity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.weisj.pj.R;
import com.weisj.pj.base.BaseActivity;
import com.weisj.pj.bean.ShareData;
import com.weisj.pj.utils.ImageLoaderUtils;
import com.weisj.pj.utils.PersonMessagePreferencesUtils;
import com.weisj.pj.view.dialog.ShareViewDialog;


public class WebViewActivity extends Activity {
    private WebView webView;
    private String imageUrl;
    private String url;
    private String title;
    private String content;
    private int id;

    class myWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.contains("goods_id")) {
                String goodId = url.split("goods_id=", 2)[1];
                if (goodId.contains("&")) {
                    goodId = goodId.split("&", 2)[0];
                }
                Intent intent = new Intent(WebViewActivity.this, GoodDetailActivity.class);
                intent.putExtra("goodId", Integer.valueOf(goodId));
                WebViewActivity.this.startActivity(intent);
            } else if (url.contains("/shop/goods/view/") && !url.contains("fx=1")) {
                if (url.contains("?")) {
                    url = url + "&fx=1";
                } else {
                    url = url + "?fx=1";
                }
                webView.loadUrl(url);
            } else {
                return false;
            }
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        load();
    }





    private void initView() {

        webView = (WebView)findViewById(R.id.webView);
    }


    class myWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            // TODO Auto-generated method stub
            if (newProgress == 100) {
//                showLoadFinish();?showLoadFinish
            } else {
//                showLoading();
            }
            super.onProgressChanged(view, newProgress);
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        webView.stopLoading();
    }


    private void load() {
        // TODO Auto-generated method stub
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new myWebViewClient());
        webView.setWebChromeClient(new myWebChromeClient());

        // 页面适应屏幕
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptEnabled(true);
        String oldUrl = getIntent().getStringExtra("url");
        if (oldUrl != null) {
            if (!oldUrl.contains("http://") && !oldUrl.contains("https://")) {
                oldUrl = "http://" + oldUrl;
            }
            url = oldUrl;
            if (oldUrl.contains("/shop/goods/view/") && !oldUrl.contains("fx=1")) {
                if (oldUrl.contains("?")) {
                    oldUrl = oldUrl + "&fx=1";
                } else {
                    oldUrl = oldUrl + "?fx=1";
                }
            }
            webView.loadUrl(oldUrl);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


}
