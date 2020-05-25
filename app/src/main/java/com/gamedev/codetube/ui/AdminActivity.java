package com.gamedev.codetube.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.gamedev.codetube.R;
import com.gamedev.codetube.models.Course;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminActivity extends AppCompatActivity {

    private EditText CourseTitle;
    private EditText ImageLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        iniViews();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.admin_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //for more items in the menu add them to the switch menu
        switch (item.getItemId()) {
            case R.id.action_save:
                saveCourse();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveCourse() {
        String title = CourseTitle.getText().toString();
        String imageLink = ImageLink.getText().toString();
        String description = "description";
        int views = 1000;

        if(title.trim().isEmpty()|| imageLink.trim().isEmpty()){
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        CollectionReference coursesRef = FirebaseFirestore.getInstance()
                .collection("Courses");
        coursesRef.add(new Course(title , imageLink));
        Toast.makeText(this,"Succesfully added new Video",Toast.LENGTH_SHORT).show();
        finish();
    }

    private void iniViews() {
        CourseTitle = findViewById(R.id.new_course_title);
        ImageLink = findViewById(R.id.new_course_image_link);
    }
}
