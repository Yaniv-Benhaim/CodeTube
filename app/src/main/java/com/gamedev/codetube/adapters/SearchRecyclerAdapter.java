package com.gamedev.codetube.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.gamedev.codetube.R;
import com.gamedev.codetube.models.Course;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder> implements Filterable {


    private List<Course> allCourses;
    private List<Course> allCoursesFull;


    public SearchRecyclerAdapter( List <Course> allCourses) {
        this.allCourses = allCourses;
        this.allCoursesFull = new ArrayList<>(allCourses);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.search_item_for_fragment_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(allCourses.get(position).getTitle());
        holder.cover.setImageResource(allCourses.get(position).getThumbnail());

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
            title = itemView.findViewById(R.id.search_fragment_title);
            cover = itemView.findViewById(R.id.search_fragment_cover_image);
        }
    }

    Filter courseFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Course> filteredList = new ArrayList<>();
            if(constraint.toString().isEmpty()){
                filteredList.addAll(allCoursesFull);
            } else {
                for(Course course : allCoursesFull){
                    if(course.getTAGS().toLowerCase().contains(constraint.toString().toLowerCase())){
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


}
