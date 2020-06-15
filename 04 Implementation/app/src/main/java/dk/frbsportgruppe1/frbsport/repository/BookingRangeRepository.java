package dk.frbsportgruppe1.frbsport.repository;

import java.util.ArrayList;

import dk.frbsportgruppe1.frbsport.model.BookingExceptionRange;
import dk.frbsportgruppe1.frbsport.model.BookingRange;
import dk.frbsportgruppe1.frbsport.model.BookingRangeIndex;
import dk.frbsportgruppe1.frbsport.model.Practitioner;

public interface BookingRangeRepository {

    void populateBookingRangeIndex(BookingRangeIndex bookingRangeIndex);




}
