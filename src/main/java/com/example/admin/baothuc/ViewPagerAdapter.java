package com.example.admin.baothuc;

/**
 * Created by admin on 4/14/2018.
 */
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<MyFragment> fragments;

    public ViewPagerAdapter(FragmentManager fm, List<MyFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getName();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return (fragments == null)? 0:fragments.size();
    }
}
