package com.dist.sys.util.constant;

import java.time.format.DateTimeFormatter;

public interface AppCommonConstant {

	DateTimeFormatter D_MM_YYYY_HH_MM_SS_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	DateTimeFormatter D_MM_YYYY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
}
