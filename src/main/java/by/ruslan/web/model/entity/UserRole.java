package by.ruslan.web.model.entity;

/**
 * The {@code UserRole} enum describes possible user roles
 *
 * @author Ruslan Nedvedskiy
 */
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
