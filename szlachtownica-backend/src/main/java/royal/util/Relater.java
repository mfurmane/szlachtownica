package royal.util;

import royal.model.Person;
import royal.model.Relationship;
import royal.util.simple.Checker;
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

	public static boolean isChildrenCompatibile(Person person, Person other) {
		boolean ageIsGood = ageIsGood(person, other);
		boolean raceIsGood = person.raceObj == other.raceObj;
		boolean sexIsGood = person.sex != other.sex;
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

	public static void divorce(Person first, Person partner) {

	}

	public static void backHome(Person first, Person partner) {

	}

}
