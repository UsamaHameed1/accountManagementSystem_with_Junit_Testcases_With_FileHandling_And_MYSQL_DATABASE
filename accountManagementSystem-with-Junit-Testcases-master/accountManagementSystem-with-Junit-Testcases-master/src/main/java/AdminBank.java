public class AdminBank {
    private String userName;
    private String password;



    //Constructor
    AdminBank(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }


    public String getPassword() {
        return this.password;
    }

    public String getUserName()
    {
        return this.userName;
    }
}
