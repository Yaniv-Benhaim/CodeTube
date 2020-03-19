package com.gamedev.codetube.utils;

import com.gamedev.codetube.R;
import com.gamedev.codetube.models.Course;

import java.util.ArrayList;
import java.util.List;

public class DataSource {


    public static List<Course> getAndroidCourses(){
        List<Course> androidCourses = new ArrayList<>();
        androidCourses.add(new Course("Javascript 2020", R.drawable.javascript1,R.drawable.androidcourse2));
        androidCourses.add(new Course("Kotlin The Future",R.drawable.kotlin4,R.drawable.androidcourse2));
        androidCourses.add(new Course("Artificial Intelligence",R.drawable.ai3,R.drawable.androidcourse2));
        androidCourses.add(new Course("Android Part 1",R.drawable.androidcourse2,R.drawable.androidcourse2));
        androidCourses.add(new Course("Java 2020 part 1",R.drawable.javascript1,R.drawable.androidcourse2));

        return androidCourses;
    }

    public static List<Course> getPopularCourses(){
        List<Course> popularCourses = new ArrayList<>();
        popularCourses.add(new Course("Javascript 2020", R.drawable.javascript1,R.drawable.androidcourse2));
        popularCourses.add(new Course("Kotlin The Future",R.drawable.kotlin4,R.drawable.androidcourse2));
        popularCourses.add(new Course("Artificial Intelligence",R.drawable.ai3,R.drawable.androidcourse2));
        popularCourses.add(new Course("Android Part 1",R.drawable.androidcourse2,R.drawable.androidcourse2));
        popularCourses.add(new Course("Java 2020 part 1",R.drawable.javascript1,R.drawable.androidcourse2));

        return popularCourses;
    }
}
