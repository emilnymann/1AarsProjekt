package dk.frbsportgruppe1.frbsport.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dk.frbsportgruppe1.frbsport.model.BookingExceptionRange;
import dk.frbsportgruppe1.frbsport.model.BookingExceptionRangeImpl;
import dk.frbsportgruppe1.frbsport.model.BookingRange;
import dk.frbsportgruppe1.frbsport.model.BookingRangeImpl;
import dk.frbsportgruppe1.frbsport.model.BookingRangeIndex;

public class BookingRangeRepositoryImpl implements BookingRangeRepository {
    private static final String TAG = "BookingRangeRepositoryImpl";
    private static final String START_TIME = "start_time";
    private static final String END_TIME = "end_time";
    private static final String DAY_OF_WEEK = "day_of_week";
    public static final String BOOKING_EXCEPTION_RANGES = "booking_exception_ranges";
    public static final String START_DATE_TIME = "start_date_time";
    public static final String END_DATE_TIME = "end_date_time";
    private final String BOOKING_RANGES = "booking_ranges";
    private final String PRACTITIONER = "practitioner";
    FirebaseFirestore db;

    public BookingRangeRepositoryImpl() {
        this.db = FirebaseFirestore.getInstance();
    }

    @Override
    public void populateBookingRangeIndex(BookingRangeIndex bookingRangeIndex) {
        db.collection(BOOKING_RANGES)
                .whereEqualTo(PRACTITIONER, bookingRangeIndex.getPractitioner().getId())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            ArrayList<BookingRange> bookingRanges = new ArrayList<>();
                            for (QueryDocumentSnapshot queryDocumentSnapshot : querySnapshot) {
                                BookingRange bookingRange = new BookingRangeImpl(DayOfWeek.of(Integer.parseInt(queryDocumentSnapshot.getString(DAY_OF_WEEK))),
                                        LocalTime.parse(queryDocumentSnapshot.getString(START_TIME), DateTimeFormatter.ISO_LOCAL_TIME),
                                        LocalTime.parse(queryDocumentSnapshot.getString(END_TIME), DateTimeFormatter.ISO_LOCAL_TIME));
                                bookingRanges.add(bookingRange);
                            }
                            bookingRangeIndex.setBookingRanges(bookingRanges);
                        }
                    }
                });
        
        db.collection(BOOKING_EXCEPTION_RANGES)
                .whereEqualTo(PRACTITIONER, bookingRangeIndex.getPractitioner().getId())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            ArrayList<BookingExceptionRange> bookingExceptionRanges = new ArrayList<>();
                            for (QueryDocumentSnapshot queryDocumentSnapshot : querySnapshot) {
                                BookingExceptionRange bookingExceptionRange = new BookingExceptionRangeImpl(LocalDateTime.parse(queryDocumentSnapshot.getString(START_DATE_TIME), DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                        LocalDateTime.parse(queryDocumentSnapshot.getString(END_DATE_TIME), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                                bookingExceptionRanges.add(bookingExceptionRange);
                            }
                            bookingRangeIndex.setBookingExceptionRanges(bookingExceptionRanges);
                        }
                    }
                });
    }
}
