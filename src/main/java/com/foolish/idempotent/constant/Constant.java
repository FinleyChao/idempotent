package com.foolish.idempotent.constant;

/**
 * @description:
 * @author：chaoxianfei
 * @date：2020/4/29 14:39
 */
public class Constant {


    public static final String TOKEN_NAME = "IDEMPOTENT_TOKEN";
    public static final String SUCCESS = "SUCESS";
    public static Integer code_success = 200;

    public class Redis {

        public static final String TOKEN_PREFIX = "IDEMPOTENT";
    }

    public class ResponseCode {

        public static final String ILLEGAL_ARGUMENT = "非法参数";

        public static final String REPETITIVE_OPERATION = "重复操作";
    }
}
