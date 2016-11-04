package com.mainli.demo;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.mainli.demo.action.JSPrint1;
import com.mainli.demo.action.JSPrint2;
import com.mainli.hybrid.client.HybridWebClient;
import com.mainli.hybrid.client.HybridWebChromeClient;
import com.mainli.hybrid.interaction.DistributManage;
import com.mainli.hybrid.interaction.Interceptor;
import com.mainli.hybrid.interaction.InterceptorConfig;
import com.mainli.hybrid.javajs.JSAction;
import com.mainli.hybrid.utils.AndroidBug5497Workaround;

import java.util.HashMap;

/**
 * Created by Mainli on 2016/11/2.
 */

public class WebActivity extends AppCompatActivity implements DistributManage {
    private static final String WEB_ACTION = "WebAction";
    Interceptor mInterceptor;
    WebView mWebview;
    ProgressBar mProgressBar;

    public void setInterceptor(Interceptor interceptor) {
        mInterceptor = interceptor;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hybrid_web);
        AndroidBug5497Workaround.assistActivity(this);//解决键盘遮盖问题
        initWebView();


        WebView webView = null;

        //设置WebView属性，能够执行Javascript脚本
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //实例化拦截器 并加入分发管理器
        Interceptor interceptor = new Interceptor(this, new DistributManage() {
            @Override
            public JSAction onIntercept(HashMap<String, String> param) {
                String name = param.get("name");
                JSAction jsAction = null;
                switch (name) {
                    case "按钮1":
                        jsAction = new JSPrint1();
                        break;
                    case "按钮2":
                        jsAction = new JSPrint2();
                        break;
                }
                return jsAction;
            }
        }, new InterceptorConfig());
        webView.setWebChromeClient(new HybridWebChromeClient(interceptor));
        webView.setWebViewClient(new HybridWebClient(interceptor));
    }


    @Override
    public JSAction onIntercept(HashMap<String, String> param) {
        String name = param.get("name");
        JSAction jsAction = null;
        switch (name) {
            case "按钮1":
                jsAction = new JSPrint1();
                break;
            case "按钮2":
                jsAction = new JSPrint2();
                break;
            case "按钮3":
                jsAction = new JSPrint1();
                break;
            case "按钮4":
                jsAction = new JSPrint2();
                break;
        }
        return jsAction;
    }

    private void initWebView() {
        mWebview = (WebView) findViewById(com.mainli.demo.R.id.hybrid_webView);
        mProgressBar = (ProgressBar) findViewById(R.id.hybrid_progressBar);

        String webAction = getIntent().getStringExtra(WEB_ACTION);
        if (TextUtils.isEmpty(webAction)) webAction = "file:///android_asset/simple.html";
        mWebview.loadUrl(webAction);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)//设置可以用Chrome调试
            WebView.setWebContentsDebuggingEnabled(true);
        //配置请求头
        //设置分发管理器
        config(new Interceptor(this, this, new InterceptorConfig()));
    }


    public void config(Interceptor helper) {
        mWebview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });//屏蔽掉长按事件 因为webview长按时将会调用系统的复制控件
        mWebview.setWebViewClient(new HybridWebClient(helper) {
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
        mWebview.setWebChromeClient(new HybridWebChromeClient(helper) {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
            }
        });
        settingConfig(mWebview);
    }

    private void settingConfig(WebView webView) {
        webView.getResources().getConfiguration().fontScale = 1f;//字体不缩放
        //设置WebView属性，能够执行Javascript脚本
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        settings.setSupportZoom(true);
        //设置字符编码
        settings.setDefaultTextEncodingName("UTF-8");
        // 设置出现缩放工具
        settings.setBuiltInZoomControls(false);
        settings.setDomStorageEnabled(true);
        //扩大比例的缩放
        settings.setUseWideViewPort(true);
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        //do not cache
        settings.setCacheMode(settings.LOAD_NO_CACHE);
    }


}
