package royal.util;

import royal.model.Family;
import royal.model.Person;
import royal.model.Relationship;

import java.util.Random;

public class Fucker {
	private static Random rand = new Random();
	static double plebsSexOpportunityChance = 0.01;
	static double outerMarriageSexChance = 0.3;
	static double moreChildrenChance = 0.3;
	static double moreRelativesChance = 0.3;

	public static void handleSex(Person person, boolean familyNeedChildren) {
		if (person.raceObj == person.family.raceObj && familyNeedChildren && person.kidsInFamily() < 3 && person.isMarried()) {
			Person partner = person.getMarriage().getPartner(person);
			if (partner != null && person.sex != partner.sex && person.raceObj == partner.raceObj) {
				if (person.pregnantCounter == 0 && partner.pregnantCounter == 0) {
					unprotectedSex(person, partner);
				}
			}
		}

//		for (int i = 0; i < person.relationships.size(); i++) {
//			Relationship relation = Relater.relationships.get(i);
//			if (relation.isActive()) {
////				System.out.println(relation.first.getFullName() + " - " + relation.second.getFullName());
//				if (samePlace(person, relation.getPartner(person))) {
//					handleRelation(relation);
//				}
//			}
//		}
//		if (!person.isMarried() || rand.nextDouble() < outerMarriageSexChance) {
//			outerRelationSex(person);
//		}
	}

	public static void sex(Person person, Person partner) {
//		String key = person.getFullName() + "-" + partner.getFullName();
//		if (person.sex != partner.sex) {
//			if (person.sex) {
//				checkPregnancy(person, partner);
//			} else {
//				checkPregnancy(partner, person);
//			}
//		}
	}

	public static void unprotectedSex(Person person, Person partner) {
		if (person.sex) {
			makeChild(person, partner);
		} else {
			makeChild(partner, person);
		}
	}

