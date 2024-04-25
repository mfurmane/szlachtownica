package royal.util.simple;

import royal.model.Person;

import java.util.Calendar;

public class Ager {

	public static void handleAge(Person person) {
		person.age = Timer.currentCalendar.get(Calendar.YEAR) - person.born.get(Calendar.YEAR);
		if (Timer.currentCalendar.get(Calendar.DAY_OF_YEAR) < person.born.get(Calendar.DAY_OF_YEAR)) {
			person.age--;
		}
		if (person.age > person.raceObj.lifespan * 1.099) {
			Killer.kill(person, Timer.currentCalendar);
		}
	}

}
