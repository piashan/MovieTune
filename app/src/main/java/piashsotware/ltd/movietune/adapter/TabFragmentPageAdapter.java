package piashsotware.ltd.movietune.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import piashsotware.ltd.movietune.MovieFragment;

/**
 * Created by piash on 11/24/16.
 */

public class TabFragmentPageAdapter extends FragmentStatePagerAdapter {

    private Context mContext;
    private String tabTitles[] = new String[] { "New Release", "Top Rated" ,"Upcomming"};

    public TabFragmentPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        return new MovieFragment().newInstance(position);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
