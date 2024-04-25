package royal.util;

import royal.model.Person;
import royal.model.Person.Status;
import royal.model.Race;
import royal.model.RelationType;
import royal.model.Relationship;
import royal.util.simple.*;

import java.util.Calendar;
import java.util.Random;

public class Birther {
	private static int afterBirth = -40;
	public static Random rand = new Random();

	public static void handleBirth(Person person) {
		person.birthAt--;
		if (person.birthAt == 0) {
			person.born = (Calendar) Timer.currentCalendar.clone();
//			handleBastard(person.father, person);
		}
	}

	public static void handlePregnancy(Person person) {
		if (person.pregnantCounter < 0) {
			person.pregnantCounter++;
		}
		if (person.pregnantCounter > 0) {
			person.pregnantCounter--;
			if (person.pregnantCounter == 0) {
				person.pregnantCounter = afterBirth * (person.raceObj.lifespan / 80) + rand.nextInt(50);
				Person child = createChild(person, person.childFather, (Calendar) Timer.currentCalendar.clone());
				child.family = person.family;
				person.family.addMember(child);
				addKid(person, child);
				boolean married = person.isMarried();
				Person partner = person;
				if (married)
					partner = person.getMarriage().getPartner(person);
				if (married && person.sex != partner.sex && person.raceObj == partner.raceObj) {
					person.legalKid++;
					person.getMarriage().getPartner(person).legalKid++;
					child.status = Status.LEGAL_CHILD;
					child.lineImportance = Stater.getImportance(person);
					downgrade(person, child, false);
				} else {
					child.status = Status.ACCEPTED_BASTARD;
					child.lineImportance = Stater.getImportance(person) * 0.6;
					downgrade(person, child, false);
				}
				child.importance = child.lineImportance * child.family.strenght / 10;
//				if (child.family.aliveMembers.size() < 10) {
//					child.plannedKids = 2 + rand.nextInt(3);
//				} else if (child.family.aliveMembers.size() < 27) {
//					child.plannedKids = rand.nextInt(3);
//				} else if (child.family.aliveMembers.size() < 50) {
//					child.plannedKids = rand.nextInt(2);
//				} else {
//					child.plannedKids = 0;
//				}
			}
		}
	}

