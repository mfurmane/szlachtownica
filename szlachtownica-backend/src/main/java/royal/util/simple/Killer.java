package royal.util.simple;

import royal.model.Person;
import royal.model.Relationship;
import royal.util.Relater;

import java.util.Calendar;
import java.util.Random;

public class Killer {
	public static Random rand = new Random();
	private static double chanceToDie = 0.008;

	public static void kill(Person person, Calendar date) {
		if (person != null) {
			person.died = (Calendar) date.clone();
			makeDeath(person, date);
		} else {
			Debug.log(date.getTime().toString());
		}
	}

	public static void killOnly(Person person, Calendar date) {
		makeDeath(person, date);
	}

	private static void makeDeath(Person person, Calendar date) {
		if (person.isMarried()) {
			Person partner = person.getMarriage().getPartner(person);
			partner.mourningEnd = (Calendar) date.clone();
			partner.mourningEnd.add(Calendar.YEAR, partner.mourningTime);
			if (partner.family != partner.parentsFamily && !partner.plebs && (rand.nextDouble() < 0.3 || (partner.family.strenght < partner.parentsFamily.strenght && rand.nextDouble() < 0.6))) {
				Relater.backHome(partner, partner);
			}
		}
		for (Relationship rel : person.relationships) {
			if (rel.isActive())
				rel.end((Calendar) date.clone());
		}
		if (person.family.aliveMembers.contains(person)) {
			person.family.aliveMembers.remove(person);
		}
		if (!person.family.deadMembers.contains(person)) {
			person.family.deadMembers.add(person);
		}
	}

	public static void handleDeath(Person person) {
		if (person.died == null) {
			if ((person.legalKid > 1) || (person.isMarried() && person.getMarriage().getPartner(person).legalKid > 1)
					|| person.age > person.raceObj.lifespan) {
				if (person.age < person.raceObj.lifespan * 0.6) {

				} else if (person.age < person.raceObj.lifespan * 0.8) {
					if (rand.nextDouble() < chanceToDie / 500) {
						Killer.kill(person, Timer.currentCalendar);
					}
				} else if (person.age < person.raceObj.lifespan) {
					if (rand.nextDouble() < chanceToDie / 5) {
						Killer.kill(person, Timer.currentCalendar);
					}
				} else {
					if (rand.nextDouble() < chanceToDie * 50 || person.age > person.raceObj.lifespan * 1.1) {
						Killer.kill(person, Timer.currentCalendar);
					}
				}
			}
		} else if (person.died.before(Timer.currentCalendar)) {
			Killer.killOnly(person, Timer.currentCalendar);
		}
	}

}
