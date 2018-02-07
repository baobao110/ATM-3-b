package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dateformate {
		public static String formate(Date date) {
			SimpleDateFormat form=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return form.format(date);
		}
}
