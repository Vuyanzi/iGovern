package studios.luxurious.igovern.utils;

public class Post {

    private String title, location, message, type, status;

    public Post(String title, String location, String message, String type, String status) {
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

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }
}
