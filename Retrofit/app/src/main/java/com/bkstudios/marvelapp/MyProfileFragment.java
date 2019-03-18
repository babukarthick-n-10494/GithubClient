package com.bkstudios.marvelapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class    MyProfileFragment extends Fragment {
    ArrayList<Repos> data=new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = (ArrayList<Repos>) getArguments().getSerializable("myRepos");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager, container, false);

        RecyclerView recyclerView= (RecyclerView)view.findViewById(R.id.list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);


        MyProfileAdapter adapter = new MyProfileAdapter(data,getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }
}

