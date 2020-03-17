package com.gamedev.codetube;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class CourseDetailActivity extends AppCompatActivity {

    private ImageView CourseThumb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        String movie = getIntent().getExtras().getString("title");
        int imageResource = getIntent().getExtras().getInt("imgURL");

        CourseThumb = findViewById(R.id.detail_course_img);
        CourseThumb.setImageResource(imageResource);


    }
}
