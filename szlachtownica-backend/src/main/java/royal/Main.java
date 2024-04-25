package royal;

import com.fasterxml.jackson.databind.ObjectMapper;
import royal.model.Family;
import royal.model.Person;
import royal.model.Relationship;
import royal.util.*;
import royal.util.simple.Timer;
import royal.util.simple.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        Familiar.fillFamilies();
        Calendar endCalendar = prepareEndOfTime();
        Date startTime = new Date();
        int year = Timer.currentCalendar.get(Calendar.YEAR);
        //TODO what is it for?
        Set<Family> usedFamilies = new HashSet<>();
        //TODO end
        runTime(endCalendar, year, usedFamilies);
        timeAfterTime(startTime);
        printRelations(startTime);
        System.out.println(startTime.toString().replace(" CEST 2022", "").replace("Fri Sep ", "") + " - "
                + new Date().toString().replace(" CEST 2022", "").replace("Fri Sep ", ""));
    }

    private static void runTime(Calendar endCalendar, int year, Set<Family> usedFamilies) {
        while (Timer.currentCalendar.before(endCalendar)) {
            if (Timer.currentCalendar.get(Calendar.YEAR) != year) {
                year = doSomethingWithYear();
            }
            for (Family family : Familiar.families) {
                if (!family.plebs && Timer.currentCalendar.get(Calendar.YEAR) >= family.creationDate) {
                    usedFamilies.add(family);
                    handleFamilyForDay(family, Timer.currentCalendar);
                } else if (family.plebs) {
                    handlePlebs(family);
                }
            }
            Timer.currentCalendar.add(Calendar.WEEK_OF_YEAR, 1); // może tydzień
        }
    }

    private static void handlePlebs(Family family) {
        for (int i = 0; i < family.aliveMembers.size(); i++) {
            Person person = family.aliveMembers.get(i);
            Ager.handleAge(person);
            Ares.handleWar(person);
            Killer.handleDeath(person);
        }
    }

    private static int doSomethingWithYear() {
        int year;
        year = Timer.currentCalendar.get(Calendar.YEAR);
        System.out.println("\n" + year);
        for (Family family : Familiar.families) {
            if (!family.plebs && Timer.currentCalendar.get(Calendar.YEAR) >= family.creationDate
                    && family.plebsMarriageYearsCounter > 0) {
                family.plebsMarriageYearsCounter--;
            }
            if (!family.plebs && Timer.currentCalendar.get(Calendar.YEAR) >= family.creationDate) {
                int fullFamily = family.allMembers();
                int trueMembers = family.trueMembers();
                System.out.println(family.childrenCount() + "\t" + trueMembers + "\t" + fullFamily + "\t"
                        + String.format("%,.2f", ((double) trueMembers / (double) fullFamily)) + "\t"
                        + family.surname);
                if (family.allMembers() >= 100) {
                    for (int i = 0; i < family.aliveMembers.size(); i++) {
                        Person member = family.aliveMembers.get(i);
                        System.out.println(member.age + "\t" + member.family.surname + "\t"
                                + member.parentsFamily.surname + "\t" + member.getName() + "\t"
                                + (member.isMarried() ? member.getMarriage().getPartner(member).getName() : "")
                                + "\t\t\t" + (member.mother != null ? member.mother.getName() : ""));
                    }
                    System.out.println("");
                }
            }
        }
        return year;
    }

    public static void handleFamilyForDay(Family family, Calendar currentCalendar) {
        Ares.handlePrinceChange(family, currentCalendar);
        boolean familyAllowsHomo = (family.trueMembers() > 35 && family.childrenCount() > 4)
                || family.trueMembers() > 50;
        boolean familyNeedChildren = family.trueMembers() < 50 && family.childrenCount() < 4;
        if ((family.childrenCount() < 2 || family.trueMembers() < 40) && family.canIntoPregnant()) {
//			System.out.println(family.childrenCount() + "\t" + family.trueMembers() + "\t" + family.allMembers() + "\t"
//					+ family.surname + "\tWILL FUCK");
            Fucker.makeMeSomeBabies(family);
        }

        for (int i = 0; i < family.aliveMembers.size(); i++) {
            Person member = family.aliveMembers.get(i);
            handleFuckup(member, family);
            handlePersonForDay(member, familyAllowsHomo, familyNeedChildren);
        }
        for (int i = 0; i < family.knownMembers.size(); i++) {
            i = Personist.handleKnownMembers(family, currentCalendar, i);
        }
        Familiar.repairBloodlines(family);
    }

    public static void handlePersonForDay(Person person, boolean familyAllowsHomo, boolean familyNeedChildren) {
        if (person.birthAt > 0)
            Birther.handleBirth(person);
        else {
            Ager.handleAge(person);
            Ares.handleWar(person);
            Killer.handleDeath(person);
            Birther.handlePregnancy(person);
            if (!Familiar.plebs.contains(person.parentsFamily)) {
                Traveler.handleTravel(person);
                Marriager.handleMissalliance(person);
                Marriager.handleMarriage(person, familyAllowsHomo);
//				Fucker.handleSex(person, familyNeedChildren);
                Birther.handleKnownKids(person);
            }
        }
    }

    private static Calendar prepareEndOfTime() {
        Timer.currentCalendar.set(Calendar.YEAR, historyStart);
        Timer.currentCalendar.set(Calendar.DAY_OF_YEAR, 1);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.YEAR, historyEnd);
        endCalendar.set(Calendar.DAY_OF_YEAR, 1);
        return endCalendar;
    }

    private static void handleFuckup(Person member, Family family) {
        if (member.family != family) {
//			System.out.println("SHIEEEEEET KURWA");
            family.aliveMembers.remove(member);
            if (!member.family.aliveMembers.contains(member)) {
                member.family.addMember(member);
            }
        }
    }

    private static final int historyStart = 687;// 1198;
    private static final int historyEnd = 1743;// 1743;
    static Random rand = new Random();

    private static void printRelations(Date startTime) {
        String firstDictionary = "G:/Szlachta/All/"
                + startTime.toString().replace(":", "_").replace(" CEST 2022", "").replace("Sun Sep 04", "") + "/";
        try {
            Files.createDirectories(Paths.get(firstDictionary));
            String fileName = firstDictionary + "relations.txt";
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            ObjectMapper mapper = new ObjectMapper();
            try {

                String json = mapper.writeValueAsString(Relater.relationships);
                printWriter.println(json);
            } catch (Exception e) {
                e.printStackTrace(printWriter);
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    private static String printAliveMember(Person person) {
        return "* " + person.getName() + " (" + person.age + ", " + Math.round(person.lineImportance * 100) + "%, "
                + Math.round(person.importance * 100) + "%) " + " - " + printParents(person) + printPartners(person);
    }

    private static String printPartners(Person person) {
        String value = "";
        if (person.isMarried()) {
            value += (", Partner: " + fullName(person.getMarriage().getPartner(person)));
        }
        List<Relationship> previousMarriages = person.getPreviousMarriages();
        if (previousMarriages.size() > 0) {
            value += ", Byli partnerzy: ";
            Iterator<Relationship> iterator = previousMarriages.iterator();
            value += fullName(iterator.next().getPartner(person));
            while (iterator.hasNext()) {
                value += (". " + fullName(iterator.next().getPartner(person)));
            }
        }
        return value;
    }

    private static String printParents(Person person) {
        return "Rodzice: "
                + (person.mother != null ? fullName(person.mother) + (person.father != null ? " i " : "") : "")
                + (person.father != null ? fullName(person.father) : "");
    }

    private static String fullName(Person person) {
        return person.getName() + " (" + person.age + ")";
    }

    private static String printPerson(Person parent, String prefix, Person kid, Family family) {
        return "";
    }

    static List<Person> printedM = new ArrayList<>();
    static List<Person> printed = new ArrayList<>();

    private static void printKids(Person parent, String prefix, PrintWriter printWriter, Family family, boolean check) {
        for (Person kid : parent.getKidsObj()) {
            handleKid(parent, prefix, printWriter, family, check, kid);
        }
    }

    private static void handleKid(Person parent, String prefix, PrintWriter printWriter, Family family, boolean check,
                                  Person kid) {
        if ((check && kid.family == family) || !check) {
            printWriter.println(printPerson(parent, prefix, kid, family));
            printKids(kid, prefix + "  ", printWriter, family, check);
        }
    }

    private static String printDate(Calendar date) {
        return "" + date.get(Calendar.DAY_OF_MONTH) + "." + date.get(Calendar.MONTH) + "." + date.get(Calendar.YEAR);
    }

    int falseX = 0;
    int trueX = 0;

    int same = 0;
    int notSame = 0;

    private void checkVassals(Person person) {
        if (rand.nextDouble() < 0.02) {
            for (Family vassal : person.family.vassals) {
                for (Person other : vassal.aliveMembers) {
                    if (rand.nextDouble() < 0.3) {
                        checkPerson(person, other);
                    }
                }
            }
            if (rand.nextDouble() < 0.001)
                Fucker.sex(person, null);
        }
    }

    private void checkPossibilities(Person person) {
        if (rand.nextDouble() < 0.02) {
            for (Person other : person.onTravelAt.aliveMembers) {
                checkPerson(person, other);
            }
            for (Family vassal : person.onTravelAt.vassals) {
                for (Person other : vassal.aliveMembers) {
                    if (rand.nextDouble() < 0.3) {
                        checkPerson(person, other);
                    }
                }
            }
            if (rand.nextDouble() < 0.001)
                Fucker.sex(person, null);
        }
    }

    private void checkPerson(Person person, Person other) {
        if (other.age >= other.hornyAge && other.onTravelAt != null) {
            if (other.isMarried()) {
                if (other.getMarriage().getPartner(other).onTravelAt != null) {
                    if (rand.nextDouble() > other.loyalFactor) {
                        checkCompatibility(person, other);
                    }
                } else {
                    if (rand.nextDouble() > other.loyalFactor * 1.3) {
                        checkCompatibility(person, other);
                    }
                }
            } else {
                checkCompatibility(person, other);
            }
        }
    }

    private void checkCompatibility(Person person, Person other) {
        if (person.raceObj == other.raceObj) {
            if (Math.abs(other.age - person.age) <= person.raceObj.lifespan * 0.2) {
                if (other.sex == person.sex) {
                    double homo = rand.nextDouble();
                    if (homo < other.homoFactor && homo < person.homoFactor) {
                        Fucker.sex(person, other);
                    }
                } else {
                    double hetero = rand.nextDouble();
                    if (hetero > other.homoFactor && hetero > person.homoFactor) {
                        Fucker.sex(person, other);
                    }
                }
            }
        }
    }

    static Map<String, Integer> sexDone = new HashMap<>();

    public double onsExposure(Person traitor1, Person traitor2) {
        Person partner1 = traitor1.isMarried() ? traitor1.getMarriage().getPartner(traitor1) : null;
        double first = partner1 != null ? (traitor1.importance * 0.3 + partner1.jealousFactor * 0.7)
                : traitor1.importance * 0.3;
        Person partner2 = traitor2.isMarried() ? traitor2.getMarriage().getPartner(traitor2) : null;
        double second = partner2 != null ? (traitor2.importance * 0.3 + partner2.jealousFactor * 0.7)
                : traitor2.importance * 0.3;
        return (first + second) * 0.05;
    }

    public double affairExposure(Person traitor1, Person traitor2) {
        Person partner1 = traitor1.isMarried() ? traitor1.getMarriage().getPartner(traitor1) : null;
        double first = partner1 != null ? (traitor1.importance * 0.2 + partner1.jealousFactor * 0.8)
                : traitor1.importance * 0.4;
        Person partner2 = traitor2.isMarried() ? traitor2.getMarriage().getPartner(traitor2) : null;
        double second = partner2 != null ? (traitor2.importance * 0.2 + partner2.jealousFactor * 0.8)
                : traitor2.importance * 0.4;
        return (first + second) * 0.2;
    }

    public double bastardExposure(Person traitor1, Person traitor2) {
        Person partner1 = traitor1.isMarried() ? traitor1.getMarriage().getPartner(traitor1) : null;
        double first = partner1 != null ? (traitor1.importance * 0.3 + partner1.jealousFactor * 0.7)
                : traitor1.importance * 0.3;
        Person partner2 = traitor2.isMarried() ? traitor2.getMarriage().getPartner(traitor2) : null;
        double second = partner2 != null ? (traitor2.importance * 0.3 + partner2.jealousFactor * 0.7)
                : traitor2.importance * 0.3;
        return (first + second) * 0.15;
    }

    private static void timeAfterTime(Date startTime) throws IOException {
        for (Family f : Familiar.families) {
            String firstDictionary = "G:/Szlachta/All/"
                    + startTime.toString().replace(":", "_").replace(" CEST 2022", "").replace("Sun Sep 04", "")
                    + "/";
            Files.createDirectories(Paths.get(firstDictionary));
            String fileName = firstDictionary + f.surname + "_ 14_43_43.txt";
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            ObjectMapper mapper = new ObjectMapper();
            try {
                String json = mapper.writeValueAsString(f);
                printWriter.println(json);
            } catch (Exception e) {
                e.printStackTrace(printWriter);
            }
            if (f.nestor != null) {
//					System.out.println("KIDS "+f.nestor.getName() + " - " + f.nestor.kids.size());
            }
            printWriter.close();
            fileWriter.close();
        }

        for (Family f : Familiar.families) {
            if (f.nestor != null) {

            }
        }
        for (Family family : Familiar.families) {

            if (!family.plebs) {
                System.out.println("\n" + family.surname.toUpperCase());
                family.nestor.printWithChildren("", new HashSet<>());

                String shitpart = family.allMembers() < 5 ? "below5_" : "";
                String firstDictionary = "G:/Szlachta/All/" + startTime.toString().replace(":", "_");
                Files.createDirectories(Paths.get(firstDictionary));
                String fileName = firstDictionary + "/" + family.creationDate + "-" + shitpart + family.raceObj.name
                        + "_" + family.surname + ".txt";
                FileWriter fileWriter = new FileWriter(fileName);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                String secondDictionary = "G:/Szlachta/Alive/" + startTime.toString().replace(":", "_");
                Files.createDirectories(Paths.get(secondDictionary));
                String fileName2 = secondDictionary + "/" + family.creationDate + "-" + shitpart
                        + family.raceObj.name + "_" + family.surname + "_MAIN.txt";
                FileWriter fileWriter2 = new FileWriter(fileName2);
                PrintWriter printWriter2 = new PrintWriter(fileWriter2);

                Person nestor;
                if (family.allMembers() > 0)
                    nestor = family.aliveMembers.get(0);
                else if (family.deadMembers.size() > 0)
                    nestor = family.deadMembers.get(0);
                else
                    nestor = null;
                if (nestor != null) {
                    for (Person person : family.deadMembers) {
                        if (person.born.before(nestor.born)) {
                            nestor = person;
                        }
                    }
                    for (Person person : family.aliveMembers) {
                        if (person.born.before(nestor.born)) {
                            nestor = person;
                        }
                    }
                }
                List<Person> elves = new ArrayList<>();
                List<Person> humans = new ArrayList<>();
                List<Person> dwarfs = new ArrayList<>();
                List<Person> halfs = new ArrayList<>();
                List<Person> gnomes = new ArrayList<>();
                List<Person> peuras = new ArrayList<>();
                List<Person> blossoms = new ArrayList<>();
                double sum = 0;

                if (family.allMembers() == 0) {
                    printWriter.println("RODZINA WYMARŁA");
                    printWriter2.println("RODZINA WYMARŁA");
                } else {
                    int elvesCount = 0;
                    int humansCount = 0;
                    int dwarfsCount = 0;
                    int halfsCount = 0;
                    int gnomesCount = 0;
                    int peurasCount = 0;
                    int blossomsCount = 0;
                    for (Person member : family.aliveMembers) {
                        sum += member.homoFactor;
                        if (member.raceObj == Racist.human) {
                            humansCount++;
                            humans.add(member);
                        }
                        if (member.raceObj == Racist.elf) {
                            elvesCount++;
                            elves.add(member);
                        }
                        if (member.raceObj == Racist.dwarf) {
                            dwarfsCount++;
                            dwarfs.add(member);
                        }
                        if (member.raceObj == Racist.half) {
                            halfsCount++;
                            halfs.add(member);
                        }
                        if (member.raceObj == Racist.gnome) {
                            gnomesCount++;
                            gnomes.add(member);
                        }
                        if (member.raceObj == Racist.peura) {
                            peurasCount++;
                            peuras.add(member);
                        }
                        if (member.raceObj == Racist.blossom) {
                            blossomsCount++;
                            blossoms.add(member);
                        }
                    }
//				System.out.println("# " + (sum / family.allMembers()));
                    printWriter2.println(
                            "Wszyscy: " + family.allMembers() + ", Ludzie: " + humansCount + ", Elfy: " + elvesCount
                                    + ", Krasnoludy: " + dwarfsCount + ", Niziołki: " + halfsCount + ", Gnomy: "
                                    + gnomesCount + ", Peura: " + peurasCount + ", Blossomici: " + blossomsCount);
                }
                printWriter.println(printPerson(null, "", nestor, family));

                printWriter.close();

                Comparator<Person> comparator1 = (Person p1, Person p2) -> -Integer.compare(p1.age, p2.age);
                printWriter2.println("\n\n\n\n");
                humans.sort(comparator1);
                elves.sort(comparator1);
                dwarfs.sort(comparator1);
                printWriter2.println("Ludzie:");
                for (Person person : humans) {
                    printWriter2.println(printAliveMember(person));
                }
                printWriter2.println("Elfy:");
                for (Person person : elves) {
                    printWriter2.println(printAliveMember(person));
                }
                printWriter2.println("Krasnoludy:");
                for (Person person : dwarfs) {
                    printWriter2.println(printAliveMember(person));
                }
                printWriter2.println("Niziołki:");
                for (Person person : halfs) {
                    printWriter2.println(printAliveMember(person));
                }
                printWriter2.println("Gnomy:");
                for (Person person : gnomes) {
                    printWriter2.println(printAliveMember(person));
                }
                printWriter2.println("Peura:");
                for (Person person : peuras) {
                    printWriter2.println(printAliveMember(person));
                }
                printWriter2.println("Blossomici:");
                for (Person person : blossoms) {
                    printWriter2.println(printAliveMember(person));
                }
                Comparator<Person> comparator2 = (Person p1,
                                                  Person p2) -> -Double.compare(p1.lineImportance, p2.lineImportance);
                printWriter2.println("\n\n\n\n");
                humans.sort(comparator2);
                elves.sort(comparator2);
                dwarfs.sort(comparator2);
                halfs.sort(comparator2);
                gnomes.sort(comparator2);
                peuras.sort(comparator2);
                blossoms.sort(comparator2);
                printWriter2.println("Ludzie:");
                for (Person person : humans) {
                    printWriter2.println(printAliveMember(person));
                }
                printWriter2.println("Elfy:");
                for (Person person : elves) {
                    printWriter2.println(printAliveMember(person));
                }
                printWriter2.println("Krasnoludy:");
                for (Person person : dwarfs) {
                    printWriter2.println(printAliveMember(person));
                }
                printWriter2.println("Niziołki:");
                for (Person person : halfs) {
                    printWriter2.println(printAliveMember(person));
                }
                printWriter2.println("Gnomy:");
                for (Person person : gnomes) {
                    printWriter2.println(printAliveMember(person));
                }
                printWriter2.println("Peura:");
                for (Person person : peuras) {
                    printWriter2.println(printAliveMember(person));
                }
                printWriter2.println("Blossomici:");
                for (Person person : blossoms) {
                    printWriter2.println(printAliveMember(person));
                }
                printWriter2.println("\n\n\n\n");
                printWriter2.println(printPerson(null, "", nestor, family));
                if (nestor != null)
                    printKids(nestor, "  ", printWriter2, family, true);

                printWriter.close();
                printWriter2.close();
            }
        }
    }

}
