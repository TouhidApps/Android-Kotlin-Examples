package com.touhidapps.viewpagerandtablayout;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by touhid on 8/4/17.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private String fragments[] = {"Frag One", "Frag Two"};

    public MyViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MyFragmentOne();
            case 1:
                return new MyFragmentTwo();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position];
    }


}
