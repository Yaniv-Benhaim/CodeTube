package com.gamedev.codetube.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.gamedev.codetube.R;
import com.gamedev.codetube.models.Course;
import com.gamedev.codetube.models.RelatedVideo;
import com.gamedev.codetube.ui.CourseDetailActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RelatedVideoAdapter extends RecyclerView.Adapter<RelatedVideoAdapter.RelatedVideoHolder> {

    Context mContext;
    List<Course> mRelatedVideos;



    public RelatedVideoAdapter(Context mContext, List<Course> mRelatedVideos) {
        this.mContext = mContext;
        this.mRelatedVideos = mRelatedVideos;
    }


    @NonNull
    @Override
    public RelatedVideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.course_item, parent,false);

        return new RelatedVideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedVideoHolder holder, int position) {
        holder.title.setText(mRelatedVideos.get(position).getTitle());
        Picasso.get().load(mRelatedVideos.get(position).getCourse_thumbnail()).into(holder.cover, new Callback() {
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
        return mRelatedVideos.size();
    }

    public class RelatedVideoHolder extends RecyclerView.ViewHolder{

        ImageView cover;
        TextView title;

        public RelatedVideoHolder(@NonNull View itemView) {
            super(itemView);

            //setOnclick listener

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), CourseDetailActivity.class);

                    intent.putExtra("title",mRelatedVideos.get(getAdapterPosition()).getTitle());
                    intent.putExtra("imgURL",mRelatedVideos.get(getAdapterPosition()).getCourse_thumbnail());
                    intent.putExtra("imgCover",mRelatedVideos.get(getAdapterPosition()).getCourse_thumbnail());
                    intent.putExtra("link",mRelatedVideos.get(getAdapterPosition()).getStreamingLink());
                    v.getContext().startActivity(intent);


                }
            });

            title = itemView.findViewById(R.id.item_course_title);
            cover = itemView.findViewById(R.id.item_course_img);

        }
    }
}
