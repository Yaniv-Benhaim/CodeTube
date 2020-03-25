package com.gamedev.codetube.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gamedev.codetube.R;
import com.gamedev.codetube.currentuser.User;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.File;

public class MyAccountFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inf = inflater.inflate(R.layout.fragment_account, container, false);
        TextView tv = inf.findViewById(R.id.person_name_tv);
        TextView email = inf.findViewById(R.id.person_mail_tv);
        ImageView profileImage = inf.findViewById(R.id.profile_image);
        File f = new File(String.valueOf(User.CurrentLoggedIn.personPhoto));
        Picasso.get().load(User.CurrentLoggedIn.personPhoto).into(profileImage);
        tv.setText(User.CurrentLoggedIn.personGivenName + " " + User.CurrentLoggedIn.personFamilyName);
        email.setText(User.CurrentLoggedIn.personEmail);
        return inf;
      //  return inflater.inflate(R.layout.fragment_account, container,false);



    }

}
