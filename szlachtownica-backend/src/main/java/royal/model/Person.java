package royal.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import royal.util.simple.Debug;
import royal.util.simple.Namer;
import royal.util.simple.Timer;

import java.util.*;

@JsonRootName(value = "Person", namespace = "people")
public class Person {

	private final Random rand = new Random();
	public static long idCounter = 0;

	public enum Status {
		LEGAL_CHILD, POSSIBLE_BASTARD, UNKNOWN_FATHER, ACCEPTED_BASTARD, OUT_MARRIAGE
	}

	public long id;
	public String race;
	public Calendar born;
	public Calendar died;
	public int age;
	public Status status;
	public boolean sex; // true female
	public double lineImportance; // scandalFactor

	@JsonGetter("kids")
	public List<Long> getKids() {
		List<Long> ki = new ArrayList<>();
		for (Person p : kids) {
			ki.add(p.id);
		}
		return ki;
	}

	@JsonGetter("father")
	public Long getFather() {
		return father != null ? father.id : -1;
	}

	@JsonGetter("mother")
	public Long getMother() {
		return mother != null ? mother.id : -1;
	}

	@JsonGetter("fullName")
	public String getName() {
		if (!plebs) {
			if (parentsFamily.raceObj.surname) {
				return name + " " + (parentsFamily != family ? "(" + parentsFamily.surname + ") " : "")
						+ family.surname;
			}
			return name
					+ ((father != null || mother != null)
							? (" " + (father != null ? father.name : mother.name) + (sex ? "detter" : "son"))
							: "")
					+ " " + family.surname + (parentsFamily != family ? " (" + parentsFamily.surname + ")" : "");
		}
		if (parentsFamily.raceObj.surname) {
			return name + " " + (parentsFamily != family ? "(" + plebsSurname + ") " : "")
					+ (!family.plebs ? family.surname : plebsSurname);
		}
		return name
				+ ((father != null || mother != null) ? (" " + (Namer.dwarfFatherName()) + (sex ? "detter" : "son"))
						: "")
				+ " " + (!family.plebs ? family.surname : plebsSurname)
				+ (parentsFamily != family ? " (" + plebsSurname + ")" : "");
	}

	@JsonIgnore
	public String plebsSurname;
	@JsonIgnore
	public String name;
	@JsonIgnore
	public Family family;
	@JsonIgnore
	public boolean plebs = false;
	@JsonIgnore
	public boolean singleChecked = false;
	@JsonIgnore
	public boolean waitsAsSingle = false;
	@JsonIgnore
	public Race raceObj;
	@JsonIgnore
	public Family parentsFamily;
	@JsonIgnore
	public Person father;
	@JsonIgnore
	public Person officialFather;
	@JsonIgnore
	public Person mother;
	@JsonIgnore
	public List<Relationship> relationships = new ArrayList<>();
	@JsonIgnore
	public Map<Person, Calendar> ons = new HashMap<>();
	@JsonIgnore
	public Family onTravelAt;
	@JsonIgnore
	public double travelFactor;
	@JsonIgnore
	public double hornyFactor;
	@JsonIgnore
	public double loyalFactor;
	@JsonIgnore
	public double homoFactor;
	@JsonIgnore
	public double interracialFactor;
	@JsonIgnore
	public double attachmentFactor;
	@JsonIgnore
	public double poliamoricFactor;
	@JsonIgnore
	public double jealousFactor;
	@JsonIgnore
	public double impulsiveFactor;
	@JsonIgnore
	public double proudFactor;
	@JsonIgnore
	public double amorousFactor;
	@JsonIgnore
	public double divorcableFactor; // pochodne
	@JsonIgnore
	public double revengeFactor; // pochodne
	@JsonIgnore
	public double inbreeding; // stopień chowu wsobnego
	@JsonIgnore
	public double importance;
	@JsonIgnore
	public double membershipStrenght;
	@JsonIgnore
	public int hornyAge;
	@JsonIgnore
	public int stableAge;
	@JsonIgnore
	public int marriageAge;
	@JsonIgnore
	public Calendar mourningEnd;
	@JsonIgnore
	public int mourningTime; // search new partner after this
	@JsonIgnore
	public List<Person> knownKids = new ArrayList<>();
	@JsonIgnore
	public boolean lastMember = false;
	@JsonIgnore
	public int pregnantCounter = 0;
	@JsonIgnore
	public Person childFather;
	@JsonIgnore
	public int birthAt = 0;
	@JsonIgnore
	public int weakBloodKid = 0;
	@JsonIgnore
	public int legalKid = 0;
	@JsonIgnore
	public int plannedKids = 0;
	@JsonIgnore
	public Person planedMarriage;
	@JsonIgnore
	public List<Person> kids = new ArrayList<>();

