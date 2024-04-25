package royal.util;

import royal.model.Family;
import royal.model.Person;
import royal.util.simple.Timer;

import java.util.Calendar;

public class Personist {

	public static int handleKnownMembers(Family family, Calendar currentCalendar, int i) {
		Person member = family.knownMembers.get(i);
		if (member.born.before(currentCalendar)) {
			family.knownMembers.remove(member);
			family.addMember(member);
			i--;
			if (member == Familiar.mereinaI) {
//					System.out.println(Familiar.ranalII.mother + " - " + Familiar.ranalII.father);
//					System.out.println(Familiar.ranalII.mother.name + " - " + Familiar.ranalII.father.name);
				member.mother = findMother(Familiar.ranalII.mother, Familiar.ranalII.father);
				member.father = member.mother.getMarriage().getPartner(member.mother);
				member.mother.kids.add(member);
				member.father.kids.add(member);
			} else {
				if (member.father == null && member.mother == null) {
					for (int j = 0; j < family.aliveMembers.size(); j++) {
						Person mem = family.aliveMembers.get(j);
						if (mem.isMarried()) {
							Person memPartner = mem.getMarriage().getPartner(mem);
							if (memPartner.sex != mem.sex && mem.raceObj == memPartner.raceObj
									&& memPartner.raceObj == member.raceObj && mem.race.equals(mem.family.race)
									&& mem.family == member.family && mem.age < 150) { // chyba chodzi o Aelervesę
								member.mother = mem;
								member.father = memPartner;
								mem.kids.add(member);
								memPartner.kids.add(member);
								break;
							}
						}
					}
					if (member.mother == null) {
						for (int j = 0; j < family.aliveMembers.size(); j++) {
							Person mem = family.aliveMembers.get(j);
							if (mem.isMarried()) {
								Person memPartner = mem.getMarriage().getPartner(mem);
								if (memPartner.sex != mem.sex && mem.raceObj == memPartner.raceObj
										&& memPartner.raceObj == member.raceObj && mem.race.equals(mem.family.race)
										&& mem.family == member.family) {
									member.mother = mem;
									member.father = memPartner;
									mem.kids.add(member);
									memPartner.kids.add(member);
									break;
								}
							}
						}
					}
//					if (member.mother == null)
//						System.out.println("FUCK");
//					else {
//						System.out.println("##########" + member.mother.getFullName());
//						System.out.println("##########" + member.father.getFullName());
//					}
				} else {
					if (member.father == null) {
						member.father = member.mother.isMarried()
								? member.mother.getMarriage().getPartner(member.mother)
								: null;
						if (member.father != null) {
							member.father.kids.add(member);
						}
					}
					if (member.mother == null) {
						member.mother = member.father.isMarried()
								? member.father.getMarriage().getPartner(member.father)
								: null;
						if (member.mother != null) {
							member.mother.kids.add(member);
						}
					}
				}
				if (member == Familiar.ranalII) {

				}
			}
		}
		return i;
	}

	private static Person findMother(Person mother, Person father) {
		Person mom = null;
		if (mother != null) {
			for (Person kid : mother.kids) {
				if (kid != null && kid.sex && kid.isMarried()) {
					Person partner = kid.getMarriage().getPartner(kid);
					if (!partner.sex && kid.race.equals(partner.race) && kid.race.equals(kid.family.race)
							&& kid.family == Familiar.holzer) {
						if (kid.died == null || kid.died.after(Timer.currentCalendar)) {
							mom = kid;
						} else {
							mom = crawlChildren(kid);
						}
					}
				}
			}
		}
		if (mom == null && father != null) {
			for (Person kid : father.kids) {
				if (kid != null && kid.sex && kid.isMarried()) {
					Person partner = kid.getMarriage().getPartner(kid);
					if (!partner.sex && kid.race.equals(partner.race) && kid.race.equals(kid.family.race)
							&& kid.family == Familiar.holzer) {
						if (kid.died == null || kid.died.after(Timer.currentCalendar)) {
							mom = kid;
						} else {
							mom = crawlChildren(kid);
						}
					}
				}
			}
		}
		if (mom == null) {
			if (mother != null && father != null) {
				return findMother(mother.mother, father.father);
			} else if (mother != null && father == null) {
				return findMother(mother.mother, mother.father);
			} else if (mother == null && father != null) {
				return findMother(father.mother, father.father);
			}
		}
		return mom;
	}

	private static Person crawlChildren(Person heh) {
//		for (Person kid : heh.kids) {
//			if (kid != null && kid.sex && kid.currentLegalPartner != null && !kid.currentLegalPartner.sex
//					&& kid.race.equals(kid.currentLegalPartner.race) && kid.race.equals(kid.family.race)
//					&& kid.family == holzer) {
//				if ((kid.died == null || kid.died.after(currentCalendar)) && kid.born.be) {
//					mom = kid;
//				} else {
//					mom = crawlChildren(kid);
//				}
//			}
		return null;
	}
}
