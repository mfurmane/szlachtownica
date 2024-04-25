package royal.util.simple;

import royal.model.Race;

import java.util.Random;

public class Racist {
	private static Random rand = new Random();
	private static Race[] races = new Race[7];

	public static Race human = new Race(80, 14, 16, 65, 0.99, 0.99, 0.6, 0.5, 0.01, Namer.femaleHumanNames, Namer.maleHumanNames,
			null, 0.5, 0.5, 0.7, 0.7, 0.7, 0.75);
	public static Race elf = new Race(900, 30, 80, 700, 0.05, 0.45, 0.95, 0.2, 0.001, Namer.femaleElfNames, Namer.maleElfNames,
			Namer.middleNames, 0.7, 0.5, 0.5, 0.8, 0.2, 0.8);
	public static Race dwarf = new Race(320, 30, 30, 250, 0.85, 0.5, 0.8, 0.6, 0.005, Namer.femaleDwarfNames,
			Namer.maleDwarfNames, null, 0.4, 0.2, 0.85, 0.5, 0.8, 0.4);
	public static Race half = new Race(120, 14, 50, 100, 0.7, 0.7, 0.8, 0.3, 0.01, Namer.femaleHalflingNames,
			Namer.maleHalflingNames, null, 0.8, 0.1, 0.8, 0.4, 0.4, 0.7);
	public static Race gnome = new Race(240, 20, 30, 210, 0.85, 0.5, 0.8, 0.3, 0.007, Namer.gnomeNames, Namer.gnomeNames, null,
			0.5, 0.35, 0.65, 0.2, 0.4, 0.4);
	public static Race peura = new Race(160, 20, 40, 120, 0.9, 0.3, 0.9, 0.2, 0.006, Namer.femalePeuraNames, Namer.malePeuraNames,
			null, 0.8, 0.4, 0.6, 0.4, 0.2, 0.5);
	public static Race blossom = new Race(2000, 200, 400, 2000, 0.05, 0.15, 0.95, 0.2, 0.001, Namer.femalePeuraNames,
			Namer.malePeuraNames, null, 0.7, 0.5, 0.5, 0.8, 0.2, 0.8);
	
	public static void prepare() {
		elf.name = "elf";
		human.name = "human";
		dwarf.name = "dwarf";
		half.name = "halfling";
		gnome.name = "gnome";
		peura.name = "peura";
		blossom.name = "blossom";
		dwarf.surname = false;
		blossom.surname = false;
		races[0] = human;
		races[1] = elf;
		races[2] = dwarf;
		races[3] = half;
		races[4] = gnome;
		races[5] = peura;
		races[6] = blossom;
	}

	public static Race randomRace(Race personRace) {
		Race otherRace = null;
		while (otherRace == null || otherRace == personRace) {
			if (rand.nextDouble() < 0.01) {
				otherRace = blossom;
			}
			if (rand.nextDouble() < 0.03) {
				otherRace = dwarf;
			}
			if (rand.nextDouble() < 0.1) {
				otherRace = gnome;
			}
			int i = rand.nextInt(6);
			while (i == 2 || i == 4) {
				i = rand.nextInt(6);
			}
			otherRace = Racist.races[i];
		}
		return otherRace;
	}
}
