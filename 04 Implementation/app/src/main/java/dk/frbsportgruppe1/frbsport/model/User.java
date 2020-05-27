package dk.frbsportgruppe1.frbsport.model;

public class User implements UserInterface{
    private int id;
    private String name="";

    public User(String name){
        this.name=name;
    }

    public void setId(int id){
        this.id=id;
    }

    public int getId(){
        return id;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }
}