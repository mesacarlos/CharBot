package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
	public static String getDateAsString() {
		return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
	}
}