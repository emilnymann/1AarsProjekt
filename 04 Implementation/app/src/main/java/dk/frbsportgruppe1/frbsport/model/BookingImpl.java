package dk.frbsportgruppe1.frbsport.model;

import java.time.LocalTime;

public class BookingImpl implements Booking {
    LocalTime startTime;
    LocalTime endTime;
    String title;

    public BookingImpl(LocalTime startTime, LocalTime endTime, String title) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
    }

    @Override
    public LocalTime getStartTime() {
        return startTime;
    }

    @Override
    public void setStartTime(LocalTime localTime) {
        this.startTime = localTime;
    }

    @Override
    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(LocalTime localTime) {
        this.endTime = localTime;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }
}
