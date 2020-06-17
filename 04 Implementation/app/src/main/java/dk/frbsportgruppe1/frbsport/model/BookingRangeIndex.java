package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;

public interface BookingRangeIndex {


    ArrayList<BookingRange> getBookingRanges();
    void setBookingRanges(ArrayList<BookingRange> bookingRanges);

    ArrayList<BookingExceptionRange> getBookingExceptionRanges();
    void setBookingExceptionRanges(ArrayList<BookingExceptionRange> bookingExceptionRanges);

    void addBookingRange(BookingRange bookingRange);
    void addBookingExceptionRange(BookingExceptionRange bookingExceptionRange);

    Practitioner getPractitioner();
    void setPractitioner(Practitioner practitioner);
}
