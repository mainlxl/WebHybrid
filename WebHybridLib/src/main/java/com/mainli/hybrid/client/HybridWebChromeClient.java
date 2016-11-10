package com.mainli.hybrid.client;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by Mainli on 2016/11/2.
 */

public class HybridWebChromeClient extends WebChromeClient {
    Interceptor mWebHelper;

    public HybridWebChromeClient(Interceptor webHelper) {
        mWebHelper = webHelper;
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        if (mWebHelper.isIntercept(message)) {//进行拦截
            mWebHelper.interceptURLDistribut(message, view);
            result.confirm("");
            return true;
        }
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }
}
