package royal.util.simple;

import royal.model.Family;
import royal.model.Person;
import royal.model.Person.Status;
import royal.model.Race;
import royal.util.Familiar;
import royal.util.Marriager;
import royal.util.Personist;

import java.util.Random;

public class EldrichOnes {
	public static Random rand = new Random();

	public static void createNestor(Family family) {
		Couple mainCouple;
		if (family.raceObj == Racist.human) {
			mainCouple = mainCouple(family, 20, 20, 8);
		} else if (family.raceObj == Racist.elf) {
			mainCouple = mainCouple(family, 40, 400, 40);
			mainCouple.person1.name += (" " + Namer.middleNames[rand.nextInt(Namer.middleNames.length)]);
			mainCouple.person2.name += (" " + Namer.middleNames[rand.nextInt(Namer.middleNames.length)]);
		} else if (family.raceObj == Racist.dwarf) {
			Couple fathers = fathers(family);
			mainCouple = mainCouple(family, 30, 140, 16, fathers);
		} else if (family.raceObj == Racist.half) {
			mainCouple = mainCouple(family, 20, 65, 10);
		} else if (family.raceObj == Racist.gnome) {
			mainCouple = mainCouple(family, 30, 130, 12);
		} else if (family.raceObj == Racist.peura) {
			mainCouple = mainCouple(family, 40, 70, 10);
		} else {
			mainCouple = mainCouple(family, 0, 0, 0);
		}
		family.addMember(mainCouple.person1);
		family.addMember(mainCouple.person2);
		Marriager.marriage(mainCouple.person1, mainCouple.person2, Timer.getDate(family.creationDate - rand.nextInt(3) - 2));
		mainCouple.person1.family.nestor = mainCouple.person1;
		mainCouple.person1.plannedKids = 2 + rand.nextInt(3);
	}

	private static Couple mainCouple(Family family, int minimalAge, int diversity, int maxAgeDifference, Couple fathers) {
		int ageDifference = rand.nextInt(maxAgeDifference);
		int born = family.creationDate - (minimalAge + rand.nextInt(diversity));
		boolean sex = rand.nextBoolean();
		Person person1 = Personist.getPerson(sex, family, sex ? born - ageDifference : born, fathers.person1);
		Person person2 = Personist.getPerson(!sex, family, sex ? born : born - ageDifference, fathers.person2);
		return new Couple(person1, person2);
	}

	private static Couple mainCouple(Family family, int minimalAge, int diversity, int maxAgeDifference) {
		return mainCouple(family, minimalAge, diversity, maxAgeDifference, new Couple(null, null));
	}

	private static Couple fathers(Family family) {
		Person person1 = Personist.getPerson(false, family, 100);
		Person person2 = Personist.getPerson(false, family, 100);
		return new Couple(person1, person2);
	}

	private static class Couple {
		public final Person person1;
		public final Person person2;

		public Couple(Person person1, Person person2) {
			this.person1 = person1;
			this.person2 = person2;
		}
	}

	public static int mourningTime() {
		return 2 + rand.nextInt(6);
	}
}
