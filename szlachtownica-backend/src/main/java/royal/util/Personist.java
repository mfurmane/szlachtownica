package royal.util;

import royal.model.Family;
import royal.model.Person;
import royal.model.Race;
import royal.util.simple.*;

import java.util.Calendar;
import java.util.Random;

import static royal.util.Birther.getFather;
import static royal.util.Birther.getMother;

public class Personist {

    private static final Random rand = new Random();

    public static void handleKnownMembers(Family family) {

    }

    private static Person findMother(Person mother, Person father) {
        return null;
    }

    public static Person getPerson(boolean sex, Person person, Person partner, double lineImportance, double membershipStrenght, Person.Status status) {
        return new Person(Namer.getName(sex, person.raceObj), person.family, getFather(person, partner),
                getMother(person, partner), Timer.currentCalendar, status, sex, rand.nextDouble(),
                StateCreator.horny(person.raceObj), StateCreator.loyal(person.raceObj), StateCreator.homo(person.raceObj), StateCreator.interracial(person.raceObj), lineImportance,
                membershipStrenght, StateCreator.childFrom(person.raceObj), StateCreator.wantChild(person.raceObj), EldrichOnes.mourningTime(),
                StateCreator.attachment(person.raceObj), StateCreator.poliamoric(person.raceObj), StateCreator.jealous(person.raceObj),
                StateCreator.impulsive(person.raceObj), StateCreator.proud(person.raceObj), StateCreator.amorous(person.raceObj));
    }

    public static Person getPerson(boolean sex, Race race, double lineImportance, double membershipStrenght, Person.Status status) {
        return new Person(Namer.getName(sex, race), plebsFamilyByRace(race), null,
                null, Timer.currentCalendar, status, sex, rand.nextDouble(),
                StateCreator.horny(race), StateCreator.loyal(race), StateCreator.homo(race), StateCreator.interracial(race), lineImportance,
                membershipStrenght, StateCreator.childFrom(race), StateCreator.wantChild(race), EldrichOnes.mourningTime(),
                StateCreator.attachment(race), StateCreator.poliamoric(race), StateCreator.jealous(race),
                StateCreator.impulsive(race), StateCreator.proud(race), StateCreator.amorous(race));
    }

    public static Person getPerson(boolean sex, Family family, int born, Person father) {
        return new Person(Namer.getName(sex, family.raceObj), family, father,
                null, Timer.getDate(born), Person.Status.LEGAL_CHILD, sex, rand.nextDouble(),
                StateCreator.horny(family.raceObj), StateCreator.loyal(family.raceObj), StateCreator.homo(family.raceObj), StateCreator.interracial(family.raceObj), 1,
                1, StateCreator.childFrom(family.raceObj), StateCreator.wantChild(family.raceObj), EldrichOnes.mourningTime(),
                StateCreator.attachment(family.raceObj), StateCreator.poliamoric(family.raceObj), StateCreator.jealous(family.raceObj),
                StateCreator.impulsive(family.raceObj), StateCreator.proud(family.raceObj), StateCreator.amorous(family.raceObj));
    }

    public static Person getPerson(boolean sex, Family family, int born) {
        return getPerson(sex, family, born, null);
    }

    private static Family plebsFamilyByRace(Race race) {
        for (Family family : Familiar.plebs) {
            if (family.raceObj == race) {
                return family;
            }
        }
        return null;
    }
}
