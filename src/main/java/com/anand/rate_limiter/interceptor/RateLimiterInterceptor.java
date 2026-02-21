package com.anand.rate_limiter.interceptor;

import com.anand.rate_limiter.service.RateLimiterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.util.RateLimiter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class RateLimiterInterceptor implements HandlerInterceptor {
    private final RateLimiterService rateLimiterService;
    public RateLimiterInterceptor(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String clientIp = request.getRemoteAddr();
        if(!rateLimiterService.isAllowed(clientIp)){
            response.setStatus(429);
            response.getWriter().write("Too Many Requests");
            return false;
        }
        return true;
    }
}
