package royal.model;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value = "families")
public class Royal {
	
	public List<Family> families = new ArrayList<>();
	public List<Person> people = new ArrayList<>();
	
}
