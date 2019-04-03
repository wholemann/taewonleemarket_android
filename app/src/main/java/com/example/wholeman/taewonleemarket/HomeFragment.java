package com.example.wholeman.taewonleemarket;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.home_rv);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setItemAnimator(new DefaultItemAnimator());


        return view;
    }
}
