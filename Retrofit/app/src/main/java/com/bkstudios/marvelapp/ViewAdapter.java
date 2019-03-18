package com.bkstudios.marvelapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class ViewAdapter extends FragmentStatePagerAdapter {

    ArrayList<String> tabs=null;
    ArrayList<Repos> myRepos = null;
    FragmentManager fm=null;


    public ViewAdapter(FragmentManager fm,ArrayList<String> tabs, ArrayList<Repos> myRepos) {
        super(fm);
        this.fm=fm;
        this.tabs=tabs;
        this.myRepos = myRepos;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }


    @Override
    public Fragment getItem(int i) {

        if (i == 0) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("myRepos", myRepos);

            MyProfileFragment fragment = new MyProfileFragment();
            fragment.setArguments(bundle);

            return fragment;
        }  else {
            Bundle bundle = new Bundle();
            bundle.putSerializable("myRepos", myRepos);

            FarvouriteFragemnt farvouriteFragemnt=new FarvouriteFragemnt();
            farvouriteFragemnt.setArguments(bundle);

            return farvouriteFragemnt;
        }


    }

    @Override
    public int getCount() {
        return tabs.size();
    }
}