	public boolean isAdult() {
		return getAge() >= stableAge;
	}

	private int getAge() {
		int years = Timer.currentCalendar.get(Calendar.YEAR) - born.get(Calendar.YEAR);
		long days = Timer.currentCalendar.get(Calendar.DAY_OF_YEAR) - born.get(Calendar.DAY_OF_YEAR);
		return days >= 0 ? years : years - 1;
	}

	public Person(String name, Family family, Person father, Person mother, Calendar born, Status status, boolean sex,
			double travelFactor, double hornyFactor, double loyalFactor, double homoFactor, double interracialFactor,
			double lineImportance, double membershipStrenght, int hornyAge, int stableAge, int mourningTime,
			double attachmentFactor, double poliamoricFactor, double jealousFactor, double impulsiveFactor,
			double proudFactor, double amorousFactor) {
		super();
		this.id = idCounter;
		idCounter++;
		this.name = name;
		this.family = family;
		this.parentsFamily = family;
		this.father = father;
		this.officialFather = father;
		this.mother = mother;
		if (father != null) {
			raceObj = father.raceObj;
		} else if (mother != null) {
			raceObj = mother.raceObj;
		} else {
			raceObj = family.raceObj;
		}
		this.race = raceObj.name;
		this.born = born;
		this.status = status;
		this.sex = sex;
		this.travelFactor = travelFactor;
		this.hornyFactor = hornyFactor;
		this.loyalFactor = loyalFactor;
		this.homoFactor = homoFactor;
		this.interracialFactor = interracialFactor;
		this.attachmentFactor = attachmentFactor;
		this.poliamoricFactor = poliamoricFactor;
		this.jealousFactor = jealousFactor;
		this.impulsiveFactor = impulsiveFactor;
		this.proudFactor = proudFactor;
		this.amorousFactor = amorousFactor;
		this.revengeFactor = calculateRevenge();
		this.divorcableFactor = calculateDivorcable();
		this.lineImportance = lineImportance;
		this.membershipStrenght = membershipStrenght;
		this.hornyAge = hornyAge;
		this.stableAge = stableAge;
		this.marriageAge = stableAge + 1 / 20 * raceObj.lifespan;
		this.mourningTime = (mourningTime / 80) * raceObj.lifespan;
		this.inbreeding = calculateInbreeding();
		this.plebsSurname = Namer.surnameFor(raceObj);
		this.plannedKids = 1 + rand.nextInt(3);
	}

	private double calculateInbreeding() {
		if (father != null && mother != null) {
			Set<Person> parents = new HashSet<>();
			Set<Person> ancestors = new HashSet<>();
			addParentsTo(father, parents);
			addParentsTo(mother, parents);
			ancestors.addAll(parents);
			Set<Person> gparents = new HashSet<>();
			for (Person p : parents) {
				addParentsTo(p, gparents);
			}
			ancestors.addAll(gparents);
			double first = 1 - (double) ancestors.size() / 6.0;
			Set<Person> ggparents = new HashSet<>();
			for (Person p : gparents) {
				addParentsTo(p, ggparents);
			}
			ancestors.addAll(ggparents);
			double second = 1 - (double) ancestors.size() / 14.0;
			Set<Person> gggparents = new HashSet<>();
			for (Person p : ggparents) {
				addParentsTo(p, gggparents);
			}
			ancestors.addAll(gggparents);
			double third = 1 - (double) ancestors.size() / 30.0;
			Set<Person> ggggparents = new HashSet<>();
			for (Person p : gggparents) {
				addParentsTo(p, ggggparents);
			}
			ancestors.addAll(ggggparents);
			double fourth = 1 - (double) ancestors.size() / 62.0;
			double inheritedFactor = (0.2 + Math.sqrt(father.inbreeding) + Math.sqrt(mother.inbreeding));
			return inheritedFactor * (first / 1.5 + second / 2.5 + third / 4.5 + fourth / 8.5);
		}
		// TODO Auto-generated method stub
		return father != null ? father.inbreeding * 0.1 : mother != null ? mother.inbreeding * 0.1 : 0;
	}

	private void addParentsTo(Person father, Set<Person> parents) {
		if (father.father != null)
			parents.add(father.father);
		if (father.mother != null)
			parents.add(father.mother);
	}

