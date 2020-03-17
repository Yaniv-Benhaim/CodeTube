package com.gamedev.codetube.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gamedev.codetube.R;

import org.w3c.dom.Text;

public class CourseDetailActivity extends AppCompatActivity {

     ImageView CourseThumb, CourseCoverImg;
     TextView tv_title,tv_description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        //ini Vies
        iniViews();



    }

    void iniViews(){
        String movie = getIntent().getExtras().getString("title");
        int imageResource = getIntent().getExtras().getInt("imgURL");
        int imageCover = getIntent().getExtras().getInt("imgCover");
        CourseThumb = findViewById(R.id.detail_course_img);
        Glide.with(this).load(imageResource).into(CourseThumb);
        CourseThumb.setImageResource(imageResource);
        tv_title = findViewById(R.id.detail_course_title);
        tv_description = findViewById(R.id.detail_course_description);
        CourseCoverImg = findViewById(R.id.detail_course_cover);
        Glide.with(this).load(imageCover).into(CourseCoverImg);
    }
}
