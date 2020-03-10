package com.zhizheng.entity;

public class Result<T> {

    private boolean result;
    private String msg;
    private T data;
    private int action;

    public Result(boolean result, String msg, T data) {
        this.result = result;
        this.msg = msg;
        this.data = data;
        this.action = 0;
    }

    public Result(boolean result, String msg, T data, int action) {
        this.result = result;
        this.msg = msg;
        this.data = data;
        this.action = action;
    }

    public Result(boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public Result(boolean result) {
        this.result = result;
    }

    public Result() {

    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public static class utils {
        public static <T> Result setSuccess() {
            Result result = new Result();
            result.setResult(true);
            result.setMsg("操作成功");
            return result;
        }

        public static <T> Result setSuccessResult(T data) {
            Result result = setSuccess();
            result.setData(data);
            return result;
        }

        public static <T> Result setFail() {
            Result result = new Result();
            result.setResult(false);
            result.setMsg("操作失败");
            return result;
        }
        public static <T> Result setFail(String message) {
            Result result = new Result();
            result.setResult(false);
            result.setMsg(message);
            return result;
        }

        public static <T> Result setFailResult(String msg) {
            Result result = setFail();
            if(msg!=null&&!"".equals(msg)){
                result.setMsg(msg);
            }
            return result;
        }
    }
}
