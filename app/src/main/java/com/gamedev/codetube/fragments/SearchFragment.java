package com.gamedev.codetube.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gamedev.codetube.R;
import com.gamedev.codetube.adapters.SearchRecyclerAdapter;
import com.gamedev.codetube.models.Course;
import com.gamedev.codetube.ui.MainActivity;
import com.gamedev.codetube.ui.SearchActivity;
import com.gamedev.codetube.utils.DataSource;

import java.util.List;

public class SearchFragment extends Fragment {

    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivity(intent);
        v= inflater.inflate(R.layout.fragment_search, container,false);
        return v;
    }



}
