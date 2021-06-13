package by.ruslan.web.model.entity;

import java.math.BigDecimal;
import java.util.List;

public class User {
    private long userId;
    private String email;
    private String userName;
    private String encodedPassword; //remove?
    private UserRole role;
    private BigDecimal balance;
    private List<Bet> activeBets; //many to one
    private List<Bet> completedBets; //many to one

    public User(){
    }

    public User(long userId, String email, String userName, UserRole role){
        this.userId = userId;
        this.email = email;
        this.userName = userName;
        this.role = role;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public long getUserId() {
        return userId;
    }

    public String getUserName() {

        return userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public List<Bet> getActiveBets() {
        return activeBets;
    }

    public void setActiveBets(List<Bet> activeBets) {
        this.activeBets = activeBets;
    }

    public List<Bet> getCompletedBets() {
        return completedBets;
    }

    public void setCompletedBets(List<Bet> completedBets) {
        this.completedBets = completedBets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                email.equals(user.email) &&
                userName.equals(user.userName);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (int) (result * prime + userId);
        result = result * prime + (userName != null? userName.hashCode(): 0);
        result = result * prime + (email != null? email.hashCode(): 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User{")
                .append("username=").append(userName)
                .append(", ")
                .append("email=").append(email)
                .append("}");
        return builder.toString();
    }
}
