package com.foolish.idempotent.entity;

import lombok.Data;

/**
 * @Description: http请求最外层对象
 * @Author DaiYang
 * @Date 2019/1/7 13:50
 */
@Data
public class ResultVo<T> {

    private Integer code;

    private String msg;

    private T data;


    public static ResultVo getFailedResult(int i, String message) {
        ResultVo<Object> resultVO = new ResultVo<>();
        resultVO.setCode(i);
        resultVO.setMsg(message);
        return resultVO;
    }

    public static ResultVo getSuccessResult(String businessResult) {
        ResultVo<Object> resultVO = new ResultVo<>();
        resultVO.setCode(200);
        resultVO.setMsg(businessResult);
        return resultVO;
    }
}
