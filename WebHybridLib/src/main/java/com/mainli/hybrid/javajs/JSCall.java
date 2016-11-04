package com.mainli.hybrid.javajs;

import android.os.Build;
import android.webkit.WebView;


/**
 * Created by Mainli on 2016/8/11.
 * JAVA调用JS
 */
public class JSCall {
    private static final String JAVA_SCRIPT = "javascript:%s(%s);";

    public static String obtainCallCMD(String functionName, String param) {
        return String.format(JAVA_SCRIPT, functionName, param);
    }

    public static void executeJavaScript(WebView webView, String functionName, String param) {
        String script1 = obtainCallCMD(functionName, param);
        System.out.println(script1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.evaluateJavascript(script1, null);
        } else webView.loadUrl(script1);
    }
}
