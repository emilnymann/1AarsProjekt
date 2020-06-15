package dk.frbsportgruppe1.frbsport.model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Observable;

public class BookingRangeImpl implements BookingRange{
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public BookingRangeImpl(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    @Override
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    @Override
    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;

    }

    @Override
    public LocalTime getStartTime() {
        return startTime;
    }

    @Override
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }


}
