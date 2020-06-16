package dk.frbsportgruppe1.frbsport.repository;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Observable;

import dk.frbsportgruppe1.frbsport.model.BookingExceptionRange;
import dk.frbsportgruppe1.frbsport.model.BookingExceptionRangeImpl;
import dk.frbsportgruppe1.frbsport.model.BookingRange;
import dk.frbsportgruppe1.frbsport.model.BookingRangeImpl;
import dk.frbsportgruppe1.frbsport.model.BookingRangeIndex;

public class BookingRangeRepositoryImpl implements BookingRangeRepository {
    private static final String TAG = "BookingRangeRepositoryImpl";
    FirebaseFirestore db;

    public BookingRangeRepositoryImpl() {
        this.db = FirebaseFirestore.getInstance();
    }

    @Override
    public void populateBookingRangeIndex(BookingRangeIndex bookingRangeIndex) {
        LocalTime startTime = LocalTime.parse("12:00", DateTimeFormatter.ISO_LOCAL_TIME);
        LocalTime endTime = LocalTime.parse("20:00", DateTimeFormatter.ISO_LOCAL_TIME);
        LocalDateTime startDateTime = LocalDateTime.parse("2020-06-15T14:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime endDateTime = LocalDateTime.parse("2020-06-15T16:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);


        BookingRange bookingRange = new BookingRangeImpl(DayOfWeek.MONDAY, startTime, endTime);
        bookingRangeIndex.addBookingRange(bookingRange);
        Log.d(TAG, "populateBookingRangeIndex: bookingrangelist size:" + bookingRangeIndex.getBookingRanges().size());
        BookingExceptionRange bookingExceptionRange = new BookingExceptionRangeImpl(startDateTime, endDateTime);
        bookingRangeIndex.addBookingExceptionRange(bookingExceptionRange);
        Log.d(TAG, "populateBookingRangeIndex: bookingExceptionRange size: " + bookingRangeIndex.getBookingExceptionRanges().size());

    }
}
