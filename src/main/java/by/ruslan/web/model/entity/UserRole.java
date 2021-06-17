package by.ruslan.web.model.entity;

public enum UserRole {
    GUEST("GUEST"),
    CLIENT("CLIENT"),
    ADMIN("ADMIN"),
    BOOKMAKER("BOOKMAKER");

    private String value;

    UserRole(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
