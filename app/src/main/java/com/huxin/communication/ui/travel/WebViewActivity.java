package com.huxin.communication.ui.travel;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;

public class WebViewActivity extends BaseActivity {
    private WebView mWebView;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_web_view);

    }

    @Override
    protected void initViews() {
        mWebView = (WebView) findViewById(R.id.webView);
        url = getIntent().getStringExtra("url");

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);  //设置运行使用JS
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // 设置可以访问文件
        settings.setAllowFileAccess(true);
        //如果访问的页面中有Javascript，则webview必须设置支持Javascript
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        mWebView.loadUrl(url);
//        mWebView.loadUrl(url);
        mWebView.getSettings().setTextZoom(100);
    }

}
