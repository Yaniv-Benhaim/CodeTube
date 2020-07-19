package com.gamedev.codetube.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gamedev.codetube.R;
import com.gamedev.codetube.models.Course;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> implements Filterable {

    Context context;
    List<Course> Courses;
    List<Course> CoursesFull;
    CourseItemClickListener courseItemClickListener;
     Filter courseFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List <Course> filteredList = new ArrayList<>();
            String filterPattern = constraint.toString().toLowerCase().trim();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(CoursesFull);
            }else{


                for(Course course : CoursesFull){
                    if(course.getTitle().toLowerCase().contains(filterPattern)||course.getDescription().toLowerCase().contains(filterPattern)){
                       filteredList.add(course);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            Courses.clear();
            Courses.addAll((Collection<? extends Course>) results.values);
            notifyDataSetChanged();
        }
    };

    public CourseAdapter( List<Course> Courses) {
        this.context = context;
        this.Courses = Courses;
        CoursesFull = new ArrayList<>(Courses);
        //courseItemClickListener = listener;
    }

    public CourseAdapter(ArrayList<Course> courses) {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.course_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.TvTitle.setText(Courses.get(position).getTitle());
        Picasso.get().load(Courses.get(position).getCourse_thumbnail()).into(holder.ImgCourse, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(holder.itemView.getContext(), "Didnt get the image", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return Courses.size();
    }

    @Override
    public Filter getFilter() {
        return courseFilter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView TvTitle;
        private ImageView ImgCourse;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            TvTitle = itemView.findViewById(R.id.item_course_title);
            ImgCourse = itemView.findViewById(R.id.item_course_img);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Click Working", Toast.LENGTH_SHORT).show();
        }
    }
}
