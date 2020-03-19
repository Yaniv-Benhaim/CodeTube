package com.gamedev.codetube.datamanager;

import com.gamedev.codetube.models.Course;

import java.util.ArrayList;

public class DataManager {

    public DataManager() {
    }

    public static ArrayList<Course> androidCourses = new ArrayList<>();

    public static ArrayList<Course> getAndroidCourses() {
        return androidCourses;
    }
}
