package com.gamedev.codetube;

import java.util.ArrayList;

public class DataManager {

    public DataManager() {
    }

    static ArrayList<AndroidCourse> androidCourses = new ArrayList<>();

    public static ArrayList<AndroidCourse> getAndroidCourses() {
        return androidCourses;
    }
}
