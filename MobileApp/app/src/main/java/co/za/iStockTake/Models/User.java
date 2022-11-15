package co.za.iStockTake.Models;

public class User
{
    private String password;
    private String username;
    private String usertype;
    private String userEmail;
    private String name;
    private String location;

    public User(String password, String username, String name)
    {
        this.password = password;
        this.username = username;
        this.name = name;
    }

    public User(String password, String username, String usertype, String userEmail, String name, String location)
    {
        this.password = password;
        this.username = username;
        this.usertype = usertype;
        this.userEmail = userEmail;
        this.name = name;
        this.location = location;
    }

    //****************************************Setters
    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setUsertype(String usertype)
    {
        this.usertype = usertype;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    //********************************************Getters
    public String getUsertype()
    {
        return usertype;
    }

    public String getUsername()
    {
        return username;
    }

    public String getLocation()
    {
        return location;
    }
}
