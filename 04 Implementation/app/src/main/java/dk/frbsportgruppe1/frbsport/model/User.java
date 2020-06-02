package dk.frbsportgruppe1.frbsport.model;

public class User implements UserInterface{
    private String id;
    private String name;
    private String email;

    public User(String id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void setId(String id){
        this.id=id;
    }

    public String getId(){
        return id;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return  email;
    }
}