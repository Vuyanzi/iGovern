package studios.luxurious.igovern.utils;

public class Post {


    private String id, content, county,device,status, title,type,username,inserted_at;

    public Post(String id, String content, String county, String device, String status, String title, String type, String username, String inserted_at) {
        this.id = id;
        this.content = content;
        this.county = county;
        this.device = device;
        this.status = status;
        this.title = title;
        this.inserted_at = inserted_at;
        this.username = username;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getCounty() {
        return county;
    }

    public String getDevice() {
        return device;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getInserted_at() {
        return inserted_at;
    }
}
