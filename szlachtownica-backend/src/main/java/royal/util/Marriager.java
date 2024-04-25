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
		if (person.raceObj == person.family.raceObj && person.age >= person.marriageAge && !person.isMarried()
				&& !person.isMourning(Timer.currentCalendar)) {
			if (person.family.allMembers() > 50) {
				if (!person.singleChecked) {
					person.singleChecked = true;
					if (rand.nextDouble() < person.family.allMembers() / 2) {
						person.waitsAsSingle = true;
					} else {
						person.waitsAsSingle = false;
					}
				}
				if (person.waitsAsSingle) {
					return;
				}
			}
			if (Timer.currentCalendar.get(Calendar.YEAR) < 1200 || rand.nextDouble() < plebsMarriageChance || person.raceObj == Racist.peura) {
				marriage(person, Birther.generateCompatibilePlebsPerson(person, true, familyAllowsHomo));
			} else {
				List<Person> others = new ArrayList<>();
				for (Family f : Familiar.families) {
					if (!f.plebs) {
						for (Person p : f.aliveMembers) {
							if (p != person && p.age >= p.stableAge && Relater.ratherNotFamily(person, p)
									&& Relater.ageIsGood(person, p) && canBeMarried(person, p)) {
								if (familyAllowsHomo || (person.sex != p.sex && person.raceObj == p.raceObj)) {
									others.add(p);
									if (person.family.vassals.contains(p.family)) {
										others.add(p);
									}
								}
							}
						}
					}
				}
				Person other = null;
				for (Person p : others) {
					if (other == null || other.family.trueMembers() < p.family.trueMembers()) {
						other = p;
					}
				}
				if (other != null)
					marriage(person, other);

//				System.out.println(person.getFullName() + " - " + person.age + " - " + others.size());
			}
		}
	}

	public static boolean canBeMarried(Person first, Person second) {
		boolean noPlanned = first.planedMarriage == null && second.planedMarriage == null;
		boolean emptySlots = !first.isMarried() && !first.isMourning(Timer.currentCalendar) && !second.isMarried()
				&& !second.isMourning(Timer.currentCalendar);
		boolean stableAge = first.age >= first.stableAge && second.age >= second.stableAge;
		boolean noRulerAndPlebs = !(first.name.endsWith("I") && second.plebs)
				&& !(second.name.endsWith("I") && first.plebs);
		boolean noParents = !first.kids.contains(second) && !second.kids.contains(first);
//		boolean otherSex = first.sex != second.sex;
//		boolean sameRace = first.raceObj == second.raceObj;
//		boolean firstNotSoImportant = rand.nextDouble() > Stater.getImportance(first);
//		boolean secondNotSoImportant = rand.nextDouble() > Stater.getImportance(first);
//		boolean importanceAllowsIt = (sameRace && otherSex) || (firstNotSoImportant && secondNotSoImportant);
		return noPlanned && emptySlots && stableAge && noRulerAndPlebs && noParents;
	}

	public static void marriage(Person person, Person other) {
		marriage(person, other, Timer.currentCalendar);
	}

	public static void marriage(Person person, Person other, Calendar date) {
		Relationship marriageRel = new Relationship(RelationType.LEGAL, person, other, (Calendar) date.clone());
		Relater.endAllSideRelations(marriageRel);
		Relater.add(marriageRel);
		person.relationships.add(marriageRel);
		other.relationships.add(marriageRel);
		Family targetFamily = findTargetFamily(person, other);
		if (targetFamily.plebs)
			System.out.println("CO JEST KURWA!");
		if (targetFamily != other.family) {
			other.family = targetFamily;
			other.family.aliveMembers.remove(other);
			targetFamily.addMember(other);
			other.lineImportance = person.lineImportance * 0.95;
			other.importance = other.lineImportance * other.family.strenght / 10;
		}
		if (targetFamily != person.family) {
			person.family = targetFamily;
			person.family.aliveMembers.remove(person);
			targetFamily.addMember(person);
			person.lineImportance = other.lineImportance * 0.95;
			person.importance = person.lineImportance * person.family.strenght / 10;
		}
	}

	private static Family findTargetFamily(Person person, Person other) {
		Family personFamily = person.family;
		Family otherFamily = other.family;
		if (person.name.endsWith("I"))
			return personFamily;
		if (other.name.endsWith("I"))
			return otherFamily;
		if (personFamily.plebs && !otherFamily.plebs)
			return otherFamily;
		if (otherFamily.plebs && !personFamily.plebs)
			return personFamily;
		if (person.plebs && !other.plebs)
			return otherFamily;
		if (other.plebs && !person.plebs)
			return personFamily;
		if (other.plebs && person.plebs) {
			System.out.println("WHAT THE FUCK TWO PLEBS?");
			return personFamily;
		}
		if (otherFamily.plebs && personFamily.plebs) {
			System.out.println("WHAT THE FUCK TWO PLEBS FAMILIES? HOW POSSIBLE?");
			return personFamily;
		}
		if (personFamily != person.parentsFamily && otherFamily == other.parentsFamily)
			return otherFamily;
		if (otherFamily != other.parentsFamily && personFamily == person.parentsFamily)
			return personFamily;
		if (otherFamily.safety() < personFamily.safety())
			return otherFamily;
		return personFamily;
	}

}
