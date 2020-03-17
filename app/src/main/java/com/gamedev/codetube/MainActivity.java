package com.gamedev.codetube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements CourseItemClickListener {

    private List<Slide> slides_array;
    private ViewPager sliderPager;
    private TabLayout indicator;
    private RecyclerView mAndroidCoursesRV;


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
        addExampleCourses();
        // Set Horizontal RecyclerView for the first row of android courses
        setAndroid_RecyclerView();

    }

    @Override
    public void onCourseClick(AndroidCourse course, ImageView courseImageView) {
        //sending course information to detail activity
        Intent intent = new Intent(MainActivity.this, CourseDetailActivity.class);
        intent.putExtra("title",course.getTitle());
        intent.putExtra("imgURL",course.getThumbnail());

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

    public void addExampleCourses(){
        DataManager.androidCourses.add(new AndroidCourse("Javascript 2020",R.drawable.javascript1));
        DataManager.androidCourses.add(new AndroidCourse("Kotlin The Future",R.drawable.kotlin4));
        DataManager.androidCourses.add(new AndroidCourse("Artificial Intelligence",R.drawable.ai3));
        DataManager.androidCourses.add(new AndroidCourse("Android Part 1",R.drawable.androidcourse2));
        DataManager.androidCourses.add(new AndroidCourse("Java 2020 part 1",R.drawable.javascript1));
    }

    public void setAndroid_RecyclerView(){
        mAndroidCoursesRV = findViewById(R.id.rv_android_courses);
        CourseAdapter courseAdapter = new CourseAdapter(this,DataManager.androidCourses,this);
        mAndroidCoursesRV.setAdapter(courseAdapter);
        mAndroidCoursesRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }
}
