package dk.frbsportgruppe1.frbsport.repository;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import dk.frbsportgruppe1.frbsport.model.BookingExceptionRange;
import dk.frbsportgruppe1.frbsport.model.BookingRange;
import dk.frbsportgruppe1.frbsport.model.BookingRangeIndex;
import dk.frbsportgruppe1.frbsport.model.Practitioner;

public interface BookingRangeRepository {

    void populateBookingRangeIndex(BookingRangeIndex bookingRangeIndex);
    void createBookingRange(int dayOfWeek, LocalTime startTime, LocalTime endTime, Practitioner practitioner);
    void createBookingRangeException(LocalDateTime startDateTime, LocalDateTime endDateTime, Practitioner practitioner);

}
