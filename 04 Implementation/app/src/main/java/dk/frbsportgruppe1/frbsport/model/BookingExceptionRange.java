package dk.frbsportgruppe1.frbsport.model;

import java.time.LocalDateTime;

public interface BookingExceptionRange {
    LocalDateTime getStartDateTime();
    void setStartDateTime(LocalDateTime localDateTime);

    LocalDateTime getEndDateTime();
    void setEndDateTime(LocalDateTime localDateTime);


}
