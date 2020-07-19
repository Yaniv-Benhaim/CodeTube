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
    private String isFirst;
    private String tag;





    public Course(){

    }

    public Course(String title, String description, int thumbnail, String creator, String rating, String streamingLink, int coverPhoto, String TAGS, int views, String course_thumbnail, String isFirst, String tag) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.creator = creator;
        this.rating = rating;
        this.streamingLink = streamingLink;
        this.coverPhoto = coverPhoto;
        this.TAGS = TAGS;
        this.views = views;
        this.course_thumbnail = course_thumbnail;
        this.isFirst = isFirst;
        this.tag = tag;
    }

    public Course(String title, String description, int views, String course_thumbnail, String isFirst, String tag) {
        this.title = title;
        this.description = description;
        this.views = views;
        this.course_thumbnail = course_thumbnail;
        this.isFirst = isFirst;
        this.tag = tag;
    }

    public Course(String title, String description, String course_thumbnail, String isFirst, String tag, String streamingLink) {
        this.title = title;
        this.description = description;
        this.course_thumbnail = course_thumbnail;
        this.isFirst = isFirst;
        this.tag = tag;
        this.streamingLink = streamingLink;
    }

    public Course(String title, String course_thumbnail){
        this.title = title;
        this.course_thumbnail = course_thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStreamingLink() {
        return streamingLink;
    }

    public void setStreamingLink(String streamingLink) {
        this.streamingLink = streamingLink;
    }

    public int getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(int coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getTAGS() {
        return TAGS;
    }

    public void setTAGS(String TAGS) {
        this.TAGS = TAGS;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getCourse_thumbnail() {
        return course_thumbnail;
    }

    public void setCourse_thumbnail(String course_thumbnail) {
        this.course_thumbnail = course_thumbnail;
    }

    public String getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(String isFirst) {
        this.isFirst = isFirst;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
