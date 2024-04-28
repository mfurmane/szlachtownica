package royal.util.simple;

import royal.Main;
import royal.model.Family;
import royal.util.Familiar;

import java.util.Calendar;
import java.util.Random;

public class Timer {
	public static Calendar currentCalendar = Calendar.getInstance();
	private static final int historyStart = 687;// 1198;
	private static final int historyEnd = 1743;// 1743;

	private static final Random rand = new Random();

	public static int startYear() {
		return Timer.currentCalendar.get(Calendar.YEAR);
	}

	public static Calendar getDate(int i) {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.YEAR, i);
		// date.add(Calendar.YEAR, i);
		date.set(Calendar.DAY_OF_YEAR, 5 + rand.nextInt(360));
		return date;
	}

	public static int updateYear() {
		int year = currentCalendar.get(Calendar.YEAR);
		for (Family family : Familiar.families) {
			if (!family.plebs && year >= family.creationDate && family.plebsMarriageYearsCounter > 0) {
				family.plebsMarriageYearsCounter--;
			}
			//if (!family.plebs && year >= family.creationDate) someDebugShitAboutFamilyMemberCount(family);
		}
		return year;
	}

	public static Calendar prepareEndOfTime() {
		currentCalendar.set(Calendar.YEAR, historyStart);
		currentCalendar.set(Calendar.DAY_OF_YEAR, 1);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.set(Calendar.YEAR, historyEnd);
		endCalendar.set(Calendar.DAY_OF_YEAR, 1);
		return endCalendar;
	}
}
