package by.ruslan.web.model.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code User} class describes the User entity
 *
 * @author Ruslan Nedvedskiy
 */
public class User {
    private long userId;
    private String email;
    private String username;
    private String encodedPassword; //remove?
    private UserRole role;
    private BigDecimal balance;
    private List<Bet> activeBets;
    private List<Bet> completedBets;

    public User(){
    }

    public User(long userId, String email, String username, UserRole role){
        this.userId = userId;
        this.email = email;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
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
                username.equals(user.username);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (int) (result * prime + userId);
        result = result * prime + (username != null? username.hashCode(): 0);
        result = result * prime + (email != null? email.hashCode(): 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User{")
                .append("username=").append(username)
                .append(", ")
                .append("userId=").append(userId)
                .append(", ")
                .append("email=").append(email)
                .append(", ")
                .append("role=").append(role)
                .append(", ")
                .append("balance=").append(balance)
                .append("Active bets=").append(activeBets)
                .append("Completed bets bets=").append(completedBets)
                .append("}");
        return builder.toString();
    }
}
