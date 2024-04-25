package royal.util.simple;

import royal.model.Family;
import royal.model.Person;
import royal.model.Person.Status;
import royal.util.Familiar;
import royal.util.Marriager;

import java.util.Random;

public class EldrichOnes {
	public static Random rand = new Random();

	public static void createNestor(Family family) {
		Person person1;
		Person person2;
		if (family.raceObj == Racist.human) {
			int born = family.creationDate - (20 + rand.nextInt(25));
			if (rand.nextBoolean()) {
				// name, family, father, mother, born, status, sex, travel, horny, loyal, homo,
				// interracial, line, membership, hornyAge, stableAge, mourningTime
				person1 = new Person(Namer.getName(true, Racist.human), family, null, null,
						Timer.getDate(born + rand.nextInt(4)), Status.LEGAL_CHILD, true, 0.7,
						0.4 + 0.6 * rand.nextDouble(), 0.4 + 0.6 * rand.nextDouble(), 0.2 + 0.3 * rand.nextDouble(),
						0.1 + 0.3 * rand.nextDouble(), 1, 1, 17, 19, mourningTime(), Stater.attachment(null),
						Stater.poliamoric(null), Stater.jealous(null), Stater.impulsive(null), Stater.proud(null),
						Stater.amorous(null));
				person2 = new Person(Namer.getName(false, Racist.human), Familiar.humanPlebs, null, null,
						Timer.getDate(born), Status.LEGAL_CHILD, false, 0.7, 0.4 + 0.6 * rand.nextDouble(),
						0.4 + 0.6 * rand.nextDouble(), 0.2 + 0.3 * rand.nextDouble(), 0.1 + 0.3 * rand.nextDouble(), 1,
						1, 17, 19, mourningTime(), Stater.attachment(null), Stater.poliamoric(null),
						Stater.jealous(null), Stater.impulsive(null), Stater.proud(null), Stater.amorous(null));
			} else {
				person1 = new Person(Namer.getName(false, Racist.human), family, null, null, Timer.getDate(born),
						Status.LEGAL_CHILD, false, 0.7, 0.4 + 0.6 * rand.nextDouble(), 0.4 + 0.6 * rand.nextDouble(),
						0.2 + 0.3 * rand.nextDouble(), 0.1 + 0.3 * rand.nextDouble(), 1, 1, 17, 19, mourningTime(),
						Stater.attachment(null), Stater.poliamoric(null), Stater.jealous(null), Stater.impulsive(null),
						Stater.proud(null), Stater.amorous(null));
				person2 = new Person(Namer.getName(true, Racist.human), Familiar.humanPlebs, null, null,
						Timer.getDate(born + rand.nextInt(4)), Status.LEGAL_CHILD, true, 0.7,
						0.4 + 0.6 * rand.nextDouble(), 0.4 + 0.6 * rand.nextDouble(), 0.2 + 0.3 * rand.nextDouble(),
						0.1 + 0.3 * rand.nextDouble(), 1, 1, 17, 19, mourningTime(), Stater.attachment(null),
						Stater.poliamoric(null), Stater.jealous(null), Stater.impulsive(null), Stater.proud(null),
						Stater.amorous(null));
			}
		} else if (family.raceObj == Racist.elf) {
			int born = family.creationDate - (40 + rand.nextInt(300));
			if (rand.nextBoolean()) {
				// name, family, father, mother, born, status, sex, travel, horny, loyal, homo,
				// interracial, line, membership, hornyAge, stableAge, mourningTime
				person1 = new Person(Namer.getName(true, Racist.elf), family, null, null,
						Timer.getDate(born + rand.nextInt(10)), Status.LEGAL_CHILD, true, 0.7,
						0.4 + 0.6 * rand.nextDouble(), 0.4 + 0.6 * rand.nextDouble(), 0.2 + 0.3 * rand.nextDouble(),
						0.1 + 0.3 * rand.nextDouble(), 1, 1, 60, 64, mourningTime(), Stater.attachment(null),
						Stater.poliamoric(null), Stater.jealous(null), Stater.impulsive(null), Stater.proud(null),
						Stater.amorous(null));
				person2 = new Person(Namer.getName(false, Racist.elf), Familiar.elfPlebs, null, null,
						Timer.getDate(born), Status.LEGAL_CHILD, false, 0.7, 0.4 + 0.6 * rand.nextDouble(),
						0.4 + 0.6 * rand.nextDouble(), 0.2 + 0.3 * rand.nextDouble(), 0.1 + 0.3 * rand.nextDouble(), 1,
						1, 60, 64, mourningTime(), Stater.attachment(null), Stater.poliamoric(null), Stater.jealous(null),
						Stater.impulsive(null), Stater.proud(null), Stater.amorous(null));
			} else {
				person1 = new Person(Namer.getName(false, Racist.elf), family, null, null, Timer.getDate(born),
						Status.LEGAL_CHILD, false, 0.7, 0.4 + 0.6 * rand.nextDouble(), 0.4 + 0.6 * rand.nextDouble(),
						0.2 + 0.3 * rand.nextDouble(), 0.1 + 0.3 * rand.nextDouble(), 1, 1, 60, 64, mourningTime(),
						Stater.attachment(null), Stater.poliamoric(null), Stater.jealous(null), Stater.impulsive(null),
						Stater.proud(null), Stater.amorous(null));
				person2 = new Person(Namer.getName(true, Racist.elf), Familiar.elfPlebs, null, null,
						Timer.getDate(born + rand.nextInt(10)), Status.LEGAL_CHILD, true, 0.7,
						0.4 + 0.6 * rand.nextDouble(), 0.4 + 0.6 * rand.nextDouble(), 0.2 + 0.3 * rand.nextDouble(),
						0.1 + 0.3 * rand.nextDouble(), 1, 1, 60, 64, mourningTime(), Stater.attachment(null),
						Stater.poliamoric(null), Stater.jealous(null), Stater.impulsive(null), Stater.proud(null),
						Stater.amorous(null));
			}
//			person1.name += (" " + middleNames[rand.nextInt(middleNames.length)]);
//			person2.name += (" " + middleNames[rand.nextInt(middleNames.length)]);
		} else if (family.raceObj == Racist.dwarf) {
			Person father1 = new Person(Namer.getName(false, Racist.dwarf), family, null, null, null, null, false, 0, 0,
					0, 0, 0, 0, 0, 0, 0, mourningTime(), Stater.attachment(null), Stater.poliamoric(null), Stater.jealous(null),
					Stater.impulsive(null), Stater.proud(null), Stater.amorous(null));
			Person father2 = new Person(Namer.getName(false, Racist.dwarf), Familiar.dwarfPlebs, null, null, null, null,
					false, 0, 0, 0, 0, 0, 0, 0, 0, 0, mourningTime(), Stater.attachment(null), Stater.poliamoric(null),
					Stater.jealous(null), Stater.impulsive(null), Stater.proud(null), Stater.amorous(null));
			int born = family.creationDate - (40 + rand.nextInt(140));
			if (rand.nextBoolean()) {
				// name, family, father, mother, born, status, sex, travel, horny, loyal, homo,
				// interracial, line, membership, hornyAge, stableAge, mourningTime
				person1 = new Person(Namer.getName(true, Racist.dwarf), family, father1, null,
						Timer.getDate(born + rand.nextInt(3)), Status.LEGAL_CHILD, true, 0.7,
						0.4 + 0.6 * rand.nextDouble(), 0.4 + 0.6 * rand.nextDouble(), 0.2 + 0.3 * rand.nextDouble(),
						0.1 + 0.3 * rand.nextDouble(), 1, 1, 60, 64, mourningTime(), Stater.attachment(null),
						Stater.poliamoric(null), Stater.jealous(null), Stater.impulsive(null), Stater.proud(null),
						Stater.amorous(null));
				person2 = new Person(Namer.getName(false, Racist.dwarf), Familiar.dwarfPlebs, father2, null,
						Timer.getDate(born), Status.LEGAL_CHILD, false, 0.7, 0.4 + 0.6 * rand.nextDouble(),
						0.4 + 0.6 * rand.nextDouble(), 0.2 + 0.3 * rand.nextDouble(), 0.1 + 0.3 * rand.nextDouble(), 1,
						1, 60, 64, mourningTime(), Stater.attachment(null), Stater.poliamoric(null), Stater.jealous(null),
						Stater.impulsive(null), Stater.proud(null), Stater.amorous(null));
			} else {
				person1 = new Person(Namer.getName(false, Racist.dwarf), family, father1, null, Timer.getDate(born),
						Status.LEGAL_CHILD, false, 0.7, 0.4 + 0.6 * rand.nextDouble(), 0.4 + 0.6 * rand.nextDouble(),
						0.2 + 0.3 * rand.nextDouble(), 0.1 + 0.3 * rand.nextDouble(), 1, 1, 60, 64, mourningTime(),
						Stater.attachment(null), Stater.poliamoric(null), Stater.jealous(null), Stater.impulsive(null),
						Stater.proud(null), Stater.amorous(null));
				person2 = new Person(Namer.getName(true, Racist.dwarf), Familiar.dwarfPlebs, father2, null,
						Timer.getDate(born + rand.nextInt(3)), Status.LEGAL_CHILD, true, 0.7,
						0.4 + 0.6 * rand.nextDouble(), 0.4 + 0.6 * rand.nextDouble(), 0.2 + 0.3 * rand.nextDouble(),
						0.1 + 0.3 * rand.nextDouble(), 1, 1, 60, 64, mourningTime(), Stater.attachment(null),
						Stater.poliamoric(null), Stater.jealous(null), Stater.impulsive(null), Stater.proud(null),
						Stater.amorous(null));
			}
		} else if (family.raceObj == Racist.half) {
			int born = family.creationDate - (20 + rand.nextInt(65));
			if (rand.nextBoolean()) {
				// name, family, father, mother, born, status, sex, travel, horny, loyal, homo,
				// interracial, line, membership, hornyAge, stableAge, mourningTime
				person1 = new Person(Namer.getName(true, Racist.half), family, null, null,
						Timer.getDate(born + rand.nextInt(4)), Status.LEGAL_CHILD, true, 0.7,
						0.4 + 0.6 * rand.nextDouble(), 0.4 + 0.6 * rand.nextDouble(), 0.2 + 0.3 * rand.nextDouble(),
						0.1 + 0.3 * rand.nextDouble(), 1, 1, 17, 19, mourningTime(), Stater.attachment(null),
						Stater.poliamoric(null), Stater.jealous(null), Stater.impulsive(null), Stater.proud(null),
						Stater.amorous(null));
				person2 = new Person(Namer.getName(false, Racist.half), Familiar.halflingPlebs, null, null,
						Timer.getDate(born), Status.LEGAL_CHILD, false, 0.7, 0.4 + 0.6 * rand.nextDouble(),
						0.4 + 0.6 * rand.nextDouble(), 0.2 + 0.3 * rand.nextDouble(), 0.1 + 0.3 * rand.nextDouble(), 1,
						1, 17, 19, mourningTime(), Stater.attachment(null), Stater.poliamoric(null), Stater.jealous(null),
						Stater.impulsive(null), Stater.proud(null), Stater.amorous(null));
			} else {
				person1 = new Person(Namer.getName(false, Racist.half), family, null, null, Timer.getDate(born),
						Status.LEGAL_CHILD, false, 0.7, 0.4 + 0.6 * rand.nextDouble(), 0.4 + 0.6 * rand.nextDouble(),
						0.2 + 0.3 * rand.nextDouble(), 0.1 + 0.3 * rand.nextDouble(), 1, 1, 17, 19, mourningTime(),
						Stater.attachment(null), Stater.poliamoric(null), Stater.jealous(null), Stater.impulsive(null),
						Stater.proud(null), Stater.amorous(null));
				person2 = new Person(Namer.getName(true, Racist.half), Familiar.halflingPlebs, null, null,
						Timer.getDate(born + rand.nextInt(4)), Status.LEGAL_CHILD, true, 0.7,
						0.4 + 0.6 * rand.nextDouble(), 0.4 + 0.6 * rand.nextDouble(), 0.2 + 0.3 * rand.nextDouble(),
						0.1 + 0.3 * rand.nextDouble(), 1, 1, 17, 19, mourningTime(), Stater.attachment(null),
						Stater.poliamoric(null), Stater.jealous(null), Stater.impulsive(null), Stater.proud(null),
						Stater.amorous(null));
			}
		} else if (family.raceObj == Racist.gnome) {
			int born = family.creationDate - (30 + rand.nextInt(130));
			if (rand.nextBoolean()) {
				// name, family, father, mother, born, status, sex, travel, horny, loyal, homo,
				// interracial, line, membership, hornyAge, stableAge, mourningTime
				person1 = new Person(Namer.getName(true, Racist.gnome), family, null, null,
						Timer.getDate(born + rand.nextInt(4)), Status.LEGAL_CHILD, true, 0.7,
						0.4 + 0.6 * rand.nextDouble(), 0.4 + 0.6 * rand.nextDouble(), 0.2 + 0.3 * rand.nextDouble(),
						0.1 + 0.3 * rand.nextDouble(), 1, 1, 17, 19, mourningTime(), Stater.attachment(null),
						Stater.poliamoric(null), Stater.jealous(null), Stater.impulsive(null), Stater.proud(null),
						Stater.amorous(null));
				person2 = new Person(Namer.getName(false, Racist.gnome), Familiar.gnomePlebs, null, null,
						Timer.getDate(born), Status.LEGAL_CHILD, false, 0.7, 0.4 + 0.6 * rand.nextDouble(),
						0.4 + 0.6 * rand.nextDouble(), 0.2 + 0.3 * rand.nextDouble(), 0.1 + 0.3 * rand.nextDouble(), 1,
						1, 17, 19, mourningTime(), Stater.attachment(null), Stater.poliamoric(null), Stater.jealous(null),
						Stater.impulsive(null), Stater.proud(null), Stater.amorous(null));
			} else {
				person1 = new Person(Namer.getName(false, Racist.gnome), family, null, null, Timer.getDate(born),
						Status.LEGAL_CHILD, false, 0.7, 0.4 + 0.6 * rand.nextDouble(), 0.4 + 0.6 * rand.nextDouble(),
						0.2 + 0.3 * rand.nextDouble(), 0.1 + 0.3 * rand.nextDouble(), 1, 1, 17, 19, mourningTime(),
						Stater.attachment(null), Stater.poliamoric(null), Stater.jealous(null), Stater.impulsive(null),
						Stater.proud(null), Stater.amorous(null));
				person2 = new Person(Namer.getName(true, Racist.gnome), Familiar.gnomePlebs, null, null,
						Timer.getDate(born + rand.nextInt(4)), Status.LEGAL_CHILD, true, 0.7,
						0.4 + 0.6 * rand.nextDouble(), 0.4 + 0.6 * rand.nextDouble(), 0.2 + 0.3 * rand.nextDouble(),
						0.1 + 0.3 * rand.nextDouble(), 1, 1, 17, 19, mourningTime(), Stater.attachment(null),
						Stater.poliamoric(null), Stater.jealous(null), Stater.impulsive(null), Stater.proud(null),
						Stater.amorous(null));
			}
		} else {
			int born = family.creationDate - (40 + rand.nextInt(70));
			if (rand.nextBoolean()) {
				// name, family, father, mother, born, status, sex, travel, horny, loyal, homo,
				// interracial, line, membership, hornyAge, stableAge, mourningTime
				person1 = new Person(Namer.getName(true, Racist.peura), family, null, null,
						Timer.getDate(born + rand.nextInt(4)), Status.LEGAL_CHILD, true, 0.7,
						0.4 + 0.6 * rand.nextDouble(), 0.4 + 0.6 * rand.nextDouble(), 0.2 + 0.3 * rand.nextDouble(),
						0.1 + 0.3 * rand.nextDouble(), 1, 1, 17, 19, mourningTime(), Stater.attachment(null),
						Stater.poliamoric(null), Stater.jealous(null), Stater.impulsive(null), Stater.proud(null),
						Stater.amorous(null));
				person2 = new Person(Namer.getName(false, Racist.peura), Familiar.peuraPlebs, null, null,
						Timer.getDate(born), Status.LEGAL_CHILD, false, 0.7, 0.4 + 0.6 * rand.nextDouble(),
						0.4 + 0.6 * rand.nextDouble(), 0.2 + 0.3 * rand.nextDouble(), 0.1 + 0.3 * rand.nextDouble(), 1,
						1, 17, 19, mourningTime(), Stater.attachment(null), Stater.poliamoric(null), Stater.jealous(null),
						Stater.impulsive(null), Stater.proud(null), Stater.amorous(null));
			} else {
				person1 = new Person(Namer.getName(false, Racist.peura), family, null, null, Timer.getDate(born),
						Status.LEGAL_CHILD, false, 0.7, 0.4 + 0.6 * rand.nextDouble(), 0.4 + 0.6 * rand.nextDouble(),
						0.2 + 0.3 * rand.nextDouble(), 0.1 + 0.3 * rand.nextDouble(), 1, 1, 17, 19, mourningTime(),
						Stater.attachment(null), Stater.poliamoric(null), Stater.jealous(null), Stater.impulsive(null),
						Stater.proud(null), Stater.amorous(null));
				person2 = new Person(Namer.getName(true, Racist.peura), Familiar.peuraPlebs, null, null,
						Timer.getDate(born + rand.nextInt(4)), Status.LEGAL_CHILD, true, 0.7,
						0.4 + 0.6 * rand.nextDouble(), 0.4 + 0.6 * rand.nextDouble(), 0.2 + 0.3 * rand.nextDouble(),
						0.1 + 0.3 * rand.nextDouble(), 1, 1, 17, 19, mourningTime(), Stater.attachment(null),
						Stater.poliamoric(null), Stater.jealous(null), Stater.impulsive(null), Stater.proud(null),
						Stater.amorous(null));
			}
		}
		family.addMember(person1);
		Marriager.marriage(person1, person2, Timer.getDate(family.creationDate - rand.nextInt(3) - 2));
		person1.family.nestor = person1;
		person1.plannedKids = 2 + rand.nextInt(3);
	}

	public static int mourningTime() {
		return 2 + rand.nextInt(6);
	}
}
