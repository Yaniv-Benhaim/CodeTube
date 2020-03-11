package com.gamedev.codetube;

public class AndroidCourse {

    private String title;
    private String description;
    private int thumbnail;
    private String creator;
    private String rating;
    private String streamingLink;

    public AndroidCourse(String title, String description, int thumbnail, String creator, String rating, String streamingLink) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.creator = creator;
        this.rating = rating;
        this.streamingLink = streamingLink;
    }

    public AndroidCourse(String title, int thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getCreator() {
        return creator;
    }

    public String getRating() {
        return rating;
    }

    public String getStreamingLink() {
        return streamingLink;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setStreamingLink(String streamingLink) {
        this.streamingLink = streamingLink;
    }
}
