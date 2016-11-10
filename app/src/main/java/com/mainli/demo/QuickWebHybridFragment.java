package com.mainli.demo;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.mainli.hybrid.R;
import com.mainli.hybrid.interaction.DistributManage;
import com.mainli.hybrid.interaction.Interceptor;
import com.mainli.hybrid.utils.AndroidBug5497Workaround;
import com.mainli.hybrid.utils.QuickConfigWebViewHelper;

/**
 * Created by Mainli on 2016/11/2.
 */

public class QuickWebHybridFragment extends Fragment {
    private static final String WEB_ACTION = "WebAction";
    Interceptor mInterceptor;
    WebView mWebview;

    public static QuickWebHybridFragment create(Activity activity, String url, @Nullable DistributManage manage) {
        QuickWebHybridFragment quickWebHybridFragment = new QuickWebHybridFragment();
        Bundle args = new Bundle();
        args.putString(WEB_ACTION, url);
        quickWebHybridFragment.setArguments(args);
        quickWebHybridFragment.setInterceptor(new Interceptor(activity, manage));
        return quickWebHybridFragment;
    }

    public void setInterceptor(Interceptor interceptor) {
        mInterceptor = interceptor;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidBug5497Workaround.assistActivity(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hybrid_web, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initWebView(view);
    }

    public void loadUrl(String url) {
        mWebview.loadUrl(url);//加载需要显示的网页
    }

    private void initWebView(View view) {
        mWebview = (WebView) view.findViewById(R.id.hybrid_webView);
        Bundle arguments = getArguments();
        String webAction = arguments.getString(WEB_ACTION);
        if (!TextUtils.isEmpty(webAction)) mWebview.loadUrl(webAction);
        //快速配置
        QuickConfigWebViewHelper config = new QuickConfigWebViewHelper().config(mWebview, mInterceptor, (ProgressBar) view.findViewById(R.id.hybrid_progressBar));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)//设置可以用Chrome调试
            WebView.setWebContentsDebuggingEnabled(true);
    }
}
