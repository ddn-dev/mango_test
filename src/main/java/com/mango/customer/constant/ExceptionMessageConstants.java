package com.mango.customer.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessageConstants {

	public static final String USER_NOT_FOUND = "User not found > email=%s";
	public static final String MAX_SLOGANS_LIMIT_REACHED = "Max slogans limit reached";
}
