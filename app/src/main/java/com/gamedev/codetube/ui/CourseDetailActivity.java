package com.gamedev.codetube.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.gamedev.codetube.R;
import com.gamedev.codetube.adapters.RelatedVideoAdapter;
import com.gamedev.codetube.models.RelatedVideo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailActivity extends AppCompatActivity {

     ImageView CourseThumb, CourseCoverImg;
     TextView tv_title,tv_description;
     private FloatingActionButton play_fab;
     private RecyclerView rv_related_videos_id;
     private RelatedVideoAdapter relatedVideoAdapter;
    private InterstitialAd interstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        //show ad
        showAd();

        //ini Vies
        iniViews();

        //Setup related video rv
        setupRvRelatedVideos();

        play_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sending course information to detail activity

                String streamingLink = getIntent().getExtras().getString("link");
                Intent intent = new Intent(CourseDetailActivity.this, MovieFireBase.class);
                intent.putExtra("link",streamingLink);
                startActivity(intent);
            }
        });



    }

    private void showAd() {

        interstitialAd = new InterstitialAd(this, "874978662996468_875003532993981");
        interstitialAd.setAdListener(new InterstitialAdListener() {
            private static final String TAG = "FACEBOOK VIDEO AD" ;

            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback

            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!");
            }
        });

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd();

        showAdWithDelay();



    }

    void iniViews(){
        rv_related_videos_id = findViewById(R.id.rv_related_videos);
        play_fab = findViewById(R.id.play_fab);
        String movie = getIntent().getExtras().getString("title");
        String description = getIntent().getExtras().getString("description");
        int imageResource = getIntent().getExtras().getInt("imgURL");
        int imageCover = getIntent().getExtras().getInt("imgCover");
        CourseThumb = findViewById(R.id.detail_course_img);
        Glide.with(this).load(imageResource).into(CourseThumb);
        CourseThumb.setImageResource(imageResource);
        tv_title = findViewById(R.id.detail_course_title);
        tv_title.setText(movie);
        //getSupportActionBar().setTitle(movie);
        tv_description = findViewById(R.id.detail_course_description);
        tv_description.setText(description);
        CourseCoverImg = findViewById(R.id.detail_course_cover);
        Glide.with(this).load(imageCover).into(CourseCoverImg);
        //set animations
        CourseCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
    }
    void setupRvRelatedVideos(){
        List<RelatedVideo> related_videos_list = new ArrayList<>();
        related_videos_list.add(new RelatedVideo("Java part 2", R.drawable.javascript1));
        related_videos_list.add(new RelatedVideo("Java part 3", R.drawable.javascript1));
        related_videos_list.add(new RelatedVideo("Java part 4", R.drawable.javascript1));
        related_videos_list.add(new RelatedVideo("Java part 5", R.drawable.javascript1));
        related_videos_list.add(new RelatedVideo("Java part 6", R.drawable.javascript1));
        related_videos_list.add(new RelatedVideo("Java part 7", R.drawable.javascript1));

        relatedVideoAdapter = new RelatedVideoAdapter(this,related_videos_list);
        rv_related_videos_id.setAdapter(relatedVideoAdapter);
        rv_related_videos_id.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void showAdWithDelay() {
        /**
         * Here is an example for displaying the ad with delay;
         * Please do not copy the Handler into your project
         */
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Check if interstitialAd has been loaded successfully
            if(interstitialAd == null || !interstitialAd.isAdLoaded()) {
                return;
            }
            // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
            if(interstitialAd.isAdInvalidated()) {
                return;
            }
            // Show the ad
            interstitialAd.show();
        }, 0); // Show the ad after 15 minutes
    }
    @Override
    protected void onDestroy() {
        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
        super.onDestroy();
    }
}
