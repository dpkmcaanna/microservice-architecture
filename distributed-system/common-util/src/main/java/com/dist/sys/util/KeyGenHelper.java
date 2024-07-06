package com.dist.sys.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class KeyGenHelper {
	
	static int NUMBER_OF_DATE_BITS = 40;
	static int NUMBER_OF_RANDOM_BITS = 12;
	static int RANDOM_VALUE_LIMIT = 4096;
	
	private static long JOINING_DAY_2013 = LocalDateTime.of(2013, 8, 21, 10, 10).toEpochSecond(ZoneOffset.UTC);
	
	public long getDiffFromPivotDay() {
		return Instant.now().getEpochSecond() - JOINING_DAY_2013;
	}
	
	public String getNextKey() {
		return UUID.randomUUID().toString();
	}

	public long getSnowFlake() {
		String dataString = Long.toBinaryString(getDiffFromPivotDay());
		return getLongValue(dataString);
	}
	
	public int getCounter() {
		return new Random().nextInt(RANDOM_VALUE_LIMIT);
	}
	
	public String getZeroPadingString(int paddingZero) {
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < paddingZero; i++) {
			stringBuilder.append("0");
		}
		return stringBuilder.toString();
	}
	
	public Long getLongValue(String dateString) {
		StringBuilder stringBuilder = new StringBuilder();
		if(dateString.length() > NUMBER_OF_DATE_BITS) {
			stringBuilder.append(dateString.substring(dateString.length() - NUMBER_OF_DATE_BITS));
		} else if(dateString.length() < NUMBER_OF_DATE_BITS) {
			int paddingSize = NUMBER_OF_DATE_BITS - dateString.length();
			stringBuilder.append(getZeroPadingString(paddingSize));
			stringBuilder.append(dateString);
		} else {
			stringBuilder.append(dateString);
		}
		
		String counterString = Integer.toBinaryString(getCounter());
		
		if(counterString.length() > NUMBER_OF_RANDOM_BITS) {
			stringBuilder.append(counterString.substring(counterString.length() - NUMBER_OF_RANDOM_BITS));
		} else if(counterString.length() < NUMBER_OF_RANDOM_BITS) {
			int paddingSize = NUMBER_OF_RANDOM_BITS - counterString.length();
			stringBuilder.append(getZeroPadingString(paddingSize));
			stringBuilder.append(counterString);
		} else {
			stringBuilder.append(counterString);
		}
		
		return Long.parseLong(stringBuilder.toString(), 2);
	}
}
