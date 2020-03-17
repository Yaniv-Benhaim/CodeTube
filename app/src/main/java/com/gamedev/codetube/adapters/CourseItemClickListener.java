package com.gamedev.codetube.adapters;

import android.widget.ImageView;

import com.gamedev.codetube.models.AndroidCourse;

public interface CourseItemClickListener {
    void onCourseClick(AndroidCourse course, ImageView courseImageView);
}
