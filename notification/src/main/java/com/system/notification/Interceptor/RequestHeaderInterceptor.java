package com.system.notification.Interceptor;

import com.system.notification.Service.JwtService;
import com.system.notification.Utils.UserUtils;
import com.system.notification.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import java.util.Date;

public class RequestHeaderInterceptor implements WebRequestInterceptor {
    public static final Logger logger= LoggerFactory.getLogger(RequestHeaderInterceptor.class);
    public static final String AUTH_TOKEN = "Authorization";
    @Autowired
    private UserUtils userUtils;
    @Autowired
    private JwtService jwtService;
    @Override
    public void preHandle(WebRequest request) throws Exception {
//        String token = request.getHeader(AUTH_TOKEN);
//        if (token == null || !token.startsWith("Bearer ")) {
//            throw new Exception("Invalid or missing value for authorization token.");
//        }
//        token = token.substring(7);
//        Date date = jwtService.extractExpiration(token);
//        if(date.before(new Date())){
//            throw new Exception("Token Expired.");
//        }
//        String username= jwtService.extractUserName(token);
//        User user= userUtils.getUsersDetailsByName(username);
//        ThreadContext.setUsers(user);
//        logger.info("Setting thread context, user: " + ThreadContext.getUsers().getName());
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) {
        ThreadContext.clear();
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) {
        // TODO Auto-generated method stub

    }
}
