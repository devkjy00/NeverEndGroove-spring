package jy.demo.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class TokenCacheService {

    @CachePut(value = "tokens", key = "#userId")
    public String storeToken(Long userId, String accessToken) {
        return accessToken;
    }

    @Cacheable(value = "tokens", key = "#userId")
    public String getToken(Long userId) {
        return null;
    }

    @CacheEvict(value = "tokens", key = "#userId")
    public void evictToken(Long userId) {
    }
}

