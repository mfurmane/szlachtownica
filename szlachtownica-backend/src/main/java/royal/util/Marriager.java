package royal.util;

import royal.model.Family;
import royal.model.Person;
import royal.model.RelationType;
import royal.model.Relationship;
import royal.util.simple.Racist;
import royal.util.simple.Timer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Marriager {
	public static Random rand = new Random();
	static Calendar missallianceDate = Timer.getDate(1416);
	static double plebsMarriageChance = 0.06;
	static double likeEachOtherChance = 0.7;

	public static void handleMissalliance(Person person) {
		if (person.planedMarriage != null && Timer.currentCalendar.after(missallianceDate)) {
			marriage(person, person.planedMarriage);
		}
	}

	public static void handleMarriage(Person person, boolean familyAllowsHomo) {

	}

	public static boolean canBeMarried(Person first, Person second) {
		return true;
	}

	public static void marriage(Person person, Person other) {
		marriage(person, other, Timer.currentCalendar);
	}

	public static void marriage(Person person, Person other, Calendar date) {

	}

	private static Family findTargetFamily(Person person, Person other) {
		return null;
	}

}
