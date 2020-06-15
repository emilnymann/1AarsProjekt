package dk.frbsportgruppe1.frbsport.viewmodel;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.model.BookingExceptionRange;
import dk.frbsportgruppe1.frbsport.model.BookingRange;
import dk.frbsportgruppe1.frbsport.model.BookingRangeIndex;

public class BookingCalenderViewModel extends Observable implements Observer {
    private static final String TAG = "BookingCalenderViewModel";

    private ArrayList<BookingRange> bookingRanges;
    private ArrayList<BookingExceptionRange> bookingExceptionRanges;

    public BookingCalenderViewModel(Observable bookingRangeIndex) {
        bookingRangeIndex.addObserver(this);
    }

    public ArrayList<BookingRange> getBookingRanges() {
        return bookingRanges;
    }

    public ArrayList<BookingExceptionRange> getBookingExceptionRanges() {
        return bookingExceptionRanges;
    }


    @Override
    public void update(Observable o, Object arg) {
        BookingRangeIndex bookingRangeIndex = (BookingRangeIndex) arg;
        bookingRanges = bookingRangeIndex.getBookingRanges();
        bookingExceptionRanges = bookingRangeIndex.getBookingExceptionRanges();
        setChanged();
        notifyObservers();

    }

}
