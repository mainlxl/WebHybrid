package com.mainli.hybrid.javajs;

import android.app.Activity;
import android.webkit.WebView;

import java.util.HashMap;

/**
 * Created by Mainli on 2016/8/25.
 */

public interface JSAction {
    //执行JS命令
    void executeJS(Activity activity, WebView webView, HashMap<String, String> param);

    //判断时候调用Js之前触发对应监听器
    boolean needJSBeforeRun(HashMap<String, String> param);
}
