package royal.util;

import royal.model.Family;
import royal.model.Person;
import royal.model.Person.Status;
import royal.util.simple.Timer;
import royal.util.simple.*;

import java.util.*;

public class Familiar {
    public static Family humanPlebs;
    public static Family elfPlebs;
    public static Family dwarfPlebs;
    public static Family halflingPlebs;
    public static Family gnomePlebs;
    public static Family peuraPlebs;
    public static Family blossomPlebs;

    public static Family holzer;
    public static Family tagar;
    public static Person garvonI;
    public static Family gloryhammer;
    public static Person mereinaI;
    public static Person ranalII;
    public static Person galinaSister;
    public static Person kaitra;
    public static Person sibrim;

    public static Set<Family> families = new HashSet<>();
    public static Set<Family> plebs = new HashSet<>();
    static List<Family[]> vassals = new ArrayList<>();
    static boolean wifeDeadBool = false;
    private static Random rand = new Random();
    public static List<Family> familiesList = new ArrayList<>();

    public static void fillFamilies() {
        Racist.prepare();
        Ares.prepareWars();
        prepareVassals();
        humanPlebs = new Family("H_PLACEHOLDER", 0, Racist.human, 0, true);
        elfPlebs = new Family("E_PLACEHOLDER", 0, Racist.elf, 0, true);
        dwarfPlebs = new Family("D_PLACEHOLDER", 0, Racist.dwarf, 0, true);
        halflingPlebs = new Family("N_PLACEHOLDER", 0, Racist.half, 0, true);
        gnomePlebs = new Family("G_PLACEHOLDER", 0, Racist.gnome, 0, true);
        peuraPlebs = new Family("P_PLACEHOLDER", 0, Racist.peura, 0, true);
        blossomPlebs = new Family("B_PLACEHOLDER", 0, Racist.blossom, 0, true);
        tagar = new Family("Tagar", 10, Racist.human, 1194);
        addFamily(tagar, 0);
        Person tagara = new Person("Tagara I", tagar, null, null, Timer.getDate(1168), Status.LEGAL_CHILD, true, 0.7,
                0.3, 0.98, 0, 0, 1, 1, 25, 25, 3, 1, 0, 0.8, 0.2, 0.3, 0.05);
        tagar.nestor = tagara;
        Person tagarasHusband = new Person(Namer.getName(false, Racist.human), Familiar.humanPlebs, null, null, Timer.getDate(1166),
                Status.LEGAL_CHILD, true, 0.7, 0.4, 0.98, 0, 0, 1, 1, 25, 25, 3, 1, 0, 0.85, 0.05, 0.1, 0.4);
        tagar.addMember(tagara);
        Marriager.marriage(tagara, tagarasHusband, Timer.getDate(1191));
        tagara.died = Timer.getDate(1226);
        Person erwinI = new Person("Erwin I", tagar, null, tagara, Timer.getDate(1195), Status.LEGAL_CHILD, false, 0.5,
                0.1, 0.98, 0, 0, 1, 1, 30, 30, 5, 0.2, 0, 0, 0, 0, 0.05);
        erwinI.died = Timer.getDate(1260);
        tagara.knownKids.add(erwinI);
        Person eolI = new Person("Eol I", tagar, erwinI, null, Timer.getDate(1240), Status.LEGAL_CHILD, false, 0.9, 0.5,
                0.1, 1, 0.7, 1, 1, 16, 26, 0, 0.7, 0.8, 0, 0.8, 0.02, 0.9);
        eolI.died = Timer.getDate(1277);
        erwinI.knownKids.add(eolI);
        Person merinaIII = new Person("Merina III", tagar, erwinI, null, Timer.getDate(1244), Status.LEGAL_CHILD, true,
                0.7, 0.5, 0.8, 0, 0, 1, 1, 12, 12, 1, 0.9, 0.2, 0.2, 0.02, 0.02, 0.2);
        merinaIII.died = Timer.getDate(1291);
        erwinI.knownKids.add(merinaIII);
        Person erwinII = new Person("Erwin II", tagar, null, merinaIII, Timer.getDate(1267), Status.LEGAL_CHILD, false,
                0.7, 0.5, 0.8, 0, 0, 1, 1, 16, 21, 1, 0.99, 0, 0.8, 0, 0, 0.5);
        erwinII.died = Timer.getDate(1312);
        merinaIII.knownKids.add(erwinII);
        Person eolII = new Person("Eol II", tagar, erwinII, null, Timer.getDate(1289), Status.LEGAL_CHILD, false, 0.1,
                0.1, 1, 0, 0, 1, 1, 1, 18, 4, 0.1, 0, 0.05, 0.9, 0.7, 0);
        eolII.died = Timer.getDate(1346);
        erwinII.knownKids.add(eolII);
        Person ranalI = new Person("Ranal I", tagar, eolII, null, Timer.getDate(1316), Status.LEGAL_CHILD, false, 0.4,
                0.1, 0.8, 0, 0, 1, 1, 20, 30, 1, 0.3, 0.1, 0.6, 0.2, 0.8, 0.03);
        ranalI.died = Timer.getDate(1383);
        eolII.knownKids.add(ranalI);
        Person erwinIII = new Person("Erwin III", tagar, ranalI, null, Timer.getDate(1362), Status.LEGAL_CHILD, false,
                0.04, 0.1, 0.8, 0, 0, 1, 1, 14, 15, 1, 0.98, 0, 0.5, 0.2, 0.04, 0.9);
        erwinIII.died = Timer.getDate(1416);
        ranalI.knownKids.add(erwinIII);
        Person roelekI = new Person("Roelek I", tagar, erwinIII, null, Timer.getDate(1383), Status.LEGAL_CHILD, false,
                0.5, 0.1, 0.99, 0, 0, 1, 1, 20, 34, 20, 0.95, 0.2, 0.2, 0.3, 0.1, 0.9);
        roelekI.died = Timer.getDate(1439);
        erwinIII.knownKids.add(roelekI);
        Person priestes = new Person(Namer.getName(true, Racist.human), tagar, erwinIII, null, Timer.getDate(1384),
                Status.ACCEPTED_BASTARD, false, 0.5, 0.1, 0.99, 0, 0, 1, 1, 20, 34, 20, 0.8, 0, 0, 0.1, 0.1, 0.4);
        priestes.died = Timer.getDate(1443);
        erwinIII.knownKids.add(priestes);
        priestes.planedMarriage = roelekI;
        roelekI.planedMarriage = priestes;
        Person aleraI = new Person("Alera I", tagar, roelekI, null, Timer.getDate(1420), Status.LEGAL_CHILD, true, 0.99,
                0.1, 0.8, 0, 0, 1, 1, 20, 22, 1, 0.3, 0.2, 0.05, 0.5, 0.05, 0.01);
        aleraI.died = Timer.getDate(1454);
        roelekI.knownKids.add(aleraI);
        garvonI = new Person("Garvon I", tagar, null, aleraI, Timer.getDate(1444), Status.LEGAL_CHILD, false, 0.5, 0, 1,
                0, 0, 1, 1, 15, 18, 95, 1, 0, 0.6, 0.3, 0.3, 0.2);
        garvonI.plannedKids = 0;
        garvonI.died = Timer.getDate(1497);
        aleraI.knownKids.add(garvonI);
        holzer = new Family("Holzer", 9, Racist.human, 1183);
        addFamily(holzer, 1);
        Person garvonII = new Person("Garvon II", holzer, null, null, Timer.getDate(1460), Status.LEGAL_CHILD, false,
                0.8, 0.3, 0.9, 0, 0, 1, 1, 20, 20, 1, 0.98, 0, 0.1, 0.1, 0, 0.2);
        holzer.knownMembers.add(garvonII);
        garvonII.died = Timer.getDate(1527);
        Person osaraI = new Person("Osara I", holzer, garvonII, null, Timer.getDate(1508), Status.LEGAL_CHILD, true,
                0.8, 0.3, 0.5, 0.2, 0, 1, 1, 20, 20, 1, 0.2, 0.7, 0, 0.8, 0, 0.8);
        osaraI.died = Timer.getDate(1552);
        garvonII.knownKids.add(osaraI);
        Person lasteraI = new Person("Lastera I", holzer, null, osaraI, Timer.getDate(1536), Status.LEGAL_CHILD, true,
                0.8, 0.3, 0.9, 0, 0, 1, 1, 20, 20, 1, 0.7, 0.2, 0.05, 0.05, 0.05, 0.3);
        osaraI.knownKids.add(lasteraI);
        lasteraI.died = Timer.getDate(1588);
        Person garvonIII = new Person("Garvon III", holzer, null, lasteraI, Timer.getDate(1566), Status.LEGAL_CHILD,
                false, 0.6, 0.3, 0.6, 0, 0, 1, 1, 14, 14, 1, 0.1, 0.4, 0.2, 0.4, 0.05, 0.4);
        lasteraI.knownKids.add(garvonIII);
        garvonIII.died = Timer.getDate(1619);
        Person galinaI = new Person("Galina I", holzer, garvonIII, null, Timer.getDate(1583), Status.LEGAL_CHILD, true,
                0.8, 0.99, 0.99, 0, 0, 1, 1, 20, 20, 1, 1, 0, 1, 1, 1, 0.05);
        galinaSister = new Person(Namer.getName(true, Racist.human), holzer, garvonIII, null, Timer.getDate(1586),
                Status.LEGAL_CHILD, true, 0.8, 0.99, 0.9, 0, 0, 1, 1, 20, 20, 1, 0.8, 0.1, 0, 2, 0.1, 0.2);
        garvonIII.knownKids.add(galinaI);
        garvonIII.knownKids.add(galinaSister);
        galinaI.died = Timer.getDate(1637);
        galinaSister.died = Timer.getDate(1643);
        ranalII = new Person("Ranal II", holzer, null, galinaI, Timer.getDate(1614), Status.LEGAL_CHILD, false, 0.8,
                0.1, 0.9, 0, 0, 1, 1, 90, 90, 1, 0.1, 0.03, 0.8, 0.6, 0.9, 0.2);
        galinaI.knownKids.add(ranalII);
        ranalII.died = Timer.getDate(1641);
        mereinaI = new Person("Mereina I", holzer, null, galinaSister, Timer.getDate(1624), Status.LEGAL_CHILD, true,
                0.8, 0.3, 0.9, 0, 0, 1, 1, 17, 17, 1, 0.7, 0.01, 0.03, 0.03, 0.03, 0.5);
        galinaSister.knownKids.add(mereinaI);
        // holzer.knownMembers.add(mereinaI);
        mereinaI.died = Timer.getDate(1697);
        Person aleraII = new Person("Alera II", holzer, null, mereinaI, Timer.getDate(1650), Status.LEGAL_CHILD, true,
                0.8, 0.15, 0.99, 0, 0, 1, 1, 20, 20, 1, 0.4, 0, 0.2, 0, 0, 0.2);
        mereinaI.knownKids.add(aleraII);
        aleraII.died = Timer.getDate(1695);
        Person lasteraII = new Person("Lastera II", holzer, null, aleraII, Timer.getDate(1679), Status.LEGAL_CHILD,
                true, 0.8, 0.3, 0.9, 0, 0, 1, 1, 20, 20, 1, 0.4, 0.2, 0.4, 0.05, 0.1, 0.4);
        aleraII.knownKids.add(lasteraII);
        lasteraII.died = Timer.getDate(1739);
        Person fallonII = new Person("Fallon II", holzer, null, lasteraII, Timer.getDate(1710), Status.LEGAL_CHILD,
                false, 0.8, 0.3, 0.9, 0.99, 0.1, 1, 1, 50, 50, 1, 0.9, 0.3, 0.1, 0.02, 0.1, 0.5);
        lasteraII.knownKids.add(fallonII);

        addFamily(new Family("Suelet", 8, Racist.elf, 836), 2);
        addFamily(new Family("Raevill", 8, Racist.elf, 687), 3);
        addFamily(new Family("Vealer", 7, Racist.elf, 745), 4);
        addFamily(new Family("Astager", 7, Racist.human, 1195), 5);
        addFamily(new Family("Sarrazin", 7, Racist.human, 1195), 6);
        addFamily(new Family("Sarrambert", 8, Racist.human, 1185), 7);
        addFamily(new Family("Morten", 7, Racist.human, 1184), 8);
        addFamily(new Family("Zakrzepek", 7, Racist.half, 1183), 9);
        addFamily(new Family("Burczybrzuszek", 9, Racist.half, 1187), 10);
        addFamily(new Family("Runiczna Piącha", 9, Racist.dwarf, 984), 11);
        for (Family family : families) {
            if (family.strenght < 10) {
                EldrichOnes.createNestor(family);
            }
            familiesList.add(family);
//			for (Person person : family.aliveMembers) {
            // Debug.log(person.getName());
//			}
        }
        for (Family family : families) {
            if (family.strenght > 6) {
                tagar.vassals.add(family);
            }
        }
        for (Family family : families) {
            for (Family vassal : family.vassals) {
                vassal.senior = family;
            }
        }
        Comparator comparator = new Comparator<Family>() {
            @Override
            public int compare(Family o1, Family o2) {
                return -Integer.compare(o1.strenght, o2.strenght);
            }
        };
        List<Family> familist = new ArrayList<>();
        familist.addAll(families);
        Collections.sort(familist, comparator);
        for (Family family : familist) {
            if (family.senior != null) {
                Debug.log(family.senior.surname + "-" + family.surname + "-" + family.strenght + "-" + (family.vassals.size() > 0 ? 1 : 0));
            }
        }

        addFamily(humanPlebs, -1);
        addFamily(elfPlebs, -1);
        addFamily(dwarfPlebs, -1);
        addFamily(halflingPlebs, -1);
        addFamily(gnomePlebs, -1);
        addFamily(peuraPlebs, -1);
        addFamily(blossomPlebs, -1);
        plebs.add(humanPlebs);
        plebs.add(elfPlebs);
        plebs.add(dwarfPlebs);
        plebs.add(halflingPlebs);
        plebs.add(gnomePlebs);
        plebs.add(peuraPlebs);
        plebs.add(blossomPlebs);


		printFamilies();
//        System.exit(0);
    }

