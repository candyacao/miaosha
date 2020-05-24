package com.github.candyacao.common.resultbean;

import com.github.candyacao.common.enums.ResultStatus;

import java.io.Serializable;

public class Result<T> extends AbstractResult implements Serializable {
    private static final long serialVersionUID = 867933019328199779L;
    private T data;
    private Integer count;

    protected Result() {
    }

    protected Result(ResultStatus status, String message) {
        super(status, message);
    }

    protected Result(ResultStatus status) {
        super(status);
    }

    public static <T> Result<T> build() {
        return new Result(ResultStatus.SUCCESS, (String) null);
    }

    public static <T> Result<T> build(String message) {
        return new Result(ResultStatus.SUCCESS, message);
    }

    public static <T> Result<T> error(ResultStatus status) {
        return new Result<T>(status);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void success(T value) {
        this.success();
        this.data = value;
        this.count = 0;
    }

}

