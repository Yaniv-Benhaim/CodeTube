package com.gamedev.codetube.adapters;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
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
import com.gamedev.codetube.ui.CourseDetailActivity;
import com.gamedev.codetube.ui.MainActivity;
import com.gamedev.codetube.utils.DataSource;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder> implements Filterable {


    private List<Course> allCourses;
    private List<Course> allCoursesFull;
    Filter courseFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Course> filteredList = new ArrayList<>();
            if(constraint.toString().isEmpty()){
                filteredList.addAll(allCoursesFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(Course course : allCoursesFull){
                    if(course.getTitle().toLowerCase().contains(filterPattern)||course.getDescription().toLowerCase().contains(filterPattern)){
                        filteredList.add(course);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            allCourses.clear();
            allCourses.addAll((Collection<? extends Course>) results.values);
            notifyDataSetChanged();
        }
    };

    public SearchHistoryAdapter( List <Course> allCourses) {
        this.allCourses = allCourses;
        this.allCoursesFull = new ArrayList<>(allCourses);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.course_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(allCourses.get(position).getTitle());
        Picasso.get().load(allCourses.get(position).getCourse_thumbnail()).into(holder.cover, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                // Toast.makeText(holder.itemView.getContext(), allCourses.get(position).getCourse_thumbnail(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return allCourses.size();
    }

    @Override
    public Filter getFilter() {
        return courseFilter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cover;
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //setOnclick listener

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), CourseDetailActivity.class);

                    intent.putExtra("title",allCourses.get(getAdapterPosition()).getTitle());
                    intent.putExtra("imgURL",allCourses.get(getAdapterPosition()).getCourse_thumbnail());
                    intent.putExtra("imgCover",allCourses.get(getAdapterPosition()).getCourse_thumbnail());
                    intent.putExtra("link",allCourses.get(getAdapterPosition()).getStreamingLink());
                    v.getContext().startActivity(intent);


                }
            });
            title = itemView.findViewById(R.id.item_course_title);
            cover = itemView.findViewById(R.id.item_course_img);
        }
    }


}
