package dk.frbsportgruppe1.frbsport.repository;

import com.applandeo.materialcalendarview.EventDay;

import java.util.List;

import dk.frbsportgruppe1.frbsport.model.Calendar;
import dk.frbsportgruppe1.frbsport.model.exceptions.FilterTypeIsNullException;

public interface CalendarRepository{
    public void filter(String type) throws FilterTypeIsNullException;
    public void populateCalendar(Calendar calendar);
}