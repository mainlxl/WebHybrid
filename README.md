#web - android 混合开发

####一、js调用java层两种方式:

    1.直接在URL后面加上自定义协议
    2.在js中使用Prompt提示框

####二、java层集成:

######1.集成步骤:
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
        }, new InterceptorConfig());//默认协议开头**client://**
        webView.setWebChromeClient(new HybridWebChromeClient(interceptor));
        webView.setWebViewClient(new HybridWebClient(interceptor));
######2.自定义JsAction:
        //实现JSAction接口  如:
        public class JSPrint1 implements JSAction {
            @Override
            public void executeJS(Activity activity, WebView webView, HashMap<String, String> param) {
                System.out.println("Js传来参数" + param);
                String call = param.get("cal");
                if (TextUtils.isEmpty(call)) call = "call";
                Toast.makeText(activity, call, Toast.LENGTH_SHORT).show();
                JSCall.executeJavaScript(webView, call, param.get("alert"));
            }
        
            @Override
            public boolean needJSBeforeRun(HashMap<String, String> param) {//判断是否需要执行JS回调之前时候执行一个回调 如获取用户信息前需要登录的操作
                return false;
            }
        }
        
####二、高级用法自定义协议头:
    默认协议为: **client://pram1="参数1"&pram2="参数2"&pram3="参数3"&pram4="参数4"......**
    修改协议头在实例化InterceptorConfig时传入
    InterceptorConfig默认两个参数:
    //自定义请求协议头部
    public String mAgreementHead = "client://";
    //截取参数正则表达式**建议不修改**
    public String mAgreementParamRegular = String.format("(?:%s|&)([^=^&]+)=([^&]*)", mAgreementHead);
    
    
    
    