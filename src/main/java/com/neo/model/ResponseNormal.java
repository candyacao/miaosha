package com.neo.model;

public class ResponseNormal {
    private boolean success;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResponseNormal() {
        this.msg = "not init Model";
    }

    public ResponseNormal(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
}
