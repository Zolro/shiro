package com.zyd.shiro.service;


import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Classname WxUserService
 * @Description TODO
 * @Date 2020/6/13 22:07
 * @Created by King
 */
public interface FileService {

     void downloadMulti(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
