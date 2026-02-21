package com.anand.rate_limiter.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {
    private final int maxRequests;
    private final long windowMs;
    private final Map<String, Deque<Long>> requestLogs =  new ConcurrentHashMap<>();
    public RateLimiterService(@Value("${rate.limit.maxRequests}") int maxRequests, @Value("${rate.limit.windowMs}") long windowMs) {
        this.maxRequests = maxRequests;
        this.windowMs = windowMs;
    }

    public boolean isAllowed(String clientIp){
        long currentTime = System.currentTimeMillis();
        requestLogs.putIfAbsent(clientIp, new ArrayDeque<>());
        Deque<Long> timestamps = requestLogs.get(clientIp);
        synchronized (timestamps){
            while (!timestamps.isEmpty() && currentTime - timestamps.peekFirst() > windowMs){
                timestamps.pollFirst();
            }
            if (timestamps.size() >= maxRequests) {
                return false;
            }
            timestamps.addLast(currentTime);
            return true;
        }
    }
}
