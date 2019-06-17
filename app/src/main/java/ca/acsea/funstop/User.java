package ca.acsea.funstop;

public class User {
    public String email;
    public String token;
    public boolean agreeToEmail;
    public boolean agreeToProgramNotification;
    public boolean agreeToJoinTheBigPrize;
    public String choiceOfCity;
    public User(){

    }
    public User(String email, String token){
        this.email = email;
        this.token = token;
    }
    public User(String email, String token, boolean agreeToEmail, boolean agreeToProgramNotification,
                boolean agreeToJoinTheBigPrize,  String choiceOfCity){
        this.email = email;
        this.token = token;
        this.agreeToEmail = agreeToEmail;
        this.agreeToProgramNotification = agreeToProgramNotification;
        this.agreeToJoinTheBigPrize = agreeToJoinTheBigPrize;
        this.choiceOfCity = choiceOfCity;
    }
}
