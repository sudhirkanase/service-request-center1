package com.wellsfargo.serv_req_center.auth.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@Component
public class LoggedInUserInfoCache {

	private Cache<String, User> userCache;

	public LoggedInUserInfoCache() {
		this.userCache = CacheBuilder.newBuilder().expireAfterAccess(15, TimeUnit.MINUTES).build();
	}

	public void put(String key, User user) {
		userCache.put(key, user);
	}

	public User get(String key) {
		return userCache.getIfPresent(key);
	}

	public void remove(String key) {
		userCache.invalidate(key);
	}
}
