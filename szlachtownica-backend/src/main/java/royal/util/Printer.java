package royal.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import royal.model.Family;
import royal.model.Person;
import royal.model.Relationship;
import royal.util.simple.Racist;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Printer {
    static List<Person> printedM = new ArrayList<>();
    static List<Person> printed = new ArrayList<>();

    public static void printRelations(Date startTime) {
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

    public static void printToFiles(Date startTime) throws IOException {
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
