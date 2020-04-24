package studios.luxurious.igovern.utils;

public class Post {


    private String id, content, county,device,status, title,type;

    public Post(String id, String content, String county, String device, String status, String title, String type) {
        this.id = id;
        this.content = content;
        this.county = county;
        this.device = device;
        this.status = status;
        this.title = title;
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
}
