package royal;

import royal.model.Family;
import royal.model.Person;
import royal.util.*;
import royal.util.simple.Timer;
import royal.util.simple.*;

import java.io.IOException;
import java.util.*;

public class Main {

    static Random rand = new Random();

    public static void main(String[] args) throws IOException {
        Familiar.fillFamilies();
        runTime(Timer.prepareEndOfTime(), Timer.startYear());
        handleFinalData();
    }

    private static void runTime(Calendar endCalendar, int year) {
        while (Timer.currentCalendar.before(endCalendar)) {
            if (Timer.currentCalendar.get(Calendar.YEAR) != year) {
                year = Timer.updateYear();
            }
            Familiar.families.forEach(family -> {
                if (!family.plebs && Timer.currentCalendar.get(Calendar.YEAR) >= family.creationDate) {
                    handleFamilyForDay(family);
                } else if (family.plebs) {
                    handlePlebs(family);
                }
            });
            Timer.currentCalendar.add(Calendar.WEEK_OF_YEAR, 1); // może tydzień
        }
    }

    public static void handleFamilyForDay(Family family) {
        Ares.handlePrinceChange(family);
        if (Familiar.familyNeedChildren(family)) {
            Fucker.makeMeSomeBabies(family);
        }
        family.aliveMembers.forEach(member -> {
            handlePersonForDay(member, Familiar.familyAllowsHomo(family));
        });
        Personist.handleKnownMembers(family);
        Familiar.repairBloodlines(family);
    }

    public static void handlePersonForDay(Person person, boolean familyAllowsHomo) {
        if (person.birthAt > 0)
            Birther.handleBirth(person);
        else {
            handleDeathPossibilities(person);
            Birther.handlePregnancy(person);
            if (!Familiar.plebs.contains(person.parentsFamily)) {
                Traveler.handleTravel(person);
                Marriager.handleMissalliance(person);
                Marriager.handleMarriage(person, familyAllowsHomo);
                Birther.handleKnownKids(person);
            }
        }
    }

    private static void handlePlebs(Family family) {
        for (Person aliveMember : family.aliveMembers) {
            handleDeathPossibilities(aliveMember);
        }
    }

    private static void handleDeathPossibilities(Person person) {
        Ager.handleAge(person);
        Ares.handleWar(person);
        Killer.handleDeath(person);
    }

    private static void handleFinalData() throws IOException {
        Date startTime = new Date();
        Printer.printToFiles(startTime);
        Printer.printRelations(startTime);
    }
}