	public static void handleRelation(Relationship relation) {
//		boolean horny1 = rand.nextDouble() < relation.first.hornyFactor;
//		boolean horny2 = rand.nextDouble() < relation.second.hornyFactor;
//		boolean attachment1 = rand.nextDouble() < relation.first.attachmentFactor;
//		boolean attachment2 = rand.nextDouble() < relation.second.attachmentFactor;
//		switch (relation.type) {
//		case LEGAL:
//			if ((horny1 && horny2) || (horny1 && attachment2) || (horny2 && attachment1)) {
//				sex(relation.first, relation.second);
//			} else {
//				if (relation.first.family.trueNumber() < 5 && relation.first.sex != relation.second.sex
//						&& relation.first.family.raceObj == relation.second.family.raceObj)
//					sex(relation.first, relation.second);
//			}
//			break;
//		case AFFAIR:
//			if (((horny1 && horny2) || (horny1 && attachment2) || (horny2 && attachment1))
//					&& noMarriageOrNotLoyalOrPoliamoricImpulsive(relation.first, relation.second)) {
//				sex(relation.first, relation.second);
//				affairAfterSex(relation);
//			} else {
//				affairNoSex(relation);
//			}
//			break;
//		case FWB:
//			if (horny1 && horny2 && noMarriageOrNotLoyalOrPoliamoricImpulsive(relation.first, relation.second)) {
//				sex(relation.first, relation.second);
//				fwbAfterSex(relation);
//			} else {
//				fwbNoSex(relation);
//			}
//			break;
//		}
	}

//	public static boolean samePlace(Person person, Person partner) {
//		Set<Family> placesInPersonRange = new HashSet<>();
//		if (person.onTravelAt == null) {
//			addWithVassals(person.family, placesInPersonRange);
//		} else {
//			addWithVassals(person.onTravelAt, placesInPersonRange);
//		}
//		Set<Family> placesInPartnerRange = new HashSet<>();
//		if (partner.onTravelAt == null) {
//			addWithVassals(partner.family, placesInPartnerRange);
//		} else {
//			addWithVassals(partner.onTravelAt, placesInPartnerRange);
//		}
//		for (Family family : placesInPersonRange) {
//			for (Family family2 : placesInPartnerRange) {
//				if (family == family2)
//					return true;
//			}
//		}
//		return false;
//	}

//	public static void outerRelationSex(Person person) {
//		if (hornyAndWilling(person) || hornyAndWilling(person)) {
//			checkOpportunities(person);
//		}
//	}

//	public static boolean hornyAndWilling(Person person) {
//		boolean horny = false;
//		boolean noLoves = !(person.isMarried() || person.hasAffairs());
//		if (person.age >= person.hornyAge && !person.isMourning(Timer.currentCalendar)) {
//			boolean freeAndWant = noLoves && (Checker.hornyCheck(person) || Checker.amorousCheck(person));
//			boolean occupiedAndWant = !noLoves && Checker.poliamoricCheck(person)
//					&& (Checker.hornyCheck(person) || Checker.amorousCheck(person));
//			horny = freeAndWant || occupiedAndWant;
//		}
//		boolean side = person.isMarried() && !Checker.attachmentCheck(person) && !Checker.loyalCheck(person);
//		if (side) {
//			for (int i = 0; i < person.getAffairs().size(); i++) {
//				if (Checker.attachmentCheck(person)) {
//					side = false;
//					break;
//				}
//			}
//		}
//		boolean willing = noLoves || side;
//		boolean hornyAndWilling = horny && willing;
//		return hornyAndWilling;
//	}

//	public static void checkOpportunities(Person person) {
//		// TODO possibly need to change
//		List<Person> inLocation = findPeopleInLocationOf(person);
////		System.out.println("######################################################################################################################################################################################");
//		for (Person other : inLocation) {
////			System.out.println(isCompatibile(person, other) + " - " + hornyAndWilling(other, currentCalendar) + " - " + (rand.nextDouble() < likeEachOtherChance));
//			if (other != person && (Relater.isCompatibile(person, other) || Relater.isAlmostCompatibile(person, other))
//					&& hornyAndWilling(other) && rand.nextDouble() < Marriager.likeEachOtherChance) {
//				handleOportunity(person, other);
//			}
//		}
//		if (rand.nextDouble() < plebsSexOpportunityChance) {
//			Person other = Birther.generateCompatibilePlebsPerson(person, false, true);
//			handleOportunity(person, other);
//		}
//	}

//	public static List<Person> findPeopleInLocationOf(Person person) {
//		List<Person> available = new ArrayList<>();
//		Set<Family> placesInRange = new HashSet<>();
//		if (person.onTravelAt == null) {
//			addWithVassals(person.family, placesInRange);
//		} else {
//			addWithVassals(person.onTravelAt, placesInRange);
//		}
//		for (Family place : placesInRange) {
//			for (Person p : place.aliveMembers) {
//				if (p.onTravelAt == null || placesInRange.contains(p.onTravelAt))
//					available.add(p);
//			}
//		}
//		return available;
//	}

//	public static void addWithVassals(Family family, Set<Family> placesInRange) {
//		placesInRange.add(family);
//		for (Family vassal : family.vassals) {
//			if (vassal.strenght < 7)
//				placesInRange.add(vassal);
//		}
//	}

//	public static void handleOportunity(Person person, Person other) {
//		sex(person, other);
//		boolean smallerFamilyChance = person.family != other.family
//				|| (rand.nextDouble() < 0.2 && !person.name.endsWith("I") && !other.name.endsWith("I"));
//		boolean noAttachments = true;
//		for (int i = 0; i < person.getAffairs().size(); i++) {
//			if (Checker.attachmentCheck(person))
//				noAttachments = false;
//		}
//		for (int i = 0; i < other.getAffairs().size(); i++) {
//			if (Checker.attachmentCheck(other))
//				noAttachments = false;
//		}
//		boolean firstImpuls = Checker.amorousCheck(person) && Checker.impulsiveCheck(person);
//		boolean secondImpuls = Checker.amorousCheck(other) && Checker.impulsiveCheck(other);
//		if (firstImpuls && secondImpuls && smallerFamilyChance && Marriager.canBeMarried(person, other)
//				&& noAttachments) {
//			Marriager.marriage(person, other);
//		} else {
//			boolean noMarriageBlocks = (!person.isMarried() || Checker.poliamoricCheck(person))
//					&& (!other.isMarried() || Checker.poliamoricCheck(other));
//			boolean noAffairBlocks = true;
//			for (int i = 0; i < person.getAffairs().size(); i++) {
//				if (!Checker.poliamoricCheck(person) && Checker.attachmentCheck(person))
//					noAffairBlocks = false;
//			}
//			for (int i = 0; i < other.getAffairs().size(); i++) {
//				if (!Checker.poliamoricCheck(other) && Checker.attachmentCheck(other))
//					noAffairBlocks = false;
//			}
//			boolean firstWant = Checker.amorousCheck(person);
//			boolean secondWant = Checker.amorousCheck(other);
//			if (noMarriageBlocks && noAffairBlocks && firstWant && secondWant) {
//				Relationship rel = new Relationship(RelationType.AFFAIR, person, other,
//						(Calendar) Timer.currentCalendar.clone());
//				Relater.relationships.add(rel);
//				person.relationships.add(rel);
//				other.relationships.add(rel);
//			} else {
//				boolean noMarriageBlocks2 = (!person.isMarried()
//						|| (!Checker.loyalCheck(person) && !Checker.attachmentCheck(person)))
//						&& (!other.isMarried() || (!Checker.loyalCheck(person) && !Checker.attachmentCheck(person)));
//				boolean noAffairBlocks2 = true;
//				for (int i = 0; i < person.getAffairs().size(); i++) {
//					if (Checker.attachmentCheck(person))
//						noAffairBlocks2 = false;
//				}
//				for (int i = 0; i < other.getAffairs().size(); i++) {
//					if (Checker.attachmentCheck(other))
//						noAffairBlocks2 = false;
//				}
//				if (noAffairBlocks2 && noMarriageBlocks2 && Checker.hornyCheck(person) && Checker.hornyCheck(other)
//						&& Checker.hornyCheck(person) && Checker.hornyCheck(other)) {
//					Relationship rel = new Relationship(RelationType.FWB, person, other,
//							(Calendar) Timer.currentCalendar.clone());
//					Relater.relationships.add(rel);
//					person.relationships.add(rel);
//					other.relationships.add(rel);
//				} else {
//					person.ons.put(other, Timer.currentCalendar);
//					other.ons.put(person, Timer.currentCalendar);
//					if (rand.nextDouble() < 0.01)
//						Relater.scandal(person, other);
//				}
//			}
//		}
//	}

//	public static void affairAfterSex(Relationship relation) {
//		boolean noMarriage = !relation.first.isMarried() && !relation.second.isMarried();
//		if (noMarriage
//				&& ChronoUnit.DAYS.between(relation.startDate.toInstant(), Timer.currentCalendar.toInstant()) > 30) {
//			if (Marriager.canBeMarried(relation.first, relation.second)) {
//				Marriager.marriage(relation.first, relation.second);
//			}
//		} else {
//			sideAffairInsecurity(relation);
//		}
//	}
//
//	public static void affairNoSex(Relationship relation) {
//		sideAffairInsecurity(relation);
//		if (relation.isActive()) {
//			if (!Checker.attachmentCheck(relation.first) && !Checker.attachmentCheck(relation.first)
//					&& !Checker.attachmentCheck(relation.second) && !Checker.attachmentCheck(relation.second)) {
//				relation.end((Calendar) Timer.currentCalendar.clone());
//			}
//		}
//	}
//
//	public static void sideAffairInsecurity(Relationship relation) {
//		boolean firstWannaBreak = relation.first.isMarried()
//				&& (Checker.loyalCheck(relation.first) && Checker.loyalCheck(relation.first)
//						&& Checker.loyalCheck(relation.first))
//				&& (Checker.loyalCheck(relation.first) || !Checker.poliamoricCheck(relation.first));
//		boolean secondWannaBreak = relation.second.isMarried()
//				&& (Checker.loyalCheck(relation.second) && Checker.loyalCheck(relation.second)
//						&& Checker.loyalCheck(relation.second))
//				&& (Checker.loyalCheck(relation.second) || !Checker.poliamoricCheck(relation.second));
//		if (firstWannaBreak || secondWannaBreak) {
//			relation.end((Calendar) Timer.currentCalendar.clone());
//			if (firstWannaBreak) {
//				brokenHeart(relation, relation.second);
//			}
//			if (secondWannaBreak) {
//				brokenHeart(relation, relation.first);
//			}
//		}
//	}
//
//	public static void brokenHeart(Relationship relation, Person first) {
//		if (relation.second.isMarried()) {
//			if (Checker.jealousCheck(first) && Checker.jealousCheck(first) && Checker.impulsiveCheck(first)
//					&& Checker.impulsiveCheck(first) && Checker.proudCheck(first) && Checker.proudCheck(first))
//				Relater.scandal(relation.first, relation.second);
//		} else {
//			if (Checker.jealousCheck(first) && Checker.impulsiveCheck(first) && Checker.proudCheck(first))
//				Relater.scandal(relation.first, relation.second);
//		}
//	}
//
//	public static void fwbAfterSex(Relationship relation) {
//		boolean basicChecks = Checker.attachmentCheck(relation.first) && Checker.attachmentCheck(relation.second)
//				&& Checker.amorousCheck(relation.first) && Checker.amorousCheck(relation.second);
//		boolean firstWantMore = (!relation.first.hasAffairs() && !relation.first.isMarried())
//				|| ((relation.first.hasAffairs() || relation.first.isMarried())
//						&& Checker.poliamoricCheck(relation.first));
//		boolean secondWantMore = (!relation.second.hasAffairs() && !relation.second.isMarried())
//				|| ((relation.second.hasAffairs() || relation.second.isMarried())
//						&& Checker.poliamoricCheck(relation.second));
//		if (basicChecks && firstWantMore && secondWantMore) {
//			relation.end((Calendar) Timer.currentCalendar.clone());
//			Relationship newRelation = new Relationship(RelationType.AFFAIR, relation.first, relation.second,
//					(Calendar) Timer.currentCalendar.clone());
//			Relater.relationships.add(newRelation);
//			relation.first.relationships.add(newRelation);
//			relation.second.relationships.add(newRelation);
//		} else {
//			fwbInsecurity(relation);
//		}
//	}
//
//	public static void fwbNoSex(Relationship relation) {
//		fwbInsecurity(relation);
//	}
//
//	public static void fwbInsecurity(Relationship relation) {
//		boolean firstWannaBreak = checkBreakFwb(relation.first);
//		boolean secondWannaBreak = checkBreakFwb(relation.second);
//		if (firstWannaBreak && secondWannaBreak) {
//			relation.end((Calendar) Timer.currentCalendar.clone());
//		} else if (firstWannaBreak) {
//			relation.end((Calendar) Timer.currentCalendar.clone());
//			brokenHeart(relation, relation.second);
//		} else if (secondWannaBreak) {
//			relation.end((Calendar) Timer.currentCalendar.clone());
//			brokenHeart(relation, relation.first);
//		}
//	}
//
//	public static boolean checkBreakFwb(Person first) {
//		boolean wannaBreak = false;
//		for (Relationship rel : first.getAffairs()) {
//			if (!wannaBreak)
//				wannaBreak = Checker.attachmentCheck(first) && !Checker.poliamoricCheck(first);
//			if (wannaBreak)
//				break;
//		}
//		boolean marriageWannaBreak = first.isMarried() && Checker.loyalCheck(first) && Checker.attachmentCheck(first);
//		return wannaBreak || marriageWannaBreak;
//	}
//
//	public static boolean noMarriageOrNotLoyalOrPoliamoricImpulsive(Person first, Person second) {
//		boolean firstMarriage = !first.isMarried() || !Checker.loyalCheck(first)
//				|| (Checker.poliamoricCheck(first) && Checker.impulsiveCheck(first));
//		boolean secondMarriage = !second.isMarried() || !Checker.loyalCheck(second)
//				|| (Checker.poliamoricCheck(second) && Checker.impulsiveCheck(second));
//		return firstMarriage && secondMarriage;
//	}
//
//	public static void checkPregnancy(Person person, Person partner) {
//		if (person.sex != partner.sex && person.raceObj == partner.raceObj) {
//			if (person.age < person.raceObj.childTo && person.pregnantCounter == 0) {
//				if (pregnancyNeeded(person, partner)) {
//					makeChild(person, partner);
//				}
//			}
//		}
//	}
//
//	public static boolean pregnancyNeeded(Person person, Person partner) {
//		if (person.family.trueNumber() > 70)
//			return false;
//		if (person.isMarried() && person.getMarriage().getPartner(person) == partner) {
//			int availableKids = person.kidsInFamily();
//			double familySafety = person.family.safety();
//			if (availableKids > 5)
//				return false;
//			if (availableKids == 0 && rand.nextBoolean())
//				return true;
//			if (availableKids < 3 && rand.nextDouble() < moreChildrenChance)
//				return true;
//			if (familySafety < 1 && rand.nextDouble() < moreRelativesChance)
//				return true;
////			Person inRace = person.family == person.parentsFamily ? person : partner;
////			if (inRace.plannedKids > 0) {
////				inRace.plannedKids--;
////				return true;
////			}
//		}
//		if (rand.nextDouble() < person.raceObj.fertileTime) {
//			return true;
//		}
//		return false;
//	}

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
//			boolean pregnantOrChildren = true;
//			for (Person p : family.aliveMembers) {
//				if (p.age > p.stableAge && p.pregnantCounter == 0 && partnerNoPregnant(p)) {
//					pregnantOrChildren = false;
//				}
////				System.out.println(p.getFullName() + "\t" + p.age + "\t" + p.pregnantCounter + "\t" + partnerNoPregnant(p) + "\t" + noPartner(p));
//			}
//			if (pregnantOrChildren) {
//				System.out.println("Kid or just pregnant");
//			} else {
//				System.out.println(
//						"############ FUCK! NOONE CAN INTO CHILDREN IN FAMILY " + family.surname + " ############");
//			}
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
