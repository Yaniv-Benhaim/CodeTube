package com.gamedev.codetube.utils;

import com.gamedev.codetube.R;
import com.gamedev.codetube.models.Course;

import java.util.ArrayList;
import java.util.List;

public class DataSource {


public static ArrayList<Course> courses = new ArrayList();

    public static ArrayList getCourses() {
        return courses;
    }
}
