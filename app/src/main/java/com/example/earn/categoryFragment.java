package com.example.earn;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class categoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.cRView);
        List<offers> list = new ArrayList<>();
        list.add(new offers(R.drawable.flipkart2));
        list.add(new offers(R.drawable.flipkart3));
        list.add(new offers(R.drawable.ajios));
        list.add(new offers(R.drawable.flipkart));

        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        recyclerView.setAdapter(new categoryAdapter(list));
        return view;
    }
}