package com.gamedev.codetube.datamanager;

import com.gamedev.codetube.models.AndroidCourse;

import java.util.ArrayList;

public class DataManager {

    public DataManager() {
    }

    public static ArrayList<AndroidCourse> androidCourses = new ArrayList<>();

    public static ArrayList<AndroidCourse> getAndroidCourses() {
        return androidCourses;
    }
}
