package dk.frbsportgruppe1.frbsport.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.model.BookingExceptionRange;
import dk.frbsportgruppe1.frbsport.model.BookingExceptionRangeImpl;
import dk.frbsportgruppe1.frbsport.model.BookingRange;
import dk.frbsportgruppe1.frbsport.model.BookingRangeImpl;
import dk.frbsportgruppe1.frbsport.model.BookingRangeIndex;
import dk.frbsportgruppe1.frbsport.model.Practitioner;

public class BookingRangeRepositoryImpl extends Observable implements BookingRangeRepository {

    private static final String TAG = "BookingRangeRepositoryImpl";
    FirebaseFirestore db;

    private static final String START_TIME = "start_time";
    private static final String END_TIME = "end_time";
    private static final String DAY_OF_WEEK = "day_of_week";
    private static final String BOOKING_EXCEPTION_RANGES = "booking_exception_ranges";
    private static final String START_DATE_TIME = "start_date_time";
    private static final String END_DATE_TIME = "end_date_time";
    private static final String BOOKING_RANGES = "booking_ranges";
    private static final String PRACTITIONER = "practitioner";

    public BookingRangeRepositoryImpl() {
        this.db = FirebaseFirestore.getInstance();
    }

    public BookingRangeRepositoryImpl(Observer observer) {
        this.db = FirebaseFirestore.getInstance();
        addObserver(observer);
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

    /**
     * INdsæt en ny ledig tid i Firestore
     * @param dayOfWeek en integer der angiver udedagen. 1 = mandag, 7 = søndag.
     * @param startTime starttidspunktet for det ledige tidsinterval
     * @param endTime sluttidspunktet for det ledige tidsinterval
     * @param practitioner den behandler som den ledige tid tilhører
     */
    @Override
    public void createBookingRange(int dayOfWeek, LocalTime startTime, LocalTime endTime, Practitioner practitioner) {

        Map<String, Object> data = new HashMap<>();

        data.put("day_of_week", String.valueOf(dayOfWeek));
        data.put("start_time", startTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
        data.put("end_time", endTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
        data.put("practitioner", practitioner.getId());

        db.collection(BOOKING_RANGES).add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "onSuccess: booking range added with id " + documentReference.getId());
                setChanged();
                notifyObservers();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: booking range add failed");
            }
        });

    }

    @Override
    public void createBookingRangeException(LocalDateTime startDateTime, LocalDateTime endDateTime, Practitioner practitioner) {

        Map<String, Object> data = new HashMap<>();

        data.put("start_date_time", startDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        data.put("end_date_time", endDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        data.put("practitioner", practitioner.getId());

        db.collection(BOOKING_EXCEPTION_RANGES).add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "onSuccess: booking range exception added with id " + documentReference.getId());
                setChanged();
                notifyObservers();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: booking range exception add failed");
            }
        });

    }
}
