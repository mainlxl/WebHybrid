package com.mainli.demo.action;

import android.app.Activity;
import android.text.TextUtils;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.mainli.demo.response.RootResponse;
import com.mainli.demo.response.WebResultState;
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
        RootResponse<Object> objectRootResponse = new RootResponse<>(WebResultState.OK);
        objectRootResponse.setResult(param.get("alert"));
        JSCall.executeJavaScript(webView, call, new Gson().toJson(objectRootResponse));
    }

    @Override
    public boolean needJSBeforeRun(HashMap<String, String> param) {
        return false;
    }
}
