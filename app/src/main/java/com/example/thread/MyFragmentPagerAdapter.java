package com.example.thread;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    public MyFragmentPagerAdapter(FragmentManager fm){
        super(fm);
    }

    public Fragment getItem(int position){
        switch (position){
            case 0:
                return new FirstFragment();
            case 1:
                return new SecondFragment();
                default:
                    return null;
        }
    }


    public int getCount(){
        return 2;
    }
}
