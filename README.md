web - android 混合开发

js调用java层两种方式:
1.直接在URL后面加上自定义协议
2.在js中使用Prompt提示框

java层调用

集成步骤:

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
        //开启Prompt提示框拦截
        webView.setWebChromeClient(new HybridWebChromeClient(interceptor));
        //开启URL拦截
        webView.setWebViewClient(new HybridWebClient(interceptor));
