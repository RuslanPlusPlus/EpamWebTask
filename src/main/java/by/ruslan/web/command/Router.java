package by.ruslan.web.command;

public class Router {
    public enum Type{
        FORWARD,
        REDIRECT;
    }

    private String path;
    private Type type;

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
