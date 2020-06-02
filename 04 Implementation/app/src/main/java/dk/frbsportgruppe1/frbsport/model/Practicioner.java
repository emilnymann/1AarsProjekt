package dk.frbsportgruppe1.frbsport.model;

public class Practicioner extends User implements PracticionerInterface{
    public Practicioner(String id, String name, String email) {
        super(id, name, email);
    }
}