    private static void printFamilies() {
        for (Family f : families) {
            Debug.log(f.surname + " - " + f.creationDate);
        }
        for (Family f : families) {
            for (Person p : f.aliveMembers) {
                int age = f.creationDate - p.born.get(Calendar.YEAR);
                Debug.log("### " + p.getName() + "\t" + age + "\tat " + f.creationDate);
            }
        }
    }

    public static void prepareVassals() {
        // attachmentFactor, poliamoricFactor, jealousFactor, impulsiveFactor,
        // proudFactor, amorousFactor
        Family barsaes = new Family("Barsaes", 3, Racist.elf, 823);
        Person aelervesa = new Person("Aelervesa Ino", barsaes, null, null, Timer.getDate(1717), Status.LEGAL_CHILD,
                true, 0.8, 0.3, 0.98, 0.5, 0.1, 1, 1, 30, 35, 1, 1, 0.01, 0.8, 1, 0.6, 0.9);
        barsaes.knownMembers.add(aelervesa);

        Family gillear = new Family("Gillear", 3, Racist.elf, 986);
        Family mondenero = new Family("Mondenero", 3, Racist.human, 1151);
        Person louis = new Person("Louis", mondenero, null, null, Timer.getDate(1705), Status.LEGAL_CHILD, true, 0.8,
                0.8, 0.9, 0.9, 0.1, 1, 1, 40, 40, 1, 0.6, 0.6, 0.2, 0.1, 0.1, 0.2);
        mondenero.knownMembers.add(louis);

        Family elfogrzmot = new Family("Elfogrzmot", 3, Racist.dwarf, 1198);
        Person flarum = new Person("Flarum", elfogrzmot, null, null, Timer.getDate(1569), Status.LEGAL_CHILD, false,
                0.8, 0.3, 0.9, 0, 0, 1, 1, 50, 50, 1, 0.7, 0, 0.2, 0.05, 0.4, 0.1);
        elfogrzmot.knownMembers.add(flarum);
        kaitra = new Person("Kaitra", elfogrzmot, flarum, null, Timer.getDate(1659), Status.LEGAL_CHILD, true, 0.8, 0.3,
                0.9, 0.1, 0.1, 1, 1, 50, 50, 1, 0.7, 0, 0.2, 0.05, 0.4, 0.1);
        flarum.knownKids.add(kaitra);

        Family czerep = new Family("Krwawy Czerep", 3, Racist.dwarf, 1114);
        Person vilgem = new Person("Vilgem", czerep, null, null, Timer.getDate(1548), Status.LEGAL_CHILD, false, 0.8,
                0.3, 0.9, 0, 0, 1, 1, 20, 20, 1, 0.1, 0.2, 0.02, 0.3, 0.2, 0.1);
        czerep.knownMembers.add(vilgem);
        sibrim = new Person("Sibrim", czerep, vilgem, null, Timer.getDate(1652), Status.LEGAL_CHILD, false, 0.8, 0.5,
                0.7, 0.1, 0.1, 1, 1, 50, 50, 1, 0.1, 0.2, 0.02, 0.3, 0.2, 0.1);
        vilgem.knownKids.add(sibrim);

        Family[] gilgamore = {new Family("Szybcioszek", 5, Racist.half, 1176), gillear, mondenero};
        Family[] lertavore = {new Family("Sartori", 5, Racist.human, 1222),
                new Family("Bolinosek", 4, Racist.half, 1229), elfogrzmot};
        Family[] astaria = {new Family("Zielona Wiśnia", 4, Racist.peura, 1499),
                new Family("Davila", 4, Racist.human, 1188), new Family("Korniszonek", 3, Racist.half, 1293),
                new Family("Męczybuła", 2, Racist.dwarf, 1195), new Family("Siroen", 1, Racist.elf, 987)};
        Family[] wornimore = {new Family("Castellano", 4, Racist.human, 1186),
                new Family("Melearis", 4, Racist.elf, 1212), barsaes, new Family("Ośmiorniczek", 2, Racist.half, 1246)};
        Family[] egerenna = {new Family("Lionear", 4, Racist.elf, 789), new Family("Zauszek", 3, Racist.half, 1195),
                new Family("Iloserin", 3, Racist.elf, 983), new Family("Navarra", 2, Racist.human, 1195),
                new Family("Cny Miłorząb", 1, Racist.peura, 1505), new Family("Der'gonall", 1, Racist.gnome, 1499)};
        Family[] korsana = {new Family("Naleśniczek", 3, Racist.half, 1292),
                new Family("Hertezza", 2, Racist.human, 1504)};
        Family[] zelderin = {new Family("Gas'trogall", 3, Racist.gnome, 1263),
                new Family("Molla", 2, Racist.human, 1499), new Family("Pierniczek", 1, Racist.half, 1306)};
        Family[] caravista = {new Family("Carranza", 5, Racist.human, 1199), czerep,
                new Family("Palniczek", 2, Racist.half, 1501)};
        Family[] pernagol = {new Family("Bru'eldell", 4, Racist.gnome, 1198),
                new Family("Paquin", 3, Racist.human, 1499)};
        Family[] xalivore = {new Family("Ser'gevall", 3, Racist.gnome, 1498),
                new Family("Bonnet", 2, Racist.human, 1191)};
        Family[] sartama = {new Family("Gaumont", 5, Racist.human, 1221),
                new Family("Gra'bendell", 4, Racist.gnome, 1262), new Family("Moczydupa", 3, Racist.dwarf, 1235),
                new Family("Erteseas", 3, Racist.elf, 1026)};
        gloryhammer = new Family("Brug'denall", 4, Racist.gnome, 1125);
        Family[] uvarra = {new Family("Szubienicznik", 5, Racist.dwarf, 996), gloryhammer,
                new Family("Smoliszek", 4, Racist.half, 1198), new Family("Zakuty Łeb", 3, Racist.dwarf, 1057)};
        vassals.add(gilgamore);
        vassals.add(lertavore);
        vassals.add(astaria);
        vassals.add(wornimore);
        vassals.add(egerenna);
        vassals.add(korsana);
        vassals.add(zelderin);
        vassals.add(caravista);
        vassals.add(pernagol);
        vassals.add(xalivore);
        vassals.add(sartama);
        vassals.add(uvarra);
    }

