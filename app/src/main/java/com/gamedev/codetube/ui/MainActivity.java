package com.gamedev.codetube.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.gamedev.codetube.adapters.CourseFireBaseAdapter;
import com.gamedev.codetube.adapters.NoteAdapter;
import com.gamedev.codetube.adapters.SearchHistoryAdapter;
import com.gamedev.codetube.adapters.SearchRecyclerAdapter;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements CourseItemClickListener {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference courseRef = db.collection("Courses");
    private CourseFireBaseAdapter adapter;

    public Button android,swift,java,cSharp,cPlus,design,flutter,unity,unreal,html;
    private RecyclerView personalRecyclerView;
    private RecyclerView.Adapter personalAdapter;

    private List<Slide> slides_array;
    private ViewPager sliderPager;
    private TabLayout indicator;
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


        LinearLayout bannerMain = findViewById(R.id.banner_main);
        bannerMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GoPremiumActivity.class);
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
        //setup Personalized Recyclerview
        setCustomRecyclerView();
        //setup firestore recyclerview
        setupRecyclerView();
        //Hide actionbar
        getSupportActionBar().hide();
        //set Category Search Buttons
        SetCategorySearch();



        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
       if (savedInstanceState == null)
       {
           getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
       }





    }

    private void setCustomRecyclerView() {
        RecyclerView mRecyclerView;
        personalAdapter = new SearchHistoryAdapter(DataSource.getCourses());
        mRecyclerView = findViewById(R.id.rv_personal_courses);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mRecyclerView.setAdapter(personalAdapter);
    }




    private void SetCategorySearch() {

        android = findViewById(R.id.android);
        swift = findViewById(R.id.swift);
        cSharp = findViewById(R.id.c_sharp);
        cPlus = findViewById(R.id.c_plus);
        design = findViewById(R.id.design);
        java = findViewById(R.id.java);
        unity = findViewById(R.id.unity);
        unreal = findViewById(R.id.unreal);
        html = findViewById(R.id.html);
        flutter = findViewById(R.id.flutter);

        android.setOnClickListener(new CateroryBtn());
        swift.setOnClickListener(new CateroryBtn());
        cSharp.setOnClickListener(new CateroryBtn());
        cPlus.setOnClickListener(new CateroryBtn());
        design.setOnClickListener(new CateroryBtn());
        java.setOnClickListener(new CateroryBtn());
        unity.setOnClickListener(new CateroryBtn());
        unreal.setOnClickListener(new CateroryBtn());
        html.setOnClickListener(new CateroryBtn());
        flutter.setOnClickListener(new CateroryBtn());


    }

    private void setupRecyclerView() {


        Query query = courseRef.orderBy("isFirst", Query.Direction.DESCENDING);

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
                intent.putExtra("imgURL",course.getCourse_thumbnail());
                intent.putExtra("imgCover",course.getCourse_thumbnail());
                intent.putExtra("link",course.getStreamingLink());
                intent.putExtra("description",course.getDescription());
                intent.putExtra("courseFbCover",course.getCourse_thumbnail());


                startActivity(intent);

            }
        });
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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    public class CateroryBtn implements View.OnClickListener{



        @Override
        public void onClick(View v) {
           switch(v.getId()){
               case R.id.android:
                   Intent android = new Intent(MainActivity.this,SearchActivity.class);
                   android.putExtra("searchTerm","android");
                   startActivity(android);
                   finish();
                   break;
               case R.id.swift:
                   Intent swift = new Intent(MainActivity.this,SearchActivity.class);
                   swift.putExtra("searchTerm","swift");
                   startActivity(swift);
                   finish();
                   break;
               case R.id.c_sharp:
                   Intent c_sharp = new Intent(MainActivity.this,SearchActivity.class);
                   c_sharp.putExtra("searchTerm","csharp");
                   startActivity(c_sharp);
                   finish();
                   break;
               case R.id.c_plus:
                   Intent c_plus = new Intent(MainActivity.this,SearchActivity.class);
                   c_plus.putExtra("searchTerm","c++");
                   Toast.makeText(MainActivity.this, "c++", Toast.LENGTH_SHORT).show();
                  // startActivity(c_plus);
                   //finish();
                   break;
               case R.id.java:
                   Intent java = new Intent(MainActivity.this,SearchActivity.class);
                   java.putExtra("searchTerm","java");
                   startActivity(java);
                   finish();
                   break;
               case R.id.flutter:
                   Intent flutter = new Intent(MainActivity.this,SearchActivity.class);
                   flutter.putExtra("searchTerm","flutter");
                   startActivity(flutter);
                   finish();
                   break;
               case R.id.html:
                   Intent html = new Intent(MainActivity.this,SearchActivity.class);
                   html.putExtra("searchTerm","html");
                   startActivity(html);
                   finish();
                   break;
               case R.id.unreal:
                   Intent unreal = new Intent(MainActivity.this,SearchActivity.class);
                   unreal.putExtra("searchTerm","unreal");
                   startActivity(unreal);
                   finish();
                   break;
               case R.id.unity:
                   Intent unity = new Intent(MainActivity.this,SearchActivity.class);
                   unity.putExtra("searchTerm","unity");
                   startActivity(unity);
                   Toast.makeText(MainActivity.this, "unity", Toast.LENGTH_SHORT).show();
                   finish();
                   break;
               case R.id.design:
                   Intent design = new Intent(MainActivity.this,SearchActivity.class);
                   design.putExtra("searchTerm","design");
                   startActivity(design);
                   finish();
                   break;

           }
        }
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
