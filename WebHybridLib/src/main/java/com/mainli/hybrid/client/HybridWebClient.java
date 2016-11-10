package com.mainli.hybrid.client;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mainli.hybrid.interaction.Interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Mainli on 2016/11/2.
 */

public class HybridWebClient extends WebViewClient {
    Interceptor mWebHelper;

    public HybridWebClient(Interceptor webHelper) {
        mWebHelper = webHelper;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        try {
            String decode = URLDecoder.decode(url, "UTF-8");
            if (mWebHelper.isIntercept(decode)) mWebHelper.interceptURLDistribut(decode, view);//进行拦截
            else view.loadUrl(url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            view.loadUrl(url);
        }
        return true;
    }
}
