package dk.frbsportgruppe1.frbsport.model;

import java.time.LocalDateTime;

public class BookingExceptionRangeImpl implements BookingExceptionRange {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public BookingExceptionRangeImpl(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;

    }

    @Override
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    @Override
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    @Override
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    @Override
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }


}
