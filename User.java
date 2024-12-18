package SaarAviviOfekKanariRonAfriat;

public abstract class User {
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getUserName() {
        return name;
    }


}
