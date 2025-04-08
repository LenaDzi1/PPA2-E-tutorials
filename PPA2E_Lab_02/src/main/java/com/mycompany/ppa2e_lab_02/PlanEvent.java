/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ppa2e_lab_02;

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
	public PlanEvent(String tutor, int start, int end, int dayOfWeek, Subject subject) {
		this.tutor = tutor;
		this.start = start;
		this.end = end;
		this.dayOfWeek = dayOfWeek;
		this.subject = subject;				
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
		if (this.end <= other.start) {
			return false;
		}
		if (other.end <= this.start) {
			return false;}
		return true;
	}
	
	public static void main(String[] args) {
		PlanEvent event1 = new PlanEvent("František Vonásek", 10, 13, 1, Subject.math);
		PlanEvent event2 = new PlanEvent("Čeněk Landsmann", 9, 12, 2, Subject.computers);
		PlanEvent event3 = new PlanEvent("Hubert Zámožný", 11, 14, 3, Subject.math);
		PlanEvent event4 = new PlanEvent("Dobromila Musilová-Wébrová", 9, 14, 4, Subject.computers);
		PlanEvent event5 = new PlanEvent("Sisoj Psoič Rispoloženskyj", 11, 12, 5, Subject.math);
		PlanEvent event6 = new PlanEvent("Billy Blaze", 8, 10, 6, Subject.computers);
		PlanEvent event7 = new PlanEvent("Flynn Taggart", 13, 15, 7, Subject.math);
		System.out.println(event1.isInConflict(event2));			
		System.out.println(event1.isInConflict(event3));
		System.out.println(event1.isInConflict(event4));
		System.out.println(event1.isInConflict(event5));
		System.out.println(event1.isInConflict(event6));
		System.out.println(event1.isInConflict(event7));
                
                ////////////////////// introduction /////////////////////
                Point point = new Point (1,1);
                Point a = new Point (1,1);
                Point b = new Point (2,2);
                double x = a.getX();
                double dis = a.distanceTo(b);
                System.out.println("Distance from a to b:" + dis);
                Point[] points = {a,b};
                Map map = new Map(points);
                
                PlanEvent[] timetable = {event1, event2, event3, event4, event5, event6, event7};
                
                
                Plan plan = new Plan (timetable);
                System.out.println("IsConflict: " + plan.isConflict());
                System.out.println("IsOK: " +plan.isOK());
                        
                
	}
}