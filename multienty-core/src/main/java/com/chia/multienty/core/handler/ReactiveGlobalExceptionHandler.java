package com.chia.multienty.core.handler;

import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.HttpException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.util.List;

@Slf4j
@ControllerAdvice
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class ReactiveGlobalExceptionHandler implements ErrorWebExceptionHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        log.info("ex:" + ex);
        // 1.创建一个响应对象
        ServerHttpResponse response = exchange.getResponse();
        HttpHeaders headers = response.getHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Result<Object> result = null;
        // 2.根据不同的异常类型返回不同的错误信息和HTTP状态码

        if(ex instanceof BindException) {
            log.warn("[全局异常处理] [提交参数校不符合规则]{}", ex.getMessage(), ex);
            result = new Result<>(HttpResultEnum.BIND_EXCEPTION);
        }
        else if(ex instanceof MethodArgumentNotValidException) {
            log.error("[全局异常处理] [方法参数效验不通过]{}", ex.getMessage(), ex);
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException)ex;
            BindingResult bindingResult = exception.getBindingResult();
            StringBuilder stringBuilder = new StringBuilder();
            if(bindingResult.hasErrors()) {
                List<ObjectError> errors = bindingResult.getAllErrors();
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
            result = new Result(HttpResultEnum.METHOD_ARG_NOT_VALID, stringBuilder.toString());
        }
        else if(ex instanceof UnexpectedTypeException) {
            log.error("[全局异常处理] [参数校验类型不匹配]{}", ex.getMessage(), ex);
            result = new Result<>(HttpResultEnum.UNEXPECTED_ARG_TYPE_ERROR);
        }
        else if(ex instanceof ConstraintViolationException) {
            log.error("[全局异常处理] [参数校验不通过]{}", ex.getMessage(), ex);
            result = new Result<>(HttpResultEnum.CONSTRAINT_VIOLATION);
        }
        else if(ex instanceof HttpException) {
            HttpException httpException = (HttpException) ex;
            result = new Result(httpException.getErrorCode(), httpException.getMessage());
        }
        else {
            log.warn("[全局异常处理] [未处理异常]{}", ex.getMessage(), ex);
            result = new Result<>(HttpResultEnum.UNHANDLED_EXCEPTION.getCode(), ex.getMessage());
        }
        try {
            DataBuffer buffer = response.bufferFactory().wrap(objectMapper.writeValueAsBytes(result));
            response.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
            return response.writeWith(Mono.just(buffer));
        } catch (JsonProcessingException e) {
            return Mono.error(e);
        }
    }
}
