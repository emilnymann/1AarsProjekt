package dk.frbsportgruppe1.frbsport.model;

import dk.frbsportgruppe1.frbsport.model.exceptions.BeskedErNullException;

public interface BeskedhistorikInterface {

    void visBeskedhistorik();
    void tilfoejBesked(Besked besked) throws BeskedErNullException;
}
