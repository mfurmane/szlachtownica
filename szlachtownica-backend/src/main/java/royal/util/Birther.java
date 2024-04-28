package royal.util;

import royal.model.Person;
import royal.model.Race;
import royal.model.RelationType;
import royal.model.Relationship;
import royal.util.simple.*;

import java.util.Calendar;
import java.util.Random;

public class Birther {
	private static final int afterBirth = -40;
	public static Random rand = new Random();

	public static void handleBirth(Person person) {
		person.birthAt--;
		if (person.birthAt == 0) {
			person.born = (Calendar) Timer.currentCalendar.clone();
		}
	}

	public static void handlePregnancy(Person person) {
		if (person.pregnantCounter < 0) {
			person.pregnantCounter++;
		}
		if (person.pregnantCounter > 0) {
			person.pregnantCounter--;
			if (person.pregnantCounter == 0) {
				childOut(person);
			}
		}
	}

	private static void childOut(Person person) {
		Person child = createChild(person, person.childFather);

	}

	private static Person createChild(Person person, Person partner) {
		return Personist.getPerson(rand.nextBoolean(), person, partner, lineImportance(person, partner), membershipStrenght(person, partner), Person.Status.LEGAL_CHILD);
	}

	private static double lineImportance(Person person, Person partner) {
		return 1;
	}

	private static double membershipStrenght(Person person, Person partner) {
		return 1;
	}

	public static Person generateCompatibilePlebsPerson(Person person, boolean toMarry, boolean familyAllowsHomo) {
		Rex sexAndRace = prepareSexAndRace(person, toMarry, familyAllowsHomo);
		Person other = Personist.getPerson(sexAndRace.female, sexAndRace.race, 1, 1, Person.Status.LEGAL_CHILD);
		double lifeMoment = ((double) person.age / person.raceObj.lifespan) * (0.8 + (0.4 * rand.nextDouble()));
		int partnerAge = Math.max((int) (lifeMoment * sexAndRace.race.lifespan), sexAndRace.race.childFrom);
		Calendar date = Timer.getDate(Timer.currentCalendar.get(Calendar.YEAR));
		date.add(Calendar.YEAR, -partnerAge);
		other.born = date;
		other.plannedKids = 0;
		other.plebs = true;
		optionalLegalPartner(toMarry, other);
		return other;
	}

	private static void optionalLegalPartner(boolean toMarry, Person other) {
		if (rand.nextDouble() < 0.1 && !toMarry) {
			Calendar someDate = (Calendar) Timer.currentCalendar.clone();
			someDate.add(Calendar.YEAR, -3);
			other.relationships.add(new Relationship(RelationType.LEGAL, other, null, someDate));
		}
	}

	private static Rex prepareSexAndRace(Person person, boolean toMarry, boolean familyAllowsHomo) {
		Race race = person.raceObj;
		boolean female;
		if (toMarry) {
			if (familyAllowsHomo) {
				female = rand.nextBoolean();
				if (Checker.interracialMultiCheck(person)) {
					race = Racist.randomRace(person.raceObj);
				}
			} else {
				female = !person.sex;
			}
		} else {
			race = Checker.interracialMultiCheck(person) ? Racist.randomRace(person.raceObj) : person.raceObj;
			female = rand.nextBoolean();
		}
		return new Rex(race, female);
	}

	private static class Rex {
		public final Race race;
		public final boolean female;

		public Rex(Race race, boolean female) {
			this.race = race;
			this.female = female;
		}
	}

	public static Person getFather(Person person, Person partner) {
		if (person != null && !person.sex)
			return person;
		if (partner != null && !partner.sex)
			return partner;
		return null;
	}

	public static Person getMother(Person person, Person partner) {
		if (person != null && person.sex)
			return person;
		if (partner != null && partner.sex)
			return partner;
		return null;
	}

	public static void handleKnownKids(Person person) {

	}

}
