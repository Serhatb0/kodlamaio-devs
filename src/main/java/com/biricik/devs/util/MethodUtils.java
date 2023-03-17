package com.biricik.devs.util;

import java.sql.Timestamp;
import java.util.Date;

public final class MethodUtils {
    
    public static Date getCurrentTimeStamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
}
