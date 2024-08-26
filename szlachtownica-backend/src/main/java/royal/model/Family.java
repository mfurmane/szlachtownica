package royal.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import royal.util.simple.Timer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@JsonRootName(value = "Family", namespace = "families")
public class Family {

	public String surname;
	public String race;
	@JsonSerialize // BackReference
	public List<Person> aliveMembers = new ArrayList<>();
	@JsonSerialize
	public List<Person> deadMembers = new ArrayList<>();
	private List<Person> membersToRemove = new ArrayList<>();

	@JsonGetter("nestor")
	public long getNestor() {
		return nestor != null ? nestor.id : -1;
//		return nestor.id;
	}

	@JsonIgnore
	public Person nestor;
	@JsonIgnore
	public int strenght;
	@JsonIgnore
	public boolean plebs = false;
	@JsonIgnore
	public boolean needChildren = true;
	@JsonIgnore
	public int creationDate;
	@JsonIgnore
	public int plebsMarriageYearsCounter = 100;
	@JsonIgnore
	public Race raceObj;
	@JsonIgnore
	public Family senior;
	@JsonIgnore
	public List<Family> vassals = new ArrayList<>();
	@JsonIgnore
	public List<Family> neighbours = new ArrayList<>();
	@JsonIgnore
	public List<Person> knownMembers = new ArrayList<>();

	public Family(String surname, int strenght, Race race, int creationDate) {
		super();
		this.surname = surname;
		this.strenght = strenght;
		this.raceObj = race;
		this.race = raceObj.name;
		this.creationDate = creationDate;
	}

	public Family(String surname, int strenght, Race race, int creationDate, boolean plebs) {
		super();
		this.surname = surname;
		this.strenght = strenght;
		this.raceObj = race;
		this.race = raceObj.name;
		this.creationDate = creationDate;
		this.plebs = plebs;
		this.needChildren = !plebs;
	}

	@JsonIgnore
	public Person getHead() {
		Person head = null;
		for (Person person : aliveMembers) {
			if (head == null)
				head = person;
			if (person.importance > head.importance)
				head = person;
		}
		return head;
	}

	@JsonIgnore
	public double safety() {
		if (plebs)
			return 1000000000;
		int i = 0;
		for (Person member : aliveMembers) {
			Relationship marriage = member.getMarriage();
			Person partner = marriage != null ? marriage.getPartner(member) : null;
			int childTo = member.raceObj.childTo;
			if (partner != null && partner.sex != member.sex && member.raceObj == partner.raceObj
					&& (((childTo - member.age) > (childTo - member.raceObj.wantChildFrom) * 0.4
							&& (childTo - partner.age) > (childTo - member.raceObj.wantChildFrom) * 0.4)
							&& member.age < 200)) {
				i++;
			}
		}
		return i * 0.25;
	}

	@JsonIgnore
	public void setChildrenNeedance(boolean needChildren) {
		this.needChildren = needChildren;
	}

	@JsonIgnore
	public int childrenCount() {
		int i = 0;
		for (Person person : aliveMembers) {
			if (person.age < person.stableAge || person.pregnantCounter > 0) {
				i++;
			}
		}
		return i;
	}

	@JsonIgnore
	public boolean canIntoPregnant() {
		for (Person person : aliveMembers) {
			if (person.sex && person.pregnantCounter == 0 && person.age > person.stableAge) {
				return true;
			}
		}
		return false;
	}
	
	@JsonIgnore
	public double averageAgePerLifespan() {
		double result = 0;
		int count = 0;
		for (Person person : aliveMembers) {
			if (person.raceObj == raceObj) {
				result += ((double)person.age / (double)raceObj.lifespan);
				count++;
			}
		}
		return result / (double)count;
	}

	@JsonIgnore
	public int trueMembers() {
		int i = 0;
		for (Person person : aliveMembers) {
			if (person.pregnantCounter > 0) {
				i++;
			}
			if (person.parentsFamily == this) {
				i++;
			}
		}
		return i;
	}

	@JsonIgnore
	public int allMembers() {
		int i = 0;
		for (Person person : aliveMembers) {
			i++;
			if (person.pregnantCounter > 0) {
				i++;
			}
		}
		return i;
	}

	@JsonIgnore
	public void addMember(Person person) {
		if (!aliveMembers.contains(person)) {
			aliveMembers.add(person);
		}
	}

	@JsonIgnore
	public int fertileBranches() {
		int branches = 0;
		for (Person p : aliveMembers) {
			if (!p.isMarried() && p.age > 14 && p.age < raceObj.childTo && this == p.parentsFamily) {
				;
			} else if (p.isMarried()) {
				Person partner = p.getMarriage().getPartner(p);
				if (partner != null && partner.sex != p.sex && partner.raceObj == p.raceObj
						&& p.age < raceObj.childTo) {
					//if (p.plannedKids > 0 || p.kidsInFamily() > 0) {
						branches++;
					//}
				}
			}
			/*
			if (p.isMarried() && this == p.parentsFamily) {
				Person partner = p.getMarriage().getPartner(p);
				if (partner != null && partner.sex != p.sex && partner.raceObj == p.raceObj
						&& p.age < raceObj.lifespan / 2) {
					if (p.plannedKids > 0 || p.kidsInFamily() > 0) {
						branches++;
					}
				}
			}
			*/
		}
		return branches;
	}

	public void markMemberForRemoval(Person member) {
		membersToRemove.add(member);
	}

	public void removeMarkedMembers() {
		membersToRemove.forEach(member -> {
			aliveMembers.remove(member);
		});
	}

    public boolean isCreated() {
		return plebs || Timer.currentCalendar.get(Calendar.YEAR) >= creationDate;
    }

public boolean isCoherent() {
    for (Person p : aliveMembers) {
	    if (p.family != this) {
		    return false;
	    }
    }
	return true;
}

	public boolean shouldMarryPlebs() {
		return isCreated() && plebsMarriageYearsCounter > 0;
	}
	
}
