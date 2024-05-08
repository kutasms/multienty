
package com.chia.multienty.core.domain.basic;



import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "Result", description = "通用请求返回数据")
public class Result<T> implements Serializable {



    public Result() {
        this.code = HttpResultEnum.SUCCESS.getCode();
        this.message = HttpResultEnum.SUCCESS.getMessage();
    }

    public Result(T data) {
        this.code = HttpResultEnum.SUCCESS.getCode();
        this.message = HttpResultEnum.SUCCESS.getMessage();
        this.data = data;
    }




    public Result(T data, Integer code){
        this.code = code;
        this.message = HttpResultEnum.parse(code);
        this.data = data;
    }


    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public Result(T data, HttpResultEnum resultEnum){
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        this.data = data;
    }



    public Result(Integer code, KutaRuntimeException e){
        this.code = code;
        this.message = e.getMessage();
        this.errorData = e.getErrorData();
    }

    public Result(Integer code, Object data){
        this.code = code;
        this.message = HttpResultEnum.parse(code);
        this.errorData = data;
    }
    public Result(HttpResultEnum resultEnum, Object data){
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        this.errorData = data;
    }

    public Result(Integer code){
        this.code = code;
        this.message = HttpResultEnum.parse(code);
    }

    public Result(HttpResultEnum resultEnum){
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }




    /**
     * 返回编码code
     */
    @ApiModelProperty(value = "返回编码code")
    private Integer code;

    /**
     * 返回数据data
     */
    @ApiModelProperty(value = "返回数据data")
    private T data;

    /**
     * 错误数据
     */
    @ApiModelProperty(value = "错误数据")
    private Object errorData;

    /**
     * 返回信息
     */
    @ApiModelProperty(value = "返回信息")
    @JsonProperty(value = "msg")
    private String message;


    /**
     * 请求描述
     */
    private String description;


}
