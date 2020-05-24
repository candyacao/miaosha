package com.github.candyacao.exception;

import com.github.candyacao.common.resultbean.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.github.candyacao.common.enums.ResultStatus.SESSION_ERROR;
import static com.github.candyacao.common.enums.ResultStatus.SYSTEM_ERROR;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobleExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        if (e instanceof GlobleException) {
            GlobleException ex = (GlobleException) e;
            return Result.error(ex.getStatus());
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            /**
             * 打印堆栈信息
             */
            log.error(String.format(msg, msg));
            return Result.error(SESSION_ERROR);
        } else {
            return Result.error(SYSTEM_ERROR);
        }
    }
}
