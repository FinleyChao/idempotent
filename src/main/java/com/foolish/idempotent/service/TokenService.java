package com.foolish.idempotent.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author：chaoxianfei
 * @date：2020/3/26 13:47
 */
public interface TokenService{
    /**
     * 创建token
     * @return
     */
    public String createToken();
    /**
     * 检验token
     * @param request
     * @return
     */
    public boolean checkToken(HttpServletRequest request) throws Exception;
}
