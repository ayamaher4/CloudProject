package model;

public class Profile {
    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;


    public static final String TABLE_NAME = "profile";

    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password";
    public static final String COL_PHONE = "phone";


    public static final String CREATE_TABLE = "create table " + TABLE_NAME +
            "(" + COL_ID + " integer primary key autoincrement," + COL_NAME +
            " text," + COL_EMAIL + " text," + COL_PASSWORD + " text," + COL_PHONE + " text," ;

    public static final String DROP_TABLE = "drop table if exists " + TABLE_NAME;

    public Profile(int id, String name, String email , String password , String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;


    }

    public Profile() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }




    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
