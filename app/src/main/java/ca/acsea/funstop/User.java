package ca.acsea.funstop;

public class User {
    public String email;
    public String token;
    public boolean agreedToReceiveEmailIsChecked;
    public boolean agreedToProgramNotification;
    public boolean agreedToJoinBigPrizeIsChecked;
    public String city;
    public User(String email, String token, boolean agreedToReceiveEmailIsChecked, boolean agreedToProgramNotification,
                boolean agreedToJoinBigPrizeIsChecked,  String city){
        this.email = email;
        this.token = token;
        this.agreedToReceiveEmailIsChecked = agreedToReceiveEmailIsChecked;
        this.agreedToProgramNotification = agreedToProgramNotification;
        this.agreedToJoinBigPrizeIsChecked = agreedToJoinBigPrizeIsChecked;
        this.city = city;
    }
    public User(String email, String token){
        this.email = email;
        this.token = token;
    }
    public User(String token){
        this.token = token;
    }
}
