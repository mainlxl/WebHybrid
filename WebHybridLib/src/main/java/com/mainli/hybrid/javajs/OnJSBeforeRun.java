package com.mainli.hybrid.javajs;

import android.app.Activity;
import android.webkit.WebView;

import java.util.HashMap;

/**
 * Created by Mainli on 2016/8/25.
 */

public interface OnJSBeforeRun {
    void onJSRunBefore(Activity activity, WebView view, HashMap<String, String> param);
}
