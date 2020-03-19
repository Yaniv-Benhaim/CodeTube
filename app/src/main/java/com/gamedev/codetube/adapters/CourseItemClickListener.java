package com.gamedev.codetube.adapters;

import android.widget.ImageView;

import com.gamedev.codetube.models.Course;

public interface CourseItemClickListener {
    void onCourseClick(Course course, ImageView courseImageView);
}
