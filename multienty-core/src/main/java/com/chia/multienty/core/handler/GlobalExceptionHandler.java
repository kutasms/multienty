package com.chia.multienty.core.handler;

import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.HttpException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.io.IOException;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @ExceptionHandler(value = HttpException.class)
    @ResponseBody
    public Result exceptionHandler(HttpException exception, HttpServletResponse response) throws IOException {
        Result result = new Result(exception.getErrorCode(), exception.getMessage());
        response.getWriter().write(objectMapper.writeValueAsString(result));
        response.setStatus(exception.getStatusCode());
        response.getWriter().flush();
        return null;
    }

    /**
     * RequestParam/PathVariable形式参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> handleConstraintViolationException(ConstraintViolationException e) {
        log.error("[全局异常处理] [参数校验不通过]{}", e.getMessage(), e);
        return new Result<>(HttpResultEnum.CONSTRAINT_VIOLATION);
    }

    /**
     * 方法参数校验异常[类型不配备]
     * @param e
     * @return
     */
    @ExceptionHandler(UnexpectedTypeException.class)
    public Result<String> handleUnexpectedArgTypeException(UnexpectedTypeException e) {
        log.error("[全局异常处理] [参数校验类型不匹配]{}", e.getMessage(), e);
        return new Result<>(HttpResultEnum.UNEXPECTED_ARG_TYPE_ERROR);
    }

    /**
     * 方法参数校验异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();
        if(result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            if(errors != null) {
                errors.forEach(p -> {
                    FieldError fieldError = (FieldError) p;
                    log.warn("Bad Request Parameters: dto: [{}], field:[{}], message:[{}]",
                            fieldError.getObjectName(),
                            fieldError.getField(),
                            fieldError.getDefaultMessage());
                    stringBuilder.append(fieldError.getDefaultMessage());
                });
            }
        }
        return new Result(HttpResultEnum.METHOD_ARG_NOT_VALID, stringBuilder.toString());
    }

    /**
     * 前端传递参数验证异常
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    public Result<String> handleBindException(BindException ex) {
        log.warn("[全局异常处理] [提交参数校不符合规则]{}", ex.getMessage(), ex);
        return new Result<>(HttpResultEnum.BIND_EXCEPTION);
    }
}
