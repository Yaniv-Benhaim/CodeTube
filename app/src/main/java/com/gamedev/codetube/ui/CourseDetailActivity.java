package com.gamedev.codetube.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gamedev.codetube.R;
import com.gamedev.codetube.adapters.RelatedVideoAdapter;
import com.gamedev.codetube.models.RelatedVideo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailActivity extends AppCompatActivity {

     ImageView CourseThumb, CourseCoverImg;
     TextView tv_title,tv_description;
     private FloatingActionButton play_fab;
     private RecyclerView rv_related_videos_id;
     private RelatedVideoAdapter relatedVideoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        //ini Vies
        iniViews();

        //Setup related video rv
        setupRvRelatedVideos();

        play_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseDetailActivity.this,MovieFireBase.class);
                startActivity(intent);
            }
        });



    }

    void iniViews(){
        rv_related_videos_id = findViewById(R.id.rv_related_videos);
        play_fab = findViewById(R.id.play_fab);
        String movie = getIntent().getExtras().getString("title");
        int imageResource = getIntent().getExtras().getInt("imgURL");
        int imageCover = getIntent().getExtras().getInt("imgCover");
        CourseThumb = findViewById(R.id.detail_course_img);
        Glide.with(this).load(imageResource).into(CourseThumb);
        CourseThumb.setImageResource(imageResource);
        tv_title = findViewById(R.id.detail_course_title);
        tv_title.setText(movie);
        getSupportActionBar().setTitle(movie);
        tv_description = findViewById(R.id.detail_course_description);
        CourseCoverImg = findViewById(R.id.detail_course_cover);
        Glide.with(this).load(imageCover).into(CourseCoverImg);
        //set animations
        CourseCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
    }
    void setupRvRelatedVideos(){
        List<RelatedVideo> related_videos_list = new ArrayList<>();
        related_videos_list.add(new RelatedVideo("Java part 2", R.drawable.javascript1));
        related_videos_list.add(new RelatedVideo("Java part 3", R.drawable.javascript1));
        related_videos_list.add(new RelatedVideo("Java part 4", R.drawable.javascript1));
        related_videos_list.add(new RelatedVideo("Java part 5", R.drawable.javascript1));
        related_videos_list.add(new RelatedVideo("Java part 6", R.drawable.javascript1));
        related_videos_list.add(new RelatedVideo("Java part 7", R.drawable.javascript1));

        relatedVideoAdapter = new RelatedVideoAdapter(this,related_videos_list);
        rv_related_videos_id.setAdapter(relatedVideoAdapter);
        rv_related_videos_id.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }
}
