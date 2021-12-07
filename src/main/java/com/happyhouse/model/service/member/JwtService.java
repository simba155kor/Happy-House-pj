package com.happyhouse.model.service.member;

import java.util.Map;

public interface JwtService {
	<T> String create(String key, T data, String subject);
	Map<String, Object> get(String key);
	boolean isUsable(String jwt);
}
