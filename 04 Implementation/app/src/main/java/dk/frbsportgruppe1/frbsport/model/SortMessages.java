package dk.frbsportgruppe1.frbsport.model;

import java.util.Comparator;

public class SortMessages implements Comparator<Message> {
    @Override
    public int compare(Message o1, Message o2) {
        return o1.getDateTime().compareTo(o2.getDateTime());
    }
}
