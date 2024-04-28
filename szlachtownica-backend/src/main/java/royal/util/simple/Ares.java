package royal.util.simple;

import royal.model.Family;
import royal.model.Person;
import royal.model.War;
import royal.util.Familiar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Ares {
	public static Random rand = new Random();
	public static List<War> wars = new ArrayList<>();
	public static List<Calendar> princeChanges = new ArrayList<>();

	public static void handleWar(Person person) {
		for (War war : wars) {
			if (war.start.before(Timer.currentCalendar) && war.end.after(Timer.currentCalendar)) {
				if (rand.nextDouble() < 0.00005 && person.family.aliveMembers.size() > 20) {
					if (person.isAdult()) {
						Killer.kill(person, Timer.currentCalendar);
					}
				}
			}
		}
	}

	public static void prepareWars() {
		wars.add(new War(Timer.getDate(1184), Timer.getDate(1188)));
		wars.add(new War(Timer.getDate(1189), Timer.getDate(1194)));
		wars.add(new War(Timer.getDate(1323), Timer.getDate(1325)));
		wars.add(new War(Timer.getDate(1380), Timer.getDate(1181)));
		wars.add(new War(Timer.getDate(1384), Timer.getDate(1388)));
		wars.add(new War(Timer.getDate(1404), Timer.getDate(1408)));
		wars.add(new War(Timer.getDate(1422), Timer.getDate(1425)));
		princeChanges.add(getLateDate(1425));
		wars.add(new War(Timer.getDate(1439), Timer.getDate(1443)));
		wars.add(new War(Timer.getDate(1447), Timer.getDate(1454)));
		wars.add(new War(Timer.getDate(1481), Timer.getDate(1487)));
		princeChanges.add(getLateDate(1487));
		wars.add(new War(Timer.getDate(1497), Timer.getDate(1507)));
		wars.add(new War(Timer.getDate(1671), Timer.getDate(1672)));
	}

	private static Calendar getLateDate(int i) {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.YEAR, i);
		// date.add(Calendar.YEAR, i);
		date.set(Calendar.DAY_OF_YEAR, 250 + rand.nextInt(110));
		return date;
	}

	public static void handlePrinceChange(Family family) {
		Calendar toDelete = null;
		for (Calendar change : princeChanges) {
			if (change.before(Timer.currentCalendar) && family.raceObj == Racist.human && family != Familiar.tagar) {
				Killer.kill(family.getHead(), Timer.currentCalendar);
				toDelete = change;
			}
		}
		if (toDelete != null) princeChanges.remove(toDelete);
	}
}
