package com.mainli.hybrid.utils;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.mainli.hybrid.client.HybridWebChromeClient;
import com.mainli.hybrid.client.HybridWebClient;
import com.mainli.hybrid.interaction.Interceptor;

public class QuickConfigWebViewHelper {
    WebSettings mWebSettings;

    public QuickConfigWebViewHelper config(WebView webView, final Interceptor helper, final ProgressBar mProgressBar) {
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });//屏蔽掉长按事件 因为webview长按时将会调用系统的复制控件
        webView.setWebViewClient(new HybridWebClient(helper) {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });
        webView.setWebChromeClient(new HybridWebChromeClient(helper) {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
            }
        });
        settingConfig(webView);
        return this;
    }

    private void settingConfig(WebView webView) {
        webView.getResources().getConfiguration().fontScale = 1f;//字体不缩放
        //设置WebView属性，能够执行Javascript脚本
        mWebSettings = webView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        mWebSettings.setSupportZoom(true);
        //设置字符编码
        mWebSettings.setDefaultTextEncodingName("UTF-8");
        // 设置出现缩放工具
        mWebSettings.setBuiltInZoomControls(false);
        mWebSettings.setDomStorageEnabled(true);
        //扩大比例的缩放
        mWebSettings.setUseWideViewPort(true);
        //自适应屏幕
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebSettings.setLoadWithOverviewMode(true);
        //do not cache
        mWebSettings.setCacheMode(mWebSettings.LOAD_NO_CACHE);
    }

    public WebSettings getWebSettings() {
        return mWebSettings;
    }
}