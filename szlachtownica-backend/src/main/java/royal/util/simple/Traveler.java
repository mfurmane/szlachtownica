package royal.util.simple;

import royal.model.*;
import royal.util.Familiar;

import java.util.List;
import java.util.Random;

public class Traveler {
	public static Random rand = new Random();

	public static void handleTravel(Person person) {
		notRelationshipTravel(person, Familiar.familiesList);
	}

	private static void notRelationshipTravel(Person person, List<Family> families) {

	}

}
