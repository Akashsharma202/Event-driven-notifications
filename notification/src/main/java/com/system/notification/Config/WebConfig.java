package com.system.notification.Config;

import com.system.notification.Interceptor.RequestHeaderInterceptor;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private final RequestHeaderInterceptor requestHeaderInterceptor;

    public WebConfig(RequestHeaderInterceptor requestHeaderInterceptor) {
        this.requestHeaderInterceptor = requestHeaderInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addWebRequestInterceptor(requestHeaderInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/**",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/csrf",
                        "/",
                        "/api/login",
                        "/api/ping",
                        "/api/v1/access/login",
                        "/actuator/**");
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }

    @Bean
    public CorsFilter corsFilter() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("http://localhost:3000", "http://127.0.0.1:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With", "user_lang", "*"));
        configuration.setExposedHeaders(List.of("Authorization", "*"));
        configuration.setAllowCredentials(true); // Only set to true if you need to send cookies or use authentication
        configuration.setMaxAge(3600L); // 1 hour

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(source);
    }

}
