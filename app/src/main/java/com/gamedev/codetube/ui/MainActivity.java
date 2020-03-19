package com.gamedev.codetube.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.gamedev.codetube.models.Course;
import com.gamedev.codetube.adapters.CourseAdapter;
import com.gamedev.codetube.adapters.CourseItemClickListener;
import com.gamedev.codetube.R;
import com.gamedev.codetube.models.Slide;
import com.gamedev.codetube.adapters.SliderAdapter;
import com.gamedev.codetube.utils.DataSource;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements CourseItemClickListener {

    private List<Slide> slides_array;
    private ViewPager sliderPager;
    private TabLayout indicator;
    private RecyclerView mAndroidCoursesRV, mPopularCoursesRV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting the array with images and titles
        setSlides_array();
        //Setting the slider
        setSliderPager();
        //Setting the indicator below the slider
        setIndicator();
        //Automate movement between slides using Timer
        setTimer();
        //Add Example android courses to horizontal Recycler View

        // Set Horizontal RecyclerView for the first row of android courses
        setAndroid_RecyclerView();
        iniPopularCourses();

    }



    @Override
    public void onCourseClick(Course course, ImageView courseImageView) {
        //sending course information to detail activity
        Intent intent = new Intent(MainActivity.this, CourseDetailActivity.class);
        intent.putExtra("title",course.getTitle());
        intent.putExtra("imgURL",course.getThumbnail());
        intent.putExtra("imgCover",course.getCoverPhoto());

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                courseImageView,"sharedName");
        startActivity(intent,options.toBundle());

    }

    class SliderTimer extends TimerTask {
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(sliderPager.getCurrentItem()<slides_array.size() - 1){
                        sliderPager.setCurrentItem(sliderPager.getCurrentItem()+1);
                    }else{
                        sliderPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    public void setSlides_array(){
        slides_array = new ArrayList<>();

        slides_array.add(new Slide(R.drawable.slide1,"Full Android Studio Course"));
        slides_array.add(new Slide(R.drawable.slide2,"Full Java Course, Watch Now!"));
        slides_array.add(new Slide(R.drawable.slide3,"Full Python Course, Watch Now!"));
        slides_array.add(new Slide(R.drawable.slide4,"Full Android Studio Course"));
    }

    public void setSliderPager(){
        sliderPager = findViewById(R.id.slider_pager);
        SliderAdapter adapter = new SliderAdapter(this,slides_array);
        sliderPager.setAdapter(adapter);
    }

    public void setIndicator(){
        indicator = findViewById(R.id.indicator);
        indicator.setupWithViewPager(sliderPager,true);
    }

    public void setTimer(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MainActivity.SliderTimer(), 4000, 6000);
    }


    public void setAndroid_RecyclerView(){
        mAndroidCoursesRV = findViewById(R.id.rv_android_courses);
        CourseAdapter courseAdapter = new CourseAdapter(this, DataSource.getAndroidCourses(),this);
        mAndroidCoursesRV.setAdapter(courseAdapter);
        mAndroidCoursesRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

    }
    public void iniPopularCourses(){
        //setPopularCourses rv
        mPopularCoursesRV = findViewById(R.id.rv_popular_courses);
        CourseAdapter popularCoursesAdapter = new CourseAdapter(this,DataSource.getPopularCourses(), this);
        mPopularCoursesRV.setAdapter(popularCoursesAdapter);
        mPopularCoursesRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

    }
}
