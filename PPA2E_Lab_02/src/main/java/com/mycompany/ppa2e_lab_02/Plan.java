/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ppa2e_lab_02;


public class Plan {
   private PlanEvent[] timetable;

    public Plan(PlanEvent[] timetable) {
        this.timetable = timetable;
    }

    public PlanEvent[] getTimetable() {
        return timetable;
    }

    public void setTimetable(PlanEvent[] timetable) {
        this.timetable = timetable;
    }
   
    public void addEvent(PlanEvent event) {
        if (this.timetable == null) {
            this.timetable = new PlanEvent[] { event };
        } else {
            PlanEvent[] newEvents = new PlanEvent[this.timetable.length + 1];
            for (int i = 0; i < this.timetable.length; i++) {
                newEvents[i] = this.timetable[i];
            }
            newEvents[this.timetable.length] = event;
            this.timetable = newEvents;
        }
    }
    
    public boolean isConflict() {
       // boolean state = false;
        
        if (timetable.length <= 1){
        return false;
        }
        
        for (int i=0; i <timetable.length; i++ ){
            for (int j = i+1; j<timetable.length; j++){
                if(timetable[i].isInConflict(timetable[j])){
                    return true;
                }
                
            }
        }
        
        return false;
    }
    
    public boolean isOK() {
    if (timetable == null) {
        return false;
    }
    
    int[] mathDays = new int[7];
    int[] computerDays = new int[7];
    int mathCount = 0;
    int computerCount = 0;
    
    for (PlanEvent event : timetable) {
        
        if (event.subject == Subject.math) {
            boolean exists = false;
            
            for (int i = 0; i < mathDays.length; i++) {
                if (mathDays[i] == event.dayOfWeek) {
                    exists = true; //there is already the 
                    break;
                }
            }
            if (!exists) {
                mathDays[mathCount] = event.dayOfWeek;
                mathCount++;
            }
        } else if (event.subject == Subject.computers) {
            boolean exists = false;
            
            for (int i = 0; i < computerDays.length; i++) {
                if (computerDays[i] == event.dayOfWeek) {
                    exists = true; //there is already the 
                    break;
                }
            }
            if (!exists) {
                computerDays[computerCount] = event.dayOfWeek;
                computerCount++;
            }
            
        }
           
    }
    
    boolean state = (mathCount >= 3 && computerCount >=2);
    return state; 
    }
}
    
    
    
    
    
    
    
    
    
    
   