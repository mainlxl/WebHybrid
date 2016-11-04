package com.mainli.demo.response;

import android.util.SparseArray;

public enum WebResultState {
    OK(0, "OK"), CLOSE(10004, "CLOSE"), ERROR(50001, "SYSTEM_ERROR"), ACTIONNAMEERROR(50002, "ACTION_NAME_MISS_MATCHES"), PARAMERROR(50003, "PARAM_ERROR");


    private int code;
    private String errorInfo;

    WebResultState(int code, String errorerrorInfo) {
        this.code = code;
        this.errorInfo = errorerrorInfo;
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

    public static WebResultState obtainState(int code) {
        for (WebResultState staus : values()) {
            if (staus.code == code) return staus;
        }
        return null;
    }

    private static SparseArray<String> stringSparseArray;

    public static SparseArray<String> getMap() {
        if (stringSparseArray == null || stringSparseArray.size() < 0) {
            stringSparseArray = new SparseArray<>(values().length);
            for (WebResultState staus : values()) {
                stringSparseArray.append(staus.code, staus.errorInfo);
            }
        }
        return stringSparseArray;
    }

    public static String obtainStateerrorInfo(Integer code) {
        if (code != null) {
            for (WebResultState staus : values()) {
                if (staus.code == code) return staus.errorInfo;
            }
        }
        return "未知";
    }

    @Override
    public String toString() {
        return errorInfo;
    }

    public static boolean isStateHave(int code) {
        return obtainState(code) != null;
    }
}