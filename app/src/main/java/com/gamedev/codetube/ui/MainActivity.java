package com.gamedev.codetube.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.gamedev.codetube.adapters.CourseFireBaseAdapter;
import com.gamedev.codetube.adapters.NoteAdapter;
import com.gamedev.codetube.currentuser.User;
import com.gamedev.codetube.fragments.FavouriteFragment;
import com.gamedev.codetube.fragments.HomeFragment;
import com.gamedev.codetube.fragments.MyAccountFragment;
import com.gamedev.codetube.fragments.MyCourses;
import com.gamedev.codetube.fragments.SearchFragment;
import com.gamedev.codetube.models.Course;
import com.gamedev.codetube.adapters.CourseAdapter;
import com.gamedev.codetube.adapters.CourseItemClickListener;
import com.gamedev.codetube.R;
import com.gamedev.codetube.models.Note;
import com.gamedev.codetube.models.Slide;
import com.gamedev.codetube.adapters.SliderAdapter;
import com.gamedev.codetube.utils.DataSource;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements CourseItemClickListener {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference courseRef = db.collection("Courses");
    private CourseFireBaseAdapter adapter;

    private List<Slide> slides_array;
    private ViewPager sliderPager;
    private TabLayout indicator;
    private RecyclerView mAndroidCoursesRV, mPopularCoursesRV;
    private GoogleSignInClient mGoogleSignInClient;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_featured:
                            mainVisible();
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_search:
                            mainInvisible();
                            selectedFragment = new SearchFragment();

                            break;
                        case R.id.nav_my_courses:
                            mainInvisible();
                            selectedFragment = new MyCourses();

                            break;
                        case R.id.nav_favourite:
                            mainInvisible();
                            selectedFragment = new FavouriteFragment();

                            break;
                        case R.id.nav_account:
                            mainInvisible();
                            selectedFragment = new MyAccountFragment();

                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();



                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*Toolbar toolbar = findViewById(R.id.mCustomToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("  Code Tube");
        getSupportActionBar().setIcon(getDrawable(R.drawable.actionbarlogo));
         */

        LinearLayout bannerMain = findViewById(R.id.banner_main);
        bannerMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AdminActivity.class);
                startActivity(intent);
            }
        });







        //Setting the array with images and titles
        setSlides_array();
        //Setting the slider
        setSliderPager();
        //Setting the indicator below the slider
        setIndicator();
        //Automate movement between slides using Timer
        setTimer();
        // Set Horizontal RecyclerView for the first row of android courses
        setAndroid_RecyclerView();
        //iniPopularCourses();
        iniPopularCourses();
        //setup firestore recyclerview
        setupRecyclerView();



        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
       if (savedInstanceState == null)
       {
           getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
       }





    }

    @Override
    public void onCourseClick(Course course, ImageView courseImageView) {
        //sending course information to detail activity
        Intent intent = new Intent(MainActivity.this, CourseDetailActivity.class);
        intent.putExtra("title",course.getTitle());
        intent.putExtra("imgURL",course.getThumbnail());
        intent.putExtra("imgCover",course.getCoverPhoto());
        intent.putExtra("link",course.getStreamingLink());

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



    public void mainInvisible(){
        ScrollView mainView = findViewById(R.id.completescrollview);
        mainView.setVisibility(View.INVISIBLE);
    }

    public void mainVisible(){
        ScrollView mainView = findViewById(R.id.completescrollview);
        mainView.setVisibility(View.VISIBLE);
    }

    public void setSlides_array(){
        slides_array = new ArrayList<>();

        slides_array.add(new Slide(R.drawable.first_slide,"Full Android Studio Course - From Beginner to Advanced"));
        slides_array.add(new Slide(R.drawable.second_slide,"Machine Learning For Beginners!"));
        slides_array.add(new Slide(R.drawable.third_slide,"Learn Python in 3 Hours!"));
        slides_array.add(new Slide(R.drawable.fourth_slide,"Learn Java in 9 Hours - From Beginner to Advanced"));
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
        CourseAdapter courseAdapter = new CourseAdapter(this, DataSource.getAllCourses(),this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout_menu:{
                mGoogleSignInClient.signOut();
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView() {
        Query query = courseRef.orderBy("views", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Course> options = new FirestoreRecyclerOptions.Builder<Course>()
                .setQuery(query, Course.class)
                .build();


        adapter = new CourseFireBaseAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.rv_firebase_courses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new CourseFireBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Course course = documentSnapshot.toObject(Course.class);

                Intent intent = new Intent(MainActivity.this, CourseDetailActivity.class);
                intent.putExtra("title",course.getTitle());
                intent.putExtra("imgURL",course.getThumbnail());
                intent.putExtra("imgCover",course.getCoverPhoto());
                intent.putExtra("link",course.getStreamingLink());
                intent.putExtra("description",course.getDescription());


                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
