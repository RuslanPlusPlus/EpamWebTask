package by.ruslan.web.model.entity;

public class User {
    private long userId;
    private String email;
    private String userName;
    private String enPassword;

    public User(){
    }

    public User(long userId, String email, String userName){
        this.userId = userId;
        this.email = email;
        this.userName = userName;
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

    public String getEnPassword() {
        return enPassword;
    }

    public void setEnPassword(String enPassword) {
        this.enPassword = enPassword;
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
