package royal.util;

import royal.model.Person;
import royal.model.Relationship;
import royal.util.simple.Checker;
import royal.util.simple.Killer;
import royal.util.simple.Stater;
import royal.util.simple.Timer;

import java.util.*;

public class Relater {
	public static Random rand = new Random();
	public static List<Relationship> relationships = new ArrayList<>();
	static double incestChance = 0.02;

	public static void add(Relationship rel) {
		relationships.add(rel);
	}

	public static void endAllSideRelations(Relationship relation) {
		Set<Relationship> rels = new HashSet<>();
		rels.addAll(relation.first.getAffairs());
		rels.addAll(relation.second.getAffairs());
		rels.addAll(relation.first.getFwbs());
		rels.addAll(relation.second.getFwbs());
		for (Relationship relationship : rels) {
			if (relationship.isActive())
				relationship.end((Calendar) Timer.currentCalendar.clone());
		}
	}

	public static boolean isAlmostCompatibile(Person person, Person other) {
		boolean ageIsGood = Math
				.abs((person.age / person.raceObj.lifespan) - (other.age / other.raceObj.lifespan)) < 0.4
				|| (Checker.amorousCheck(person) && Checker.amorousCheck(person) && Checker.amorousCheck(person)
						&& Checker.amorousCheck(person) && Checker.amorousCheck(other) && Checker.amorousCheck(other)
						&& Checker.amorousCheck(other) && Checker.amorousCheck(other));
		boolean raceIsGood = person.raceObj == other.raceObj
				|| (Checker.interracialCheck(person) && Checker.interracialCheck(other))
				|| (Checker.interracialCheck(person) && Checker.interracialCheck(other));
		boolean homoCheck1 = Checker.homoCheck(person) || Checker.homoCheck(person);
		boolean homoCheck2 = Checker.homoCheck(other) || Checker.homoCheck(other);
		boolean sexIsGood = (homoCheck1 && homoCheck2 && person.sex == other.sex)
				|| (!homoCheck1 && !homoCheck2 && person.sex != other.sex);
		boolean notExtraCloseFamily = !person.kids.contains(other) && !other.kids.contains(person);
		boolean familyLesserChance = ratherNotFamily(person, other);
		return ageIsGood && raceIsGood && sexIsGood && notExtraCloseFamily && familyLesserChance;
	}

	public static boolean isCompatibile(Person person, Person other) {
		boolean ageIsGood = ageIsGood(person, other);
		boolean raceIsGood = person.raceObj == other.raceObj
				|| (Checker.interracialCheck(person) && Checker.interracialCheck(other));
		boolean homoCheck1 = Checker.homoCheck(person);
		boolean homoCheck2 = Checker.homoCheck(other);
		boolean sexIsGood = (homoCheck1 && homoCheck2 && person.sex == other.sex)
				|| (!homoCheck1 && !homoCheck2 && person.sex != other.sex);
		boolean notExtraCloseFamily = !person.kids.contains(other) && !other.kids.contains(person);
		boolean familyLesserChance = ratherNotFamily(person, other);
		return ageIsGood && raceIsGood && sexIsGood && notExtraCloseFamily && familyLesserChance;
	}

	public static boolean ratherNotFamily(Person person, Person other) {
		return (person.family != other.family && person.parentsFamily != other.family
				&& person.family != other.parentsFamily && person.parentsFamily != other.parentsFamily)
				|| rand.nextDouble() < incestChance;
	}

	public static boolean ageIsGood(Person person, Person other) {
		return Math
				.abs((person.age / person.raceObj.lifespan) - (other.age / other.raceObj.lifespan)) < 0.2
				|| (Checker.amorousCheck(person) && Checker.amorousCheck(person) && Checker.amorousCheck(person)
						&& Checker.amorousCheck(person) && Checker.amorousCheck(other) && Checker.amorousCheck(other)
						&& Checker.amorousCheck(other) && Checker.amorousCheck(other));
	}

	public static boolean isFamilyCompatibile(Person person, Person other) {
		boolean ageIsGood = ageIsGood(person, other);
		boolean raceIsGood = person.raceObj == other.raceObj;
		boolean sexIsGood = person.sex != other.sex;
		boolean notExtraCloseFamily = !person.kids.contains(other) && !other.kids.contains(person);
		boolean familyLesserChance = (person.family != other.family && person.parentsFamily != other.family
				&& person.family != other.parentsFamily && person.parentsFamily != other.parentsFamily);
		return ageIsGood && raceIsGood && sexIsGood && notExtraCloseFamily && familyLesserChance;
	}

	public static void scandal(Person first, Person second) {
//		System.out.println("SKANDAL! " + first.family.surname);
		if (first.isMarried()) {
			handleReactions(first, second);
			handleReactions(second, first);
		}
	}

	public static void handleReactions(Person first, Person second) {
		if (first.isMarried()) {
			Person partner = first.getMarriage().getPartner(first);
			boolean notImportant = notImportantToSpare(first, partner);
			if (rand.nextDouble() < 0.1) {
				if (notImportant) {
					react(first, second, partner);
				} else {
					if (rand.nextDouble() < 0.2) {
						react(first, second, partner);
					}
				}
			}
		}
	}

	public static void react(Person first, Person second, Person partner) {
		if (first.died == null && partner != null) {
			if (rand.nextDouble() < partner.calculateDivorcable()) {
				divorce(first, partner);
			} else if (rand.nextDouble() < partner.calculateRevenge()) {
				if (rand.nextDouble() < 0.03) {
					Killer.kill(first, Timer.currentCalendar);
					if (second.died == null)
						Killer.kill(second, Timer.currentCalendar);
				} else {
					if (rand.nextDouble() < 0.6 || second.died != null) {
						Killer.kill(first, Timer.currentCalendar);
					} else {
						Killer.kill(second, Timer.currentCalendar);
					}
				}
			}
		}
	}

	public static boolean notImportantToSpare(Person first, Person partner) {
		if (partner != null) {
			boolean otherSex = first.sex != partner.sex;
			boolean sameRace = first.raceObj == partner.raceObj;
			boolean firstNotSoImportant = rand.nextDouble() > Stater.getImportance(first);
			boolean secondNotSoImportant = rand.nextDouble() > Stater.getImportance(partner);
			boolean notImportant = (!sameRace || !otherSex) || (firstNotSoImportant && secondNotSoImportant);
			return notImportant;
		} else {
			return true;
		}
	}

	public static void divorce(Person first, Person partner) {
		partner.getMarriage().end((Calendar) Timer.currentCalendar.clone());
		backHome(first, partner);
	}

	public static void backHome(Person first, Person partner) {
		if (first.parentsFamily == first.family) {
			partner.family = partner.parentsFamily;
			first.family.aliveMembers.remove(partner);
			partner.family.addMember(partner);
			partner.lineImportance = partner.father != null && partner.mother != null
					? 0.25 * (partner.father.lineImportance + partner.mother.lineImportance)
					: partner.father != null ? partner.father.lineImportance * 0.5
							: partner.mother != null ? partner.mother.lineImportance * 0.5 : 0.05;
		} else {
			first.family = first.parentsFamily;
			partner.family.aliveMembers.remove(first);
			first.family.addMember(first);
			first.lineImportance = first.father != null && first.mother != null
					? 0.25 * (first.father.lineImportance + first.mother.lineImportance)
					: first.father != null ? first.father.lineImportance * 0.5
							: first.mother != null ? first.mother.lineImportance * 0.5 : 0.05;
		}
	}

}
