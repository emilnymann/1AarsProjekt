package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;
import java.util.Observable;

public class BookingRangeIndexImpl extends Observable implements BookingRangeIndex {
    private ArrayList<BookingRange> bookingRanges;
    private ArrayList<BookingExceptionRange> bookingExceptionRanges;
    private Practitioner practitioner;

    public BookingRangeIndexImpl(Practitioner practitioner) {
        this.bookingRanges = new ArrayList<>();
        this.bookingExceptionRanges = new ArrayList<>();
        this.practitioner = practitioner;
    }

    @Override
    public ArrayList<BookingRange> getBookingRanges() {
        return bookingRanges;
    }

    @Override
    public void setBookingRanges(ArrayList<BookingRange> bookingRanges) {
        this.bookingRanges = bookingRanges;
        setChanged();
        notifyObservers(this);
    }

    @Override
    public ArrayList<BookingExceptionRange> getBookingExceptionRanges() {
        return bookingExceptionRanges;
    }

    @Override
    public void setBookingExceptionRanges(ArrayList<BookingExceptionRange> bookingExceptionRanges) {
        this.bookingExceptionRanges = bookingExceptionRanges;
        setChanged();
        notifyObservers(this);
    }

    @Override
    public void addBookingRange(BookingRange bookingRange) {
        bookingRanges.add(bookingRange);
        setChanged();
        notifyObservers(this);
    }

    @Override
    public void addBookingExceptionRange(BookingExceptionRange bookingExceptionRange) {
        bookingExceptionRanges.add(bookingExceptionRange);
        setChanged();
        notifyObservers(this);
    }

    @Override
    public Practitioner getPractitioner() {
        return practitioner;
    }

    @Override
    public void setPractitioner(Practitioner practitioner) {
        this.practitioner = practitioner;
    }


}
