# Assignment 2
# Motivation
Student Support Centre offers help with math and computer science courses to students of the first year, in the form of consultation with students of higher years (tutors). Every month, it is necessary to construct a schedule of consultation that avoids scheduling conflicts. Tutors create their offers (offered time, i.e. at what time the tutor can start and end, and the subject being offered), and a conflict-free schedule is built from the offers. We will create a program that finds such conflict-free schedules.

# Task 1 (debugging) (up to 2 points)
The following source code defines a PlanEvent class, which represents one particular offering of a consultation. It defines a method boolean isInConflict(PlanEvent other), which tests, whether or not an offer is in time conflict with another offer (i.e. a time overlap, if an offer ends exactly at the same time as another starts, they are not in conflict). The program contains both syntactic and semantic errors, find them and fix them.

```
/**
 * Consultation subjects
 * @author Libor Vasa
 */
enum Subject {math, computers}

/**
 * An offer of a consultation
 * @author Libor Vasa
 */
public class PlanEvent {
	/** tutor name */
	public String tutor;
	/** start time (10 = 10:00 etc.) */
	public int start;
	/** end time (10 = 10:00 etc.) */
	public int end;
	/** day of week (0 = Monday, 1 = Tuesday ets.) */
	public int dayOfWeek; 
	/** Subject of the offered consultation */
	public Subject subject;
	
	/**
	 * Creates an offer of a consultation
	 */
	public PlanEvent(string tutor, int start, int end, int dayOfWeek, Subject subject) {
		tutor = tutor;
		start = start;
		end = end;
		dayOfWeek = dayOfWeek;
		subject = subject;				
	}
	
	/**
	 * Returns true when an overlap with another consultation is detected, otherwise returns false
	 * @param other event (consultation offer) which may overlap this event.
	 * @return true, if this event overlaps with the other event, otherwise reutnrs false
	 */
	public boolean isInConflict(PlanEvent other) {
		if (this.dayOfWeek != other.dayOfWeek) {
			return false;
		}
		if (this.end >= other.start) {
			return false;
		}
		if (other.end >= this.start) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		PlanEvent event1 = new PlanEvent("František Vonásek", 10, 13, 1, Subject.math);
		PlanEvent event2 = new PlanEvent("Čeněk Landsmann", 9, 12, 1, Subject.computers);
		PlanEvent event3 = new PlanEvent("Hubert Zámožný", 11, 14, 1, Subject.math);
		PlanEvent event4 = new PlanEvent("Dobromila Musilová-Wébrová", 9, 14, 1, Subject.computers);
		PlanEvent event5 = new PlanEvent("Sisoj Psoič Rispoloženskyj", 11, 12, 1, Subject.math);
		PlanEvent event6 = new PlanEvent("Billy Blaze", 8, 10, 1, Subject.computers);
		PlanEvent event7 = new PlanEvent("Flynn Taggart", 13, 15, 1, Subject.math);
		System.out.println(event1.isInConflict(event2));			
		System.out.println(event1.isInConflict(event3));
		System.out.println(event1.isInConflict(event4));
		System.out.println(event1.isInConflict(event5));
		System.out.println(event1.isInConflict(event6));
		System.out.println(event1.isInConflict(event7));
	}
}
```

# Task 2 (up to 4 points)
add a Plan class which represents a particular shedule of consultations. The class contains an array of PlanEvent instances representing the particular offers as an attribute
add a constructor of the Plan class, which accepts an existing array of offers as a parameter
add a method boolean isConflict() which tests, whether there is a conflict between any two items of the schedule (use the isInConflict method of the PlanEvent class)
add method boolean isOK() which tests, whether the given schedule has at least three consultation of math, each on a different day, and at least two lessons of computers, also each on a different day
# Task 3 (homework) (up to 4 points)
add loading offers from a file. Each offer is represented by 5 lines of the input text file in the following order:
tutor name
offered subject
day of week (integer 0-4)
start time (integer representing the hour)
end time (integer representing the hour)
add a method that constructs all possible sets of 5 offers. For each such set, create an instance of the Plan class and test, whether the schedule is correct (use the isOK method) and conflict free (use the isConflict method). Write the correct and conflict free schedules to the console in some reasonable format. 
test the program on data in the ssc.txt file, which is available on courseware, and finally print out the number of corrent and conflict free schedules the program has found.
