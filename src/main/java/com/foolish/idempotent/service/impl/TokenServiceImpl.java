package com.foolish.idempotent.service.impl;

import com.foolish.idempotent.constant.Constant;
import com.foolish.idempotent.service.RedisService;
import com.foolish.idempotent.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @description:
 * @author：chaoxianfei
 * @date：2020/3/26 13:47
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisService redisService;

    /**
     * 创建token
     *
     * @return
     */
    @Override
    public String createToken() {
        String str = UUID.randomUUID().toString();
        StringBuilder token = new StringBuilder();
        try {
            token.append(Constant.Redis.TOKEN_PREFIX).append(str);
            redisService.setEx(token.toString(), token.toString(), 10000L);
            boolean notEmpty = StringUtils.isNotEmpty(token.toString());
            if (notEmpty) {
                return token.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 检验token
     *
     * @param request
     * @return
     */
    @Override
    public boolean checkToken(HttpServletRequest request) throws Exception {
        String token = request.getHeader(Constant.TOKEN_NAME);
        if (StringUtils.isBlank(token)) {// header中不存在token
            token = request.getParameter(Constant.TOKEN_NAME);
            if (StringUtils.isBlank(token)) {// parameter中也不存在token
                throw new RuntimeException(Constant.ResponseCode.ILLEGAL_ARGUMENT);
            }
        }
        boolean exists = redisService.exists(token);
        if (!exists) {
            throw new RuntimeException(Constant.ResponseCode.REPETITIVE_OPERATION);
        }
        boolean remove = redisService.remove(token);
        if (!remove) {
            throw new RuntimeException(Constant.ResponseCode.REPETITIVE_OPERATION);
        }
        return true;
    }
}