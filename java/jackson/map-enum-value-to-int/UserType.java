package com.ghoon.webapp.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserType {
	Administrator(1),
	Guest(2),
	Normal(3);
	
	private int value;
	
	private UserType(int value) {
		this.value = value;
	}
	
	@JsonCreator
	public static UserType valueOf(int value) {
		switch (value) {
		case 1: return UserType.Administrator;
		case 2: return UserType.Guest;
		case 3: return UserType.Normal;
		
		default:
			throw new IllegalArgumentException(String.format("Unknown value: %s", value));
		}
	}
	
	@JsonValue
	public int intValue() {
		return this.value;
	}
}
