package com.foolish.idempotent.service.impl;

import com.foolish.idempotent.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author：chaoxianfei
 * @date：2020/4/29 14:48
 */
@Service
public class TestServiceImpl implements TestService {


    @Override
    public String testIdempotence() {
        return "测试重复提交的方法！";
    }
}
