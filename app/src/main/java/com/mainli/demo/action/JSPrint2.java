package com.mainli.demo.action;

import android.app.Activity;
import android.text.TextUtils;
import android.webkit.WebView;

import com.mainli.hybrid.javajs.JSAction;
import com.mainli.hybrid.javajs.JSCall;

import java.util.HashMap;

/**
 * Created by Mainli on 2016/11/2.
 */

public class JSPrint2 implements JSAction {
    @Override
    public void executeJS(Activity activity, WebView webView, HashMap<String, String> param) {
        System.out.println("Js传来参数" + param);
        String call = param.get("call");
        if (TextUtils.isEmpty(call)) call = "call";
        JSCall.executeJavaScript(webView, call, param.get("alert"));
    }

    @Override
    public boolean needJSBeforeRun(HashMap<String, String> param) {
        return false;
    }
}
