package com.weisj.pj.base.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.weisj.pj.R;


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
