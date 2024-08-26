package royal;

import royal.model.Family;
import royal.model.Person;
import royal.model.Race;
import royal.util.Familiar;
import royal.util.simple.Debug;
import royal.util.simple.Timer;

import java.util.Calendar;

public class Testing {
	
	static double fatherinbreeding = 0.6476600832429119;
	static double motherinbreeding = 0.6476600832429119;

	public static void main(String[] args) {

		Calendar first = Timer.getDate(1457);
		Calendar second = Timer.getDate(1452);
		long years = first.get(Calendar.YEAR) - second.get(Calendar.YEAR);
		long days = first.get(Calendar.DAY_OF_YEAR) - second.get(Calendar.DAY_OF_YEAR);
		Debug.log(first.getTime() + " - " + second.getTime());
		Debug.log(years + " - " + days);

/*
		Race race = new Race(0, 0, 0, 0, fatherinbreeding, fatherinbreeding, fatherinbreeding, fatherinbreeding, fatherinbreeding, args, args, args, fatherinbreeding, fatherinbreeding, fatherinbreeding, fatherinbreeding, motherinbreeding, fatherinbreeding);
		Family family = new Family(null, 0, race , 0);
		Person p = new Person(null, family , null, null, null, null, false, fatherinbreeding, fatherinbreeding, fatherinbreeding, fatherinbreeding, fatherinbreeding, fatherinbreeding, fatherinbreeding, 0, 0, 0, fatherinbreeding, fatherinbreeding, fatherinbreeding, fatherinbreeding, motherinbreeding, fatherinbreeding);
		Debug.log(p.died);
		double parents = 2;
		double ancestors = parents;
		double gparents = parents ;
		ancestors += gparents;
		double first = 1 - (double) ancestors / 6.0;
		double ggparents = gparents + 4;
		ancestors += ggparents;
		double second = 1 - (double) ancestors / 14.0;
		double gggparents = ggparents + 8;
		ancestors += gggparents;
		double third = 1 - (double) ancestors / 30.0;
		double ggggparents = gggparents + 16;
		ancestors += ggggparents;
		double fourth = 1 - (double) ancestors / 62.0;
		double inheritedFactor = (0.2 + Math.sqrt(fatherinbreeding) + Math.sqrt(motherinbreeding));
		Debug.log(inheritedFactor * (first / 1.5 + second / 2.5 + third / 4.5 + fourth / 8.5));
 */
	}

}
