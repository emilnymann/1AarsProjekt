package dk.frbsportgruppe1.frbsport.model;

public class User implements UserInterface{
    private int id;
    private String name;
    private String username;

    public User(String name, String username){
        this.name = name;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}