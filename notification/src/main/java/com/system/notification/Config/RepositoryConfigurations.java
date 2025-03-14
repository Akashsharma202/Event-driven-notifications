package com.system.notification.Config;

import com.system.notification.Interceptor.RequestHeaderInterceptor;
import com.system.notification.Repository.CommonCrudRepo;
import com.system.notification.Service.JwtService;
import com.system.notification.Utils.UserUtils;
import com.system.notification.model.Notification;
import com.system.notification.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfigurations {
    @Bean
    public CommonCrudRepo<User> commonCrudRepoUser(){return new CommonCrudRepo<>(User.class);}

    @Bean
    public CommonCrudRepo<Notification> commonCrudRepoNotification(){return new CommonCrudRepo<>(Notification.class);}


    @Bean
    public RequestHeaderInterceptor requestHeaderInterceptor(){return new RequestHeaderInterceptor();}

    @Bean
    public JwtService jwtService(){ return new JwtService();}

    @Bean
    public UserUtils userUtils(){return new UserUtils();}
}