	private static Person createChild(Person person, Person partner, Calendar date) {
		Person child;
		if (person.raceObj == Racist.human) {
			if (rand.nextBoolean()) {
				// name, family, father, mother, born, status, sex, travel, horny, loyal, homo,
				// interracial, line, membership, hornyAge, stableAge, mourningTime
				// attachmentFactor, poliamoricFactor, jealousFactor, impulsiveFactor,
				// proudFactor, amorousFactor
				child = new Person(Namer.getName(true, Racist.human), person.family, getFather(person, partner),
						getMother(person, partner), date, Status.LEGAL_CHILD, true, rand.nextDouble(),
						Stater.horny(person), Stater.loyal(person), Stater.homo(person), Stater.interracial(person), 1,
						1, Stater.childFrom(person), Stater.wantChild(person), EldrichOnes.mourningTime(),
						Stater.attachment(person), Stater.poliamoric(person), Stater.jealous(person),
						Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			} else {
				child = new Person(Namer.getName(false, Racist.human), person.family, getFather(person, partner),
						getMother(person, partner), date, Status.LEGAL_CHILD, false, rand.nextDouble(),
						Stater.horny(person), Stater.loyal(person), Stater.homo(person), Stater.interracial(person), 1,
						1, Stater.childFrom(person), Stater.wantChild(person), EldrichOnes.mourningTime(),
						Stater.attachment(person), Stater.poliamoric(person), Stater.jealous(person),
						Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			}
		} else if (person.raceObj == Racist.elf) {
			if (rand.nextBoolean()) {
				// name, family, father, mother, born, status, sex, travel, horny, loyal, homo,
				// interracial, line, membership, hornyAge, stableAge, mourningTime
				child = new Person(Namer.getName(true, Racist.elf), person.family, getFather(person, partner),
						getMother(person, partner), date, Status.LEGAL_CHILD, true, rand.nextDouble(),
						Stater.horny(person), Stater.loyal(person), Stater.homo(person), Stater.interracial(person), 1,
						1, Stater.childFrom(person), Stater.wantChild(person), EldrichOnes.mourningTime(),
						Stater.attachment(person), Stater.poliamoric(person), Stater.jealous(person),
						Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			} else {
				child = new Person(Namer.getName(false, Racist.elf), person.family, getFather(person, partner),
						getMother(person, partner), date, Status.LEGAL_CHILD, false, rand.nextDouble(),
						Stater.horny(person), Stater.loyal(person), Stater.homo(person), Stater.interracial(person), 1,
						1, Stater.childFrom(person), Stater.wantChild(person), EldrichOnes.mourningTime(),
						Stater.attachment(person), Stater.poliamoric(person), Stater.jealous(person),
						Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			}
//			child.name += (" " + middleNames[rand.nextInt(middleNames.length)]);
		} else if (person.raceObj == Racist.dwarf) {
			if (rand.nextBoolean()) {
				// name, family, father, mother, born, status, sex, travel, horny, loyal, homo,
				// interracial, line, membership, hornyAge, stableAge, mourningTime
				child = new Person(Namer.getName(true, Racist.dwarf), person.family, getFather(person, partner),
						getMother(person, partner), date, Status.LEGAL_CHILD, true, rand.nextDouble(),
						Stater.horny(person), Stater.loyal(person), Stater.homo(person), Stater.interracial(person), 1,
						1, Stater.childFrom(person), Stater.wantChild(person), EldrichOnes.mourningTime(),
						Stater.attachment(person), Stater.poliamoric(person), Stater.jealous(person),
						Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			} else {
				child = new Person(Namer.getName(false, Racist.dwarf), person.family, getFather(person, partner),
						getMother(person, partner), date, Status.LEGAL_CHILD, false, rand.nextDouble(),
						Stater.horny(person), Stater.loyal(person), Stater.homo(person), Stater.interracial(person), 1,
						1, Stater.childFrom(person), Stater.wantChild(person), EldrichOnes.mourningTime(),
						Stater.attachment(person), Stater.poliamoric(person), Stater.jealous(person),
						Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			}
		} else if (person.raceObj == Racist.half) {
			if (rand.nextBoolean()) {
				child = new Person(Namer.getName(true, Racist.half), person.family, getFather(person, partner),
						getMother(person, partner), date, Status.LEGAL_CHILD, true, rand.nextDouble(),
						Stater.horny(person), Stater.loyal(person), Stater.homo(person), Stater.interracial(person), 1,
						1, Stater.childFrom(person), Stater.wantChild(person), EldrichOnes.mourningTime(),
						Stater.attachment(person), Stater.poliamoric(person), Stater.jealous(person),
						Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			} else {
				child = new Person(Namer.getName(false, Racist.half), person.family, getFather(person, partner),
						getMother(person, partner), date, Status.LEGAL_CHILD, false, rand.nextDouble(),
						Stater.horny(person), Stater.loyal(person), Stater.homo(person), Stater.interracial(person), 1,
						1, Stater.childFrom(person), Stater.wantChild(person), EldrichOnes.mourningTime(),
						Stater.attachment(person), Stater.poliamoric(person), Stater.jealous(person),
						Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			}
		} else if (person.raceObj == Racist.gnome) {
			if (rand.nextBoolean()) {
				child = new Person(Namer.getName(true, Racist.gnome), person.family, getFather(person, partner),
						getMother(person, partner), date, Status.LEGAL_CHILD, true, rand.nextDouble(),
						Stater.horny(person), Stater.loyal(person), Stater.homo(person), Stater.interracial(person), 1,
						1, Stater.childFrom(person), Stater.wantChild(person), EldrichOnes.mourningTime(),
						Stater.attachment(person), Stater.poliamoric(person), Stater.jealous(person),
						Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			} else {
				child = new Person(Namer.getName(false, Racist.gnome), person.family, getFather(person, partner),
						getMother(person, partner), date, Status.LEGAL_CHILD, false, rand.nextDouble(),
						Stater.horny(person), Stater.loyal(person), Stater.homo(person), Stater.interracial(person), 1,
						1, Stater.childFrom(person), Stater.wantChild(person), EldrichOnes.mourningTime(),
						Stater.attachment(person), Stater.poliamoric(person), Stater.jealous(person),
						Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			}
		} else {
			if (rand.nextBoolean()) {
				child = new Person(Namer.getName(true, Racist.peura), person.family, getFather(person, partner),
						getMother(person, partner), date, Status.LEGAL_CHILD, true, rand.nextDouble(),
						Stater.horny(person), Stater.loyal(person), Stater.homo(person), Stater.interracial(person), 1,
						1, Stater.childFrom(person), Stater.wantChild(person), EldrichOnes.mourningTime(),
						Stater.attachment(person), Stater.poliamoric(person), Stater.jealous(person),
						Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			} else {
				child = new Person(Namer.getName(false, Racist.peura), person.family, getFather(person, partner),
						getMother(person, partner), date, Status.LEGAL_CHILD, false, rand.nextDouble(),
						Stater.horny(person), Stater.loyal(person), Stater.homo(person), Stater.interracial(person), 1,
						1, Stater.childFrom(person), Stater.wantChild(person), EldrichOnes.mourningTime(),
						Stater.attachment(person), Stater.poliamoric(person), Stater.jealous(person),
						Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			}
		}
		return child;
	}

	public static Person generateCompatibilePlebsPerson(Person person, boolean toMarry, boolean familyAllowsHomo) {
//		Race race = interracialCheck(person) || interracialCheck(person) ? Racer.randomRace() : person.raceObj;
		Race race = null;
		boolean female = true;
		if (toMarry) {
			if (familyAllowsHomo) {
				race = Racist.randomRace(person.raceObj);
				female = rand.nextBoolean();

			} else {
				race = person.raceObj;
				female = !person.sex;
			}
		} else {
			race = Checker.interracialCheck(person) || Checker.interracialCheck(person)
					|| Checker.interracialCheck(person) || Checker.interracialCheck(person)
					|| Checker.interracialCheck(person) ? Racist.randomRace(person.raceObj) : person.raceObj;
			female = rand.nextBoolean();
		}
		Person other = null;
		double lifeMoment = (person.age / person.raceObj.lifespan) * (0.8 + 0.4 * rand.nextDouble());
		int partnerAge = Math.max((int) (lifeMoment * race.lifespan), race.childFrom);
		Calendar date = Timer.getDate(Timer.currentCalendar.get(Calendar.YEAR));
		date.add(Calendar.YEAR, 0 - partnerAge);
		if (race == Racist.human) {
			if (female) {
				other = new Person(Namer.getName(true, Racist.human), Familiar.humanPlebs, null, null, date,
						Status.LEGAL_CHILD, true, rand.nextDouble(), Stater.horny(person), Stater.loyal(person),
						Stater.homo(person), Stater.interracial(person), 1, 1, Stater.childFrom(person),
						Stater.wantChild(person), EldrichOnes.mourningTime(), Stater.attachment(person), Stater.poliamoric(person),
						Stater.jealous(person), Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			} else {
				other = new Person(Namer.getName(false, Racist.human), Familiar.humanPlebs, null, null, date,
						Status.LEGAL_CHILD, false, rand.nextDouble(), Stater.horny(person), Stater.loyal(person),
						Stater.homo(person), Stater.interracial(person), 1, 1, Stater.childFrom(person),
						Stater.wantChild(person), EldrichOnes.mourningTime(), Stater.attachment(person), Stater.poliamoric(person),
						Stater.jealous(person), Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			}
		} else if (race == Racist.elf) {
			if (female) {
				other = new Person(Namer.getName(true, Racist.elf), Familiar.elfPlebs, null, null, date,
						Status.LEGAL_CHILD, true, rand.nextDouble(), Stater.horny(person), Stater.loyal(person),
						Stater.homo(person), Stater.interracial(person), 1, 1, Stater.childFrom(person),
						Stater.wantChild(person), EldrichOnes.mourningTime(), Stater.attachment(person), Stater.poliamoric(person),
						Stater.jealous(person), Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			} else {
				other = new Person(Namer.getName(false, Racist.elf), Familiar.elfPlebs, null, null, date,
						Status.LEGAL_CHILD, false, rand.nextDouble(), Stater.horny(person), Stater.loyal(person),
						Stater.homo(person), Stater.interracial(person), 1, 1, Stater.childFrom(person),
						Stater.wantChild(person), EldrichOnes.mourningTime(), Stater.attachment(person), Stater.poliamoric(person),
						Stater.jealous(person), Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			}
//			other.name += (" " + middleNames[rand.nextInt(middleNames.length)]);
		} else if (race == Racist.dwarf) {
			if (female) {
				other = new Person(Namer.getName(true, Racist.dwarf), Familiar.dwarfPlebs, null, null, date,
						Status.LEGAL_CHILD, true, rand.nextDouble(), Stater.horny(person), Stater.loyal(person),
						Stater.homo(person), Stater.interracial(person), 1, 1, Stater.childFrom(person),
						Stater.wantChild(person), EldrichOnes.mourningTime(), Stater.attachment(person), Stater.poliamoric(person),
						Stater.jealous(person), Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			} else {
				other = new Person(Namer.getName(false, Racist.dwarf), Familiar.dwarfPlebs, null, null, date,
						Status.LEGAL_CHILD, false, rand.nextDouble(), Stater.horny(person), Stater.loyal(person),
						Stater.homo(person), Stater.interracial(person), 1, 1, Stater.childFrom(person),
						Stater.wantChild(person), EldrichOnes.mourningTime(), Stater.attachment(person), Stater.poliamoric(person),
						Stater.jealous(person), Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			}
		} else if (race == Racist.half) {
			if (female) {
				other = new Person(Namer.getName(true, Racist.half), Familiar.halflingPlebs, null, null, date,
						Status.LEGAL_CHILD, true, rand.nextDouble(), Stater.horny(person), Stater.loyal(person),
						Stater.homo(person), Stater.interracial(person), 1, 1, Stater.childFrom(person),
						Stater.wantChild(person), EldrichOnes.mourningTime(), Stater.attachment(person), Stater.poliamoric(person),
						Stater.jealous(person), Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			} else {
				other = new Person(Namer.getName(false, Racist.half), Familiar.halflingPlebs, null, null, date,
						Status.LEGAL_CHILD, false, rand.nextDouble(), Stater.horny(person), Stater.loyal(person),
						Stater.homo(person), Stater.interracial(person), 1, 1, Stater.childFrom(person),
						Stater.wantChild(person), EldrichOnes.mourningTime(), Stater.attachment(person), Stater.poliamoric(person),
						Stater.jealous(person), Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			}
		} else if (race == Racist.gnome) {
			if (female) {
				other = new Person(Namer.getName(true, Racist.gnome), Familiar.gnomePlebs, null, null, date,
						Status.LEGAL_CHILD, true, rand.nextDouble(), Stater.horny(person), Stater.loyal(person),
						Stater.homo(person), Stater.interracial(person), 1, 1, Stater.childFrom(person),
						Stater.wantChild(person), EldrichOnes.mourningTime(), Stater.attachment(person), Stater.poliamoric(person),
						Stater.jealous(person), Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			} else {
				other = new Person(Namer.getName(false, Racist.gnome), Familiar.gnomePlebs, null, null, date,
						Status.LEGAL_CHILD, false, rand.nextDouble(), Stater.horny(person), Stater.loyal(person),
						Stater.homo(person), Stater.interracial(person), 1, 1, Stater.childFrom(person),
						Stater.wantChild(person), EldrichOnes.mourningTime(), Stater.attachment(person), Stater.poliamoric(person),
						Stater.jealous(person), Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			}
		} else if (race == Racist.peura) {
			if (female) {
				other = new Person(Namer.getName(true, Racist.peura), Familiar.peuraPlebs, null, null, date,
						Status.LEGAL_CHILD, true, rand.nextDouble(), Stater.horny(person), Stater.loyal(person),
						Stater.homo(person), Stater.interracial(person), 1, 1, Stater.childFrom(person),
						Stater.wantChild(person), EldrichOnes.mourningTime(), Stater.attachment(person), Stater.poliamoric(person),
						Stater.jealous(person), Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			} else {
				other = new Person(Namer.getName(false, Racist.peura), Familiar.peuraPlebs, null, null, date,
						Status.LEGAL_CHILD, false, rand.nextDouble(), Stater.horny(person), Stater.loyal(person),
						Stater.homo(person), Stater.interracial(person), 1, 1, Stater.childFrom(person),
						Stater.wantChild(person), EldrichOnes.mourningTime(), Stater.attachment(person), Stater.poliamoric(person),
						Stater.jealous(person), Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			}
		} else if (race == Racist.blossom) {
			if (female) {
				other = new Person(Namer.getName(true, Racist.blossom), Familiar.blossomPlebs, null, null, date,
						Status.LEGAL_CHILD, true, rand.nextDouble(), Stater.horny(person), Stater.loyal(person),
						Stater.homo(person), Stater.interracial(person), 1, 1, Stater.childFrom(person),
						Stater.wantChild(person), EldrichOnes.mourningTime(), Stater.attachment(person), Stater.poliamoric(person),
						Stater.jealous(person), Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			} else {
				other = new Person(Namer.getName(false, Racist.blossom), Familiar.blossomPlebs, null, null, date,
						Status.LEGAL_CHILD, false, rand.nextDouble(), Stater.horny(person), Stater.loyal(person),
						Stater.homo(person), Stater.interracial(person), 1, 1, Stater.childFrom(person),
						Stater.wantChild(person), EldrichOnes.mourningTime(), Stater.attachment(person), Stater.poliamoric(person),
						Stater.jealous(person), Stater.impulsive(person), Stater.proud(person), Stater.amorous(person));
			}
		}
		other.plannedKids = 0;
		other.plebs = true;
		if (rand.nextDouble() < 0.1 && !toMarry) {
			Calendar someDate = (Calendar) Timer.currentCalendar.clone();
			someDate.add(Calendar.YEAR, -3);
			other.relationships.add(new Relationship(RelationType.LEGAL, other, null, someDate));
		}
		return other;
	}

	private static void downgrade(Person person, Person child, boolean illegal) {
		for (Person kid : person.kids) {
			if (kid.status == Status.LEGAL_CHILD
					|| ((kid.status == Status.POSSIBLE_BASTARD || kid.status == Status.OUT_MARRIAGE) && illegal))
				child.lineImportance *= 0.95;
		}
	}

	public static void addKid(Person person, Person child) {
		if (!person.kids.contains(child)) {
			person.kids.add(child);
		}
		if (person.isMarried() && person.getMarriage().getPartner(person) != null
				&& !person.getMarriage().getPartner(person).kids.contains(child)) {
			person.getMarriage().getPartner(person).kids.add(child);
		}
	}

	private static Person getFather(Person person, Person partner) {
		if (person != null && !person.sex)
			return person;
		if (partner != null && !partner.sex)
			return partner;
		return null;
	}

	private static Person getMother(Person person, Person partner) {
		if (person != null && person.sex)
			return person;
		if (partner != null && partner.sex)
			return partner;
		return null;
	}

	public static void handleKnownKids(Person person) {
		for (int i = 0; i < person.knownKids.size(); i++) {
			Person kid = person.knownKids.get(i);
			if (Timer.currentCalendar.after(kid.born)) {
				// i--;
				person.knownKids.remove(kid);
				person.kids.add(kid);
				person.family.addMember(kid);
				if (person.isMarried()) {
					Person partner = person.getMarriage().getPartner(person);
					partner.kids.add(kid);
					if (kid.mother == null)
						kid.mother = partner;
					if (kid.father == null)
						kid.father = partner;
				}
				System.out.println(kid.getName() + " - " + person.getName() + " - "
						+ (person.isMarried() ? person.getMarriage().getPartner(person).getName() : ""));
			}
		}
	}
//	public static void handlePregnancy(Person person) {
//		} else if (person.status == Status.LEGAL_CHILD || (person.lineImportance > 0.4)) {
//		if (person.isMarried() && person.raceObj == person.getMarriage().getPartner(person).raceObj) {
//			if (rand.nextDouble() < 0.85) {
//				legalChild(person, child);
//				child.officialFather = person.getMarriage().getPartner(person);
//				if (person.childFather != null)
//					person.childFather.kids.remove(child);
//			} else {
//				if (rand.nextDouble() < 0.9) {
//					possibleBastard(person, child);
//					child.officialFather = person.getMarriage().getPartner(person);
//					if (person.childFather != null)
//						person.childFather.kids.remove(child);
//				} else {
//					if (person.childFather == null) {
//						bastard(person, child);
//					} else if (rand.nextBoolean()) {
//						outMarriage(person, child);
//						child.officialFather = person.getMarriage().getPartner(person);
//						person.childFather.kids.remove(child);
//					} else {
//						outMarriage(person.childFather, child);
//						Relationship fatherMarriage = person.childFather.getMarriage();
//						if (fatherMarriage != null) {
//							child.mother = fatherMarriage.getPartner(person.childFather);
//						}
//						// child.officialFather = person.currentLegalPartner;
//						person.kids.remove(child);
//					}
//				}
//			}
//		} else {
//			if (person.childFather != null && rand.nextBoolean()) {
//				if (rand.nextDouble() < 0.6) {
//					outMarriage(person, child);
//					child.officialFather = person.isMarried() ? person.getMarriage().getPartner(person) : null;
//					person.childFather.kids.remove(child);
//				} else {
//					outMarriage(person.childFather, child);
//					child.mother = person.childFather.isMarried() ? person.childFather.getMarriage().getPartner(person.childFather) : null;
//					// child.officialFather = person.currentLegalPartner;
//					person.kids.remove(child);
//				}
//			} else {
//				bastard(person, child);
//			}
//		}
//		if (child.lineImportance < 0.3) {
//			child.family.aliveMembers.remove(child);
//			if (child.mother != null)
//				child.mother.kids.remove(child);
//			if (child.father != null)
//				child.father.kids.remove(child);
//			if (child.officialFather != null)
//				child.officialFather.kids.remove(child);
//		}
//	} else {
//		person.weakBloodKid++;
//	}
//	}

}
