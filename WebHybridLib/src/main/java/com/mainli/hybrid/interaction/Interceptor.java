package com.mainli.hybrid.interaction;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.webkit.WebView;

import com.mainli.hybrid.javajs.JSAction;
import com.mainli.hybrid.javajs.OnJSBeforeRun;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mainli on 2016/8/11.
 * 拦截器
 */
public final class Interceptor {
    private DistributManage mListener;
    private OnJSBeforeRun mJSBeforeRun;
    private Activity mActivity;
    private InterceptorConfig mConfig;

    public Interceptor(Activity activity, @NonNull DistributManage listener) {
        this(activity, listener, null, new InterceptorConfig());
    }

    public Interceptor(Activity activity, @NonNull DistributManage listener, InterceptorConfig config) {
        this(activity, listener, null, config);
    }


    public Interceptor(Activity activity, @NonNull DistributManage listener, OnJSBeforeRun jSBeforeRun, InterceptorConfig config) {
        this.mListener = listener;
        this.mActivity = activity;
        this.mJSBeforeRun = jSBeforeRun;
        this.mConfig = config;
    }

    /**
     * 拦截URL信息,执行分发
     */
    public void interceptURLDistribut(String url, WebView view) {
        HashMap<String, String> param = getParam4URL(url);
        JSAction jsAction = mListener.onIntercept(param);
        if (jsAction != null) {
            if (mJSBeforeRun != null && jsAction.needJSBeforeRun(param))
                mJSBeforeRun.onJSRunBefore(mActivity, view, param);
            jsAction.executeJS(mActivity, view, param);
        } else {
            Log.e("WebHyBrid", "Not found Action - " + url);
        }
    }

    //截取URL上参数
    private HashMap<String, String> getParam4URL(String url) {
        Matcher matcher = Pattern.compile(this.mConfig.getAgreementParamRegular()).matcher(url);
        HashMap<String, String> param = new HashMap<>();
        while (matcher.find()) param.put(matcher.group(1), matcher.group(2));
        return param;
    }

    //判断是否拦截
    public boolean isIntercept(String url) {
        return Pattern.compile(this.mConfig.getAgreementHead()).matcher(url).find();
    }
}
