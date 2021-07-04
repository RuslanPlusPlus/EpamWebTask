package by.ruslan.web.command;

/**
 * The {@code Router} class represents page router.
 *
 * @author Ruslan Nedvedskiy
 */

public class Router {

    /**
     * The {@code Type} enum represents page redirect type.
     *
     * @author Ruslan Nedvedskiy
     */

    public enum Type{
        FORWARD,
        REDIRECT;
    }

    private String path;
    private Type type = Type.FORWARD;

    public Router(){}

    public Router(String path){
        this.path = path;
    }

    public Router(String path, Type type){
        this.path = path;
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public Type getType() {
        return type;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
