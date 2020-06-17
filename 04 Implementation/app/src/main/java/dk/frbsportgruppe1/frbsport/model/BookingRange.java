package dk.frbsportgruppe1.frbsport.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface BookingRange {

    DayOfWeek getDayOfWeek();
    void setDayOfWeek(DayOfWeek dayOfWeek);

    LocalTime getStartTime();
    void setStartTime(LocalTime localTime);

    LocalTime getEndTime();
    void setEndTime(LocalTime localTime);

}
