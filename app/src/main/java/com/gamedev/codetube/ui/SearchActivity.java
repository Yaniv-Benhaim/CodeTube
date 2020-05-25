package com.gamedev.codetube.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;

import com.gamedev.codetube.R;
import com.gamedev.codetube.adapters.CourseAdapter;
import com.gamedev.codetube.adapters.SearchRecyclerAdapter;
import com.gamedev.codetube.fragments.FavouriteFragment;
import com.gamedev.codetube.fragments.HomeFragment;
import com.gamedev.codetube.fragments.MyAccountFragment;
import com.gamedev.codetube.fragments.MyCourses;
import com.gamedev.codetube.fragments.SearchFragment;
import com.gamedev.codetube.utils.DataSource;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchActivity extends AppCompatActivity {


    private SearchRecyclerAdapter searchRecyclerAdapter;



    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_featured:
                            Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                            startActivity(intent);

                            break;
                        case R.id.nav_search:
                            mainVisible();
                            Intent intent2 = new Intent(SearchActivity.this, SearchActivity.class);
                            startActivity(intent2);
                            //selectedFragment = new SearchFragment();

                            break;
                        case R.id.nav_my_courses:
                            mainInvisible();
                            selectedFragment = new MyCourses();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selectedFragment).commit();
                            break;
                        case R.id.nav_favourite:
                            mainInvisible();
                            selectedFragment = new FavouriteFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selectedFragment).commit();
                            break;
                        case R.id.nav_account:
                            mainInvisible();
                            selectedFragment = new MyAccountFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selectedFragment).commit();

                            break;
                    }




                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        RecyclerView mRecyclerView;

        searchRecyclerAdapter = new SearchRecyclerAdapter(DataSource.getAllCourses());
        mRecyclerView = findViewById(R.id.search_recycler_view_layout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRecyclerView.setAdapter(searchRecyclerAdapter);
        setToolBars();
        //setRecyclerView();

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchRecyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(SearchActivity.this,MainActivity.class);
        startActivity(intent);
        super.onBackPressed();  // optional depending on your needs
    }


    public void mainInvisible(){
        ScrollView mainView = findViewById(R.id.main_search_layout);
        mainView.setVisibility(View.INVISIBLE);
    }
    public void mainVisible(){
       ScrollView mainView = findViewById(R.id.main_search_layout);
        mainView.setVisibility(View.VISIBLE);
    }

    public void setToolBars(){
       // Toolbar toolbar = findViewById(R.id.mCustomToolBarSearch);
       // setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("  Code Tube");
       //getSupportActionBar().setIcon(getDrawable(R.drawable.actionbarlogo));



        //Bottom Navigation
        BottomNavigationView bottomNav;
        bottomNav = findViewById(R.id.bottom_navigation_from_search);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.getMenu().getItem(1).setChecked(true);
    }


}
