package dk.frbsportgruppe1.frbsport.model;

import java.time.LocalTime;

public interface Booking {
    LocalTime getStartTime();
    void setStartTime(LocalTime localTime);

    LocalTime getEndTime();
    void setEndTime(LocalTime localTime);

    String getTitle();
    void setTitle(String title);



}
