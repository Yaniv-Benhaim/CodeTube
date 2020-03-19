package com.gamedev.codetube.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.gamedev.codetube.R;
import com.gamedev.codetube.models.RelatedVideo;

import java.util.List;

public class RelatedVideoAdapter extends RecyclerView.Adapter<RelatedVideoAdapter.RelatedVideoHolder> {

    Context mContext;
    List<RelatedVideo> mRelatedVideos;



    public RelatedVideoAdapter(Context mContext, List<RelatedVideo> mRelatedVideos) {
        this.mContext = mContext;
        this.mRelatedVideos = mRelatedVideos;
    }


    @NonNull
    @Override
    public RelatedVideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_related_video, parent,false);

        return new RelatedVideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedVideoHolder holder, int position) {
        //holder.ImgCourse.setImageResource(mRelatedVideos.get(position).getImg_link());
        Glide.with(mContext).load(mRelatedVideos.get(position).getImg_link()).into(holder.ImgCourse);
    }

    @Override
    public int getItemCount() {
        return mRelatedVideos.size();
    }

    public class RelatedVideoHolder extends RecyclerView.ViewHolder{

        ImageView ImgCourse;

        public RelatedVideoHolder(@NonNull View itemView) {
            super(itemView);

            ImgCourse = itemView.findViewById(R.id.img_related_video);

        }
    }
}
