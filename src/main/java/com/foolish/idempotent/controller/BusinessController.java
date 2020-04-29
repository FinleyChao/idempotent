package com.foolish.idempotent.controller;

import com.alibaba.fastjson.JSON;
import com.foolish.idempotent.constant.Constant;
import com.foolish.idempotent.entity.ResultVo;
import com.foolish.idempotent.service.AutoIdempotent;
import com.foolish.idempotent.service.TestService;
import com.foolish.idempotent.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author：chaoxianfei
 * @date：2020/3/26 14:03
 */
@RestController
public class BusinessController {

    @Resource
    private TokenService tokenService;

    @Resource
    private TestService testService;

    @PostMapping("/get/token")
    public String getToken() {
        String token = tokenService.createToken();
        if (StringUtils.isNotEmpty(token)) {
            ResultVo resultVo = new ResultVo();
            resultVo.setCode(Constant.code_success);
            resultVo.setMsg(Constant.SUCCESS);
            resultVo.setData(token);
            return JSON.toJSONString(resultVo);
        }
        return "";
    }

    @AutoIdempotent
    @PostMapping("/test/Idempotence")
    public String testIdempotence() {
        String businessResult = testService.testIdempotence();
        if (StringUtils.isNotEmpty(businessResult)) {
            ResultVo successResult = ResultVo.getSuccessResult(businessResult);
            return JSON.toJSONString(successResult);
        }
        return "";
    }
}
