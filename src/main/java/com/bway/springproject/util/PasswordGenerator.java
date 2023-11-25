package com.bway.springproject.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator {
	public String getNewPassword() {
		return UUID.randomUUID().toString().substring(0, 8);
	}
}
