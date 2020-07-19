package com.gamedev.codetube.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gamedev.codetube.R;
import com.gamedev.codetube.currentuser.User;
import com.gamedev.codetube.ui.About;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.File;

public class MyAccountFragment extends Fragment {

    private LinearLayout aboutBtn;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inf = inflater.inflate(R.layout.fragment_account, container, false);
        TextView tv = inf.findViewById(R.id.person_name_tv);
        TextView email = inf.findViewById(R.id.person_mail_tv);
        ImageView profileImage = inf.findViewById(R.id.profile_image);
        Picasso.get().load(User.CurrentLoggedIn.personPhoto).into(profileImage);
        tv.setText(User.CurrentLoggedIn.personGivenName + " " + User.CurrentLoggedIn.personFamilyName);
        email.setText(User.CurrentLoggedIn.personEmail);
        aboutBtn = inf.findViewById(R.id.about_codetube_btn);
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), About.class);
                startActivity(intent);
            }
        });

        return inf;
      //  return inflater.inflate(R.layout.fragment_account, container,false);



    }

}