	public double calculateDivorcable() {
		double tmpImportance = (family.strenght * lineImportance / 10);
		double divor = (0.02 * (1.0 - travelFactor) + 0.1 * (1.0 - poliamoricFactor) + 0.38 * jealousFactor
				+ 0.15 * impulsiveFactor + 0.35 * proudFactor)
				/ (0.1 * loyalFactor + 0.55 * attachmentFactor + 0.3 * tmpImportance);
		divorcableFactor = divor;
		return divor;
	}

	public double calculateRevenge() {
		// up - jealousFactor, impulsiveFactor, proudFactor
		// down - attachmentFactor, importance
		double tmpImportance = (family.strenght * lineImportance / 10);
		double reven = (0.33 * jealousFactor + 0.34 * impulsiveFactor + 0.33 * proudFactor)
				/ (0.6 * attachmentFactor + 0.4 * tmpImportance);
		revengeFactor = reven;
		return reven;
	}

	public String alive() {
		return died != null ? "+" : "";
	}

	@JsonIgnore
	public int getLegalKidsCount() {
		int i = 0;
		for (Person kid : kids) {
			if (kid.status == Status.LEGAL_CHILD)
				i++;
		}
		return i;
	}

	@JsonIgnore
	public boolean isMarried() {
		for (Relationship rel : relationships) {
			if (rel.type == RelationType.LEGAL && rel.isActive())
				return true;
		}
		return false;
	}

	@JsonIgnore
	public Relationship getMarriage() {
		for (Relationship rel : relationships) {
			if (rel.type == RelationType.LEGAL && rel.isActive())
				return rel;
		}
		return null;
	}

	@JsonIgnore
	public boolean isMourning(Calendar currentTime) {
		if (mourningEnd == null)
			return false;
		return mourningEnd.after(currentTime);
	}

	@JsonIgnore
	public boolean hasAffairs() {
		for (Relationship rel : relationships) {
			if (rel.type == RelationType.AFFAIR && rel.isActive())
				return true;
		}
		return false;
	}

	@JsonIgnore
	public boolean hasFwbs() {
		for (Relationship rel : relationships) {
			if (rel.type == RelationType.FWB && rel.isActive())
				return true;
		}
		return false;
	}

	@JsonIgnore
	public List<Relationship> getAffairs() {
		List<Relationship> toReturn = new ArrayList<>();
		for (Relationship rel : relationships) {
			if (rel.type == RelationType.AFFAIR && rel.isActive())
				toReturn.add(rel);
		}
		return toReturn;
	}

	@JsonIgnore
	public List<Relationship> getFwbs() {
		List<Relationship> toReturn = new ArrayList<>();
		for (Relationship rel : relationships) {
			if (rel.type == RelationType.FWB && rel.isActive())
				toReturn.add(rel);
		}
		return toReturn;
	}

	@JsonIgnore
	public List<Relationship> getPreviousMarriages() {
		List<Relationship> rels = new ArrayList<>();
		for (Relationship rel : relationships) {
			if (rel.type == RelationType.LEGAL && !rel.isActive()) {
				rels.add(rel);
			}
		}
		return rels;
	}

	@JsonIgnore
	public Set<Person> getKidsObj() {
		Set<Person> kidos = new HashSet<>();
		kidos.addAll(kids);
		if (isMarried())
			kidos.addAll(getMarriage().getPartner(this).kids);
		return kidos;
	}

	@JsonIgnore
	public int kidsInFamily() {
		int kido = 0;
		for (Person kid : getKidsObj()) {
			if (kid.family == family)
				kido++;
		}
		return kido;
	}

	@JsonIgnore
	public void printWithChildren(String prefix, Set<Person> added) {
		Debug.log(
				prefix + getName() + "\t" + aliveTime() + "\t[" + (father != null ? father.getName() : "") + ","
						+ (mother != null ? mother.getName() : "") + "]\t"
						+ (isMarried()
								? getMarriage().getPartner(this).getName() + "\t"
										+ getMarriage().getPartner(this).aliveTime()
								: ""));
		for (Person child : kids) {
			if (child.family == family) {
				if (added.contains(child)) {
					Debug.log(prefix + "  ADDED BEFORE " + child.getName());
				} else {
					added.add(child);
					child.printWithChildren(prefix + "  ", added);
				}
			}
		}
	}

	@JsonIgnore
	public String aliveTime() {
		return "(" + (born != null ? born.get(Calendar.YEAR) : "") + "-" + (died != null ? died.get(Calendar.YEAR) : "")
				+ ")";
	}
}
