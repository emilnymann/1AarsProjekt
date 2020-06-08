package dk.frbsportgruppe1.frbsport.repository;

import dk.frbsportgruppe1.frbsport.model.Calendar;
import dk.frbsportgruppe1.frbsport.model.exceptions.FilterTypeIsNullException;

public class CalendarRepositoryImpl implements CalendarRepository{
    private boolean isBookingOn=true;
    private boolean isWorkoutOn=true;

    public void populateCalendar(Calendar calendar){
        //Midertidlig data
        calendar.addEvent("Ben øvelser","Workout",2020,5,9);
        calendar.addEvent("Højre arm","Workout",2020,5,10);
        calendar.addEvent("Behandling 45 min.","Booking",2020,5,10,10,00);
        calendar.addEvent("Behandling 60 min.","Booking",2020,5,12,11,00);
    }

    public void filter(String type) throws FilterTypeIsNullException {
        if(type == null){
            throw new FilterTypeIsNullException("Intet filter angivet");
        }else{
            if(type.contentEquals("Booking")){
                if(isBookingOn){
                    isBookingOn=false;
                }else{
                    isBookingOn=true;
                }
            }else if(type.contentEquals("Workout")){
                if(isWorkoutOn){
                    isWorkoutOn=false;
                }else{
                    isWorkoutOn=true;
                }
            }
        }
    }
}
