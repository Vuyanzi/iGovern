package studios.luxurious.igovern.utils;

public class Post {

    private String title, location, message;
    private int type, status;

    public Post(String title, String location, String message, int type, int status) {
        this.title = title;
        this.location = location;
        this.message = message;
        this.type = type;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getMessage() {
        return message;
    }

    public int getType() {
        return type;
    }

    public int getStatus() {
        return status;
    }
}
