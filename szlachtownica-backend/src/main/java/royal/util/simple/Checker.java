package royal.util.simple;

import royal.model.Person;

import java.util.Random;

public class Checker {
	public static Random rand = new Random();

	public static boolean hornyCheck(Person first) {
		return rand.nextDouble() < first.hornyFactor;
	}

	public static boolean loyalCheck(Person first) {
		return rand.nextDouble() < first.loyalFactor;
	}

	public static boolean homoCheck(Person first) {
		return rand.nextDouble() < first.homoFactor;
	}

	public static boolean interracialCheck(Person first) {
		return rand.nextDouble() < first.interracialFactor;
	}

	public static boolean attachmentCheck(Person first) {
		return rand.nextDouble() < first.attachmentFactor;
	}

	public static boolean poliamoricCheck(Person first) {
		return rand.nextDouble() < first.poliamoricFactor;
	}

	public static boolean jealousCheck(Person first) {
		return rand.nextDouble() < first.jealousFactor;
	}

	public static boolean impulsiveCheck(Person first) {
		return rand.nextDouble() < first.impulsiveFactor;
	}

	public static boolean proudCheck(Person first) {
		return rand.nextDouble() < first.proudFactor;
	}

	public static boolean amorousCheck(Person first) {
		return rand.nextDouble() < first.amorousFactor;
	}

}
