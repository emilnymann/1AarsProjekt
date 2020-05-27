package dk.frbsportgruppe1.frbsport.model;

import java.util.Comparator;

public class SorterBeskeder implements Comparator<Besked> {
    @Override
    public int compare(Besked o1, Besked o2) {
        return o1.hentDatotid().compareTo(o2.hentDatotid());
    }
}
