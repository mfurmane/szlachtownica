package royal.util.simple;

import royal.model.Person;

import java.util.Random;

public class Stater {
	public static Random rand = new Random();

	public static double getImportance(Person person) {
		if (!person.isMarried())
			return person.lineImportance;
		return (person.lineImportance + (person.getMarriage().getPartner(person) != null ? person.getMarriage().getPartner(person).lineImportance : person.lineImportance * 0.8)) / 2;
	}

	public static double homo(Person person) {
		if (rand.nextDouble() < 0.3)
			return 0;
		if (rand.nextDouble() < 0.05)
			return 1;
		return rand.nextDouble();
	}

	public static double attachment(Person person) {
		if (person == null)
			return (0.4 + rand.nextDouble() * 1.2);
		if (rand.nextDouble() < 0.2)
			return rand.nextDouble();
		return Math.min(person.raceObj.attachmentFactor * (0.2 + rand.nextDouble() * 1.6), 0.99);
	}

	public static double poliamoric(Person person) {
		if (person == null)
			return rand.nextDouble();
		if (rand.nextDouble() < 0.2)
			return rand.nextDouble();
		return Math.min(person.raceObj.poliamoricFactor * (0.4 + rand.nextDouble() * 1.2), 0.9);
	}

	public static double impulsive(Person person) {
		if (person == null)
			return rand.nextDouble();
		if (rand.nextDouble() < 0.2)
			return rand.nextDouble();
		return Math.min(person.raceObj.impulsiveFactor * (0.4 + rand.nextDouble() * 1.2), 0.98);
	}

	public static double jealous(Person person) {
		if (person == null)
			return rand.nextDouble();
		if (rand.nextDouble() < 0.2)
			return rand.nextDouble();
		return Math.min(person.raceObj.jealousFactor * (0.3 + rand.nextDouble() * 1.4), 0.9);
	}

	public static double amorous(Person person) {
		if (person == null)
			return (0.2 + rand.nextDouble() * 1.6);
		if (rand.nextDouble() < 0.2)
			return rand.nextDouble();
		return Math.min(person.raceObj.amorousFactor * (0.1 + rand.nextDouble() * 1.8), 0.999);
	}

	public static double proud(Person person) {
		if (person == null)
			return rand.nextDouble();
		if (rand.nextDouble() < 0.2)
			return rand.nextDouble();
		return Math.min(person.raceObj.proudFactor * (0.4 + rand.nextDouble() * 1.2), 0.9);
	}

	public static int childFrom(Person person) {
		return (int) (person.raceObj.childFrom * (1 + rand.nextDouble() * 0.4));
	}

	public static int wantChild(Person person) {
		return (int) (person.raceObj.wantChildFrom * (1 + rand.nextDouble() * 0.2));
	}

	public static double horny(Person person) {
		return Math.min(person.raceObj.hornyFactor * (0.7 + rand.nextDouble() * 0.6), 0.98);
	}

	public static double loyal(Person person) {
		return Math.min(person.raceObj.loyalFactor * (0.7 + rand.nextDouble() * 0.6), 0.999);
	}

	public static double interracial(Person person) {
		double toReturn = Math.min(person.raceObj.interracialFactor * (0.6 + rand.nextDouble() * 0.2), 0.8);
		return toReturn;
	}
}
