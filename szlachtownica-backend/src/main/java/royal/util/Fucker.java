package royal.util;

import royal.model.Family;
import royal.model.Person;
import royal.model.Relationship;

import java.util.Random;

public class Fucker {

	public static void unprotectedSex(Person person, Person partner) {
		if (person.sex) {
			makeChild(person, partner);
		} else {
			makeChild(partner, person);
		}
	}

	public static void makeChild(Person person, Person partner) {
		person.pregnantCounter = 40;
		person.childFather = partner;
	}

	public static void makeMeSomeBabies(Family family) {
		Person bestChoice = null;
		Person bestChoicePartner = null;
		Person personPartner = null;
		for (Person person : family.aliveMembers) {
			if (person.raceObj == family.raceObj && person.age > person.stableAge && person.kidsInFamily() < 5 && person.pregnantCounter == 0
					&& (partnerNoPregnant(person) || noPartner(person))) {
				if (bestChoice == null) {
					bestChoice = person;
				} else {
					if (!bestChoice.isMarried() && person.isMarried()) {
						bestChoice = person;
					} else if (bestChoice.isMarried() && person.isMarried()) {
						bestChoicePartner = bestChoice.getMarriage().getPartner(bestChoice);
						personPartner = person.getMarriage().getPartner(person);
						if (bestChoicePartner == null && personPartner != null) {
							bestChoice = person;
						} else if (bestChoicePartner != null && personPartner != null) {
							if (bestChoice.sex == bestChoicePartner.sex && person.sex != personPartner.sex) {
								bestChoice = person;
							} else if (bestChoice.sex != bestChoicePartner.sex && person.sex != personPartner.sex) {
								if (bestChoice.raceObj != bestChoicePartner.raceObj
										&& person.raceObj == personPartner.raceObj) {
									bestChoice = person;
								} else if (bestChoice.raceObj == bestChoicePartner.raceObj
										&& person.raceObj == personPartner.raceObj) {
									if (bestChoice.parentsFamily != family && person.parentsFamily == family) {
										bestChoice = person;
									} else if (bestChoice.parentsFamily == family && person.parentsFamily == family) {
										if (bestChoice.age > person.age) {
											bestChoice = person;
										}
									}
								} else {
									if (!bestChoice.sex && person.sex) {
										bestChoice = person;
									}
								}
							} else {
								if (!bestChoice.sex && person.sex) {
									bestChoice = person;
								}
							}
						}
					}
				}
			}
		}
		if (bestChoice != null) {
			if (bestChoicePartner != null) {
				if (bestChoice.sex != bestChoicePartner.sex && bestChoice.raceObj == bestChoicePartner.raceObj) {
					unprotectedSex(bestChoice, bestChoicePartner);
				} else if (bestChoice.hasAffairs()) {
					Person toFuck = null;
					for (Relationship rel : bestChoice.getAffairs()) {
						Person partner = rel.getPartner(bestChoice);
						if (bestChoice.sex != partner.sex && bestChoice.raceObj == partner.raceObj) {
							toFuck = partner;
						}
					}
					if (toFuck != null) {
						unprotectedSex(bestChoice, toFuck);
					} else {
						unprotectedSex(bestChoice, Birther.generateCompatibilePlebsPerson(bestChoice, true, false));
					}
				} else {
					unprotectedSex(bestChoice, Birther.generateCompatibilePlebsPerson(bestChoice, true, false));
				}
			} else {
				unprotectedSex(bestChoice, Birther.generateCompatibilePlebsPerson(bestChoice, true, false));
			}
		} else {

		}
	}

	private static boolean partnerNoPregnant(Person person) {
		return person.getMarriage() != null && person.getMarriage().getPartner(person) != null
				&& person.getMarriage().getPartner(person).pregnantCounter == 0;
	}
	
	private static boolean noPartner(Person person) {
		return !person.isMarried()
				|| person.getMarriage().getPartner(person) == null;
	}
}
