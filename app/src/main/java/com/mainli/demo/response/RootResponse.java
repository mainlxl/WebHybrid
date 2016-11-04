package com.mainli.demo.response;

/**
 * Created by Mainli on 2016/5/15.
 */
public class RootResponse<T> {
    private T result;
    private String errorInfo;
    private int code;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public RootResponse(WebResultState errorInfo, T result) {
        this.code = errorInfo.getCode();
        this.errorInfo = errorInfo.getErrorInfo();
        this.result = result;
    }

    public RootResponse(T result) {
        this.code = WebResultState.OK.getCode();
        this.errorInfo = WebResultState.OK.getErrorInfo();
        this.result = result;
    }

    public RootResponse() {
    }

    public RootResponse(WebResultState errorInfo) {
        this.code = errorInfo.getCode();
        this.errorInfo = errorInfo.getErrorInfo();
    }

    @Override
    public String toString() {
        return "RootRespoon{" +
                "result=" + result +
                ", errorInfo='" + errorInfo + '\'' +
                ", code=" + code +
                '}';
    }
}
