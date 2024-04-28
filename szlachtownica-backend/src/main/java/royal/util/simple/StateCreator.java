package royal.util.simple;

import royal.model.Person;
import royal.model.Race;

import java.util.Random;

public class StateCreator {
	public static Random rand = new Random();

	public static double getImportance(Race race) {
		return 1;
	}

	public static double homo(Race race) {
		if (rand.nextDouble() < 0.3)
			return 0;
		if (rand.nextDouble() < 0.05)
			return 1;
		return rand.nextDouble();
	}

	public static double attachment(Race race) {
		if (rand.nextDouble() < 0.2)
			return rand.nextDouble();
		return Math.min(race.attachmentFactor * (0.2 + rand.nextDouble() * 1.6), 0.99);
	}

	public static double poliamoric(Race race) {
		if (rand.nextDouble() < 0.2)
			return rand.nextDouble();
		return Math.min(race.poliamoricFactor * (0.4 + rand.nextDouble() * 1.2), 0.9);
	}

	public static double impulsive(Race race) {
		if (rand.nextDouble() < 0.2)
			return rand.nextDouble();
		return Math.min(race.impulsiveFactor * (0.4 + rand.nextDouble() * 1.2), 0.98);
	}

	public static double jealous(Race race) {
		if (rand.nextDouble() < 0.2)
			return rand.nextDouble();
		return Math.min(race.jealousFactor * (0.3 + rand.nextDouble() * 1.4), 0.9);
	}

	public static double amorous(Race race) {
		if (rand.nextDouble() < 0.2)
			return rand.nextDouble();
		return Math.min(race.amorousFactor * (0.1 + rand.nextDouble() * 1.8), 0.999);
	}

	public static double proud(Race race) {
		if (rand.nextDouble() < 0.2)
			return rand.nextDouble();
		return Math.min(race.proudFactor * (0.4 + rand.nextDouble() * 1.2), 0.9);
	}

	public static int childFrom(Race race) {
		return (int) (race.childFrom * (1 + rand.nextDouble() * 0.4));
	}

	public static int wantChild(Race race) {
		return (int) (race.wantChildFrom * (1 + rand.nextDouble() * 0.2));
	}

	public static double horny(Race race) {
		return Math.min(race.hornyFactor * (0.7 + rand.nextDouble() * 0.6), 0.98);
	}

	public static double loyal(Race race) {
		return Math.min(race.loyalFactor * (0.7 + rand.nextDouble() * 0.6), 0.999);
	}

	public static double interracial(Race race) {
		return Math.min(race.interracialFactor * (0.6 + rand.nextDouble() * 0.2), 0.8);
	}
}
