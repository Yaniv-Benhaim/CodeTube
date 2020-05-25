package com.gamedev.codetube.models;

public class Course {

    private String title;
    private String description;
    private int thumbnail;
    private String creator;
    private String rating;
    private String streamingLink;
    private int coverPhoto;
    private String TAGS;
    private int views;
    private String course_thumbnail;

    public String getCourse_thumbnail() {
        return course_thumbnail;
    }

    public void setCourse_thumbnail(String course_thumbnail) {
        this.course_thumbnail = course_thumbnail;
    }

    public Course(String title, String course_thumbnail) {
        this.title = title;
        this.course_thumbnail = course_thumbnail;
    }

    public Course(){

    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getTAGS() {
        return TAGS;
    }

    public void setTAGS(String TAGS) {
        this.TAGS = TAGS;
    }


    public Course(String title, int thumbnail, int coverPhoto, String streamingLink, String TAGS) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.coverPhoto = coverPhoto;
        this.streamingLink = streamingLink;
        this.TAGS = TAGS;

    }

    public Course(String title, String description, int thumbnail, String creator, String rating, String streamingLink) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.creator = creator;
        this.rating = rating;
        this.streamingLink = streamingLink;
    }

    public Course(String title, int thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public int getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(int coverPhoto) {
        this.coverPhoto = coverPhoto;
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
