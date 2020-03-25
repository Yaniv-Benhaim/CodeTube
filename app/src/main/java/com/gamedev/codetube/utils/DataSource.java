package com.gamedev.codetube.utils;

import com.gamedev.codetube.R;
import com.gamedev.codetube.models.Course;

import java.util.ArrayList;
import java.util.List;

public class DataSource {


    public static List<Course> getAllCourses(){
        List<Course> androidCourses = new ArrayList<>();
        androidCourses.add(new Course("Learn JavaScript in 3 hours - From beginner to advanced", R.drawable.javascript1,R.drawable.androidcourse2,"PkZNo7MFNFg"));
        androidCourses.add(new Course("Kotlin The Future - Complete Kotlin tutorial",R.drawable.kotlin4,R.drawable.androidcourse2,"F9UC9DY-vIU"));
        androidCourses.add(new Course("Java for Beginners 2020 - Java Beginner Course",R.drawable.ai3,R.drawable.androidcourse2,"eIrMbAQSU34&t"));
        androidCourses.add(new Course("Android Studio in 9 hours - From Beginner to Advanced",R.drawable.androidcourse2,R.drawable.androidcourse2,"aS__9RbCyHg"));
        androidCourses.add(new Course("RecyclerView Android Studio - How to use the RecyclerView",R.drawable.javascript1,R.drawable.androidcourse2,"Vyqz_-sJGFk"));

        return androidCourses;
    }

    public static List<Course> getPopularCourses(){
        List<Course> popularCourses = new ArrayList<>();
        popularCourses.add(new Course("Learn JavaScript in 3 hours", R.drawable.javascript1,R.drawable.androidcourse2,"PkZNo7MFNFg"));
        popularCourses.add(new Course("Kotlin The Future",R.drawable.kotlin4,R.drawable.androidcourse2,"F9UC9DY-vIU"));
        popularCourses.add(new Course("Java for Beginners 2020",R.drawable.ai3,R.drawable.androidcourse2,"eIrMbAQSU34&t=5707s"));
        popularCourses.add(new Course("Android Studio in 9 hours",R.drawable.androidcourse2,R.drawable.androidcourse2,"aS__9RbCyHg"));
        popularCourses.add(new Course("RecyclerView Android Studio",R.drawable.javascript1,R.drawable.androidcourse2,"Vyqz_-sJGFk"));

        return popularCourses;
    }
}
