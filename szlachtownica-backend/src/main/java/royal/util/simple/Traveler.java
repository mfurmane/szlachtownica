package royal.util.simple;

import royal.model.*;
import royal.util.Familiar;

import java.util.List;
import java.util.Random;

public class Traveler {
	public static Random rand = new Random();

	public static void handleTravel(Person person) {
		for (Relationship rel : person.relationships) {
			Person partner = rel.getPartner(person);
			if (rel.type != RelationType.LEGAL && rel.isActive()) {
				if (partner.onTravelAt == null) {
					if (rel.type == RelationType.AFFAIR && partner.onTravelAt == null && rand.nextDouble() < 0.4) {
						person.onTravelAt = partner.family;
					} else if (rel.type == RelationType.FWB && partner.onTravelAt == null && rand.nextDouble() < 0.1) {
						person.onTravelAt = partner.family;
					}
				} else if (partner.onTravelAt == person.family) {
					person.onTravelAt = null;
					return;
				} else if (rand.nextDouble() < 0.4) {
					person.onTravelAt = partner.onTravelAt;
					return;
				}
			}
		}
		notRelationshipTravel(person, Familiar.familiesList);
	}

	private static void notRelationshipTravel(Person person, List<Family> families) {
		Race race;
		if (rand.nextDouble() < person.travelFactor || (!person.isMarried() // wolny
				&& person.age > person.stableAge // szuka małżeństwa
				&& ((double) person.age / (double) person.raceObj.lifespan) < 0.8)) { // dość daleko mu jeszcze do
																						// grobu?
			int r = rand.nextInt(12);
			switch (r) {
			case 0:
				race = Racist.human;
				break;
			case 1:
				race = Racist.elf;
				break;
			case 2:
				race = Racist.dwarf;
				break;
			case 3:
				race = Racist.half;
				break;
			case 4:
				race = Racist.gnome;
				break;
			case 5:
				race = Racist.peura;
				break;
			default:
				race = person.raceObj;
			}
			person.onTravelAt = families.get(rand.nextInt(families.size()));
			while (person.onTravelAt.raceObj != race || person.onTravelAt == person.family) {
				person.onTravelAt = families.get(rand.nextInt(families.size()));
			}
		} else {
			person.onTravelAt = null;
		}
	}

}
