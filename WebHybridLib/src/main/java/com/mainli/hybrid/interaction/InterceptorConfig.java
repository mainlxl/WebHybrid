package com.mainli.hybrid.interaction;

/**
 * Created by Mainli on 2016/11/2.
 */
public class InterceptorConfig {
    //自定义请求协议头部
    public String agreementUrl = "client://";
    //截取参数正则表达式
    public String agreement_param = String.format("(?:%s|&)([^=^&]+)=([^&]*)", agreementUrl);


    public String getAgreementUrl() {
        return agreementUrl;
    }

    public void setAgreementUrl(String agreementUrl) {
        this.agreementUrl = agreementUrl;
    }

    public String getAgreement_param() {
        return agreement_param;
    }

    public void setAgreement_param(String agreement_param) {
        this.agreement_param = agreement_param;
    }

    @Override
    public String toString() {
        return "InteractionConfig{" +
                "agreementUrl='" + agreementUrl + '\'' +
                ", agreement_param='" + agreement_param + '\'' +
                '}';
    }
}
