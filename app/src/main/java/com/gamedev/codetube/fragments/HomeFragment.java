package com.gamedev.codetube.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gamedev.codetube.R;
import com.gamedev.codetube.ui.MainActivity;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Intent intent = new Intent(getActivity(), MainActivity.class);
        //startActivity(intent);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
