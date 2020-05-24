package com.github.candyacao.exception;

import com.github.candyacao.common.enums.ResultStatus;
// Todo: 用Lombok去重构getter/setter
public class GlobleException extends RuntimeException {


    private ResultStatus status;

    public GlobleException(ResultStatus status){
        super();
        this.status = status;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }
}
