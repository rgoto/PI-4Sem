package com.example.leandro.pi_4sem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leandro on 21/10/2017.
 */

class SectionPageAdpter extends FragmentPagerAdapter {

    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentesTitleList = new ArrayList<>();


    public void addFragment (Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentesTitleList.add(title);
    }

    public SectionPageAdpter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentesTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