    public static void addFamily(Family family, int i) {
        families.add(family);
        if (i >= 0) {
            for (Family family2 : vassals.get(i)) {
                if (family2 != family) {
                    family.vassals.add(family2);
                    families.add(family2);
                }
            }
        }
    }

    public static void repairBloodlines(Family family) {
        Object killDate = Timer.getDate(1493);
        Object dynastyDate = Timer.getDate(1507);
        Object wifeDead = Timer.getDate(1476);
        Object newWife = Timer.getDate(1482);
        if (!sibrim.isMarried() && Timer.currentCalendar.after(Timer.getDate(1692))) {
            Marriager.marriage(sibrim, kaitra);
        }
        if (sibrim.isMarried() && sibrim.getMarriage().getPartner(sibrim) == kaitra
                && Timer.currentCalendar.after(Timer.getDate(1716))) {
            Relater.divorce(sibrim, kaitra);
        }
        if (garvonI.isMarried() && Timer.currentCalendar.after(wifeDead) && !wifeDeadBool) {
            Killer.kill(garvonI.getMarriage().getPartner(garvonI), Timer.currentCalendar);
            wifeDeadBool = true;
        }
        if (!garvonI.isMarried() && Timer.currentCalendar.after(newWife)) {
            Person newWifeObj = new Person(Namer.getName(true, Racist.human), humanPlebs, null, null,
                    Timer.getDate(garvonI.born.get(Calendar.YEAR) + 8), Status.LEGAL_CHILD, true, 0.7,
                    0.5 + 0.5 * rand.nextDouble(), 0.7 + 0.3 * rand.nextDouble(), 0.1 + 0.1 * rand.nextDouble(),
                    0.1 + 0.3 * rand.nextDouble(), 1, 1, 17, 19, 1, 1, 0, StateCreator.jealous(Racist.human), StateCreator.impulsive(Racist.human),
                    StateCreator.proud(Racist.human), StateCreator.amorous(Racist.human));
            newWifeObj.plannedKids = 0;
            Marriager.marriage(garvonI, newWifeObj);
        }
        if (Timer.currentCalendar.after(killDate) && family == tagar) {
            for (int i = 0; i < family.aliveMembers.size(); i++) {
                Person person = family.aliveMembers.get(i);
                if (rand.nextDouble() < 0.1) {
                    Killer.kill(person, Timer.currentCalendar);
                }
            }
        }
        if (Timer.currentCalendar.after(dynastyDate)) {
            holzer.strenght = 10;
        }
    }

    public static boolean familyNeedChildren(Family family) {
        boolean allMembersInRange = allMembersInRange(family); //not too many members
        boolean fewChildren = fewChildren(family); //
        boolean fewFertileBranches = fewFertileBranches(family);
        return allMembersInRange && fewChildren && fewFertileBranches;
    }

    private static boolean allMembersInRange(Family family) {
        return family.aliveMembers.size() < 50;
    }

    private static boolean fewChildren(Family family) {
        return family.childrenCount() < 10;
    }

    private static boolean fewFertileBranches(Family family) {
        return true;
    }

    public static boolean familyAllowsHomo(Family family) {
        return true;
    }
}
