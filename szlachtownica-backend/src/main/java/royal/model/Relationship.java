package royal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Calendar;

@JsonRootName(value = "Relationship", namespace = "relationships")
public class Relationship {
	public RelationType type;
	@JsonIgnore
	public Person first;
	@JsonIgnore
	public Person second;
	public long left;
	public long right;
	public Calendar startDate;
	public Calendar endDate;

	public Relationship(RelationType type, Person first, Person second, Calendar startDate) {
		super();
		this.type = type;
		this.first = first;
		this.startDate = startDate;
		left = first.id;
		if (second != null) {
			this.second = second;
			right = second.id;
		}
	}

	@JsonIgnore
	public String description() {
		return type + "\t" + first.getName() + "\t" + (startDate != null ? startDate.get(Calendar.YEAR) : "")
				+ "\t" + (second != null ? second.getName() : "random") + "\t" + (endDate != null ? endDate.get(Calendar.YEAR) : "");
	}

	@JsonIgnore
	public void end(Calendar date) {
//		if (type == RelationType.LEGAL) System.out.println("End of relation:\t" + first.getName() + "\t" + (first.died != null ? first.died.get(Calendar.YEAR) : "") + "\t" + second.getName() + "\t" + (second.died != null ? second.died.get(Calendar.YEAR) : "") + "\t" + first.family.creationDate);
		endDate = date;
	}

	@JsonIgnore
	public Person getPartner(Person person) {
		if (person == first)
			return second;
		else
			return first;
	}

	@JsonIgnore
	public boolean isActive() {
		return endDate == null;
	}

}
