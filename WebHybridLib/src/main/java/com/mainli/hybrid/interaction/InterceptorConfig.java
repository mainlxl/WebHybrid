package com.mainli.hybrid.interaction;

public class InterceptorConfig {
    //自定义请求协议头部
    public String mAgreementHead = "client://";
    //截取参数正则表达式
    public String mAgreementParamRegular = String.format("(?:%s|&)([^=^&]+)=([^&]*)", mAgreementHead);

    public InterceptorConfig() {

    }

    public InterceptorConfig(String agreementHead) {
        mAgreementHead = agreementHead;
    }

    public String getAgreementHead() {
        return mAgreementHead;
    }

    public void setAgreementHead(String agreementHead) {
        this.mAgreementHead = agreementHead;
    }

    public String getAgreementParamRegular() {
        return mAgreementParamRegular;
    }

    public void setAgreementParamRegular(String agreementParamRegular) {
        this.mAgreementParamRegular = agreementParamRegular;
    }
}