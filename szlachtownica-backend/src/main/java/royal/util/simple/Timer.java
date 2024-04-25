package royal.util.simple;

import java.util.Calendar;
import java.util.Random;

public class Timer {
	public static Calendar currentCalendar = Calendar.getInstance();

	private static Random rand = new Random();

	public static Calendar getDate(int i) {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.YEAR, i);
		// date.add(Calendar.YEAR, i);
		date.set(Calendar.DAY_OF_YEAR, 5 + rand.nextInt(360));
		return date;
	}
}
