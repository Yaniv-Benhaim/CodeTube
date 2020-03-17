package com.gamedev.codetube.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.gamedev.codetube.R;
import com.gamedev.codetube.models.Slide;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    private Context mContext;
    private List<Slide> mSlideList;


    public SliderAdapter(Context mContext, List<Slide> mSlideList) {
        this.mContext = mContext;
        this.mSlideList = mSlideList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.slider_item, null);

        ImageView slideImg = slideLayout.findViewById(R.id.slide_img);
        TextView slideTitle = slideLayout.findViewById(R.id.slide_title);

        slideImg.setImageResource(mSlideList.get(position).getImage());
        slideTitle.setText(mSlideList.get(position).getTitle());
        container.addView(slideLayout);

        return slideLayout;
    }

    @Override
    public int getCount() {
        return mSlideList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
