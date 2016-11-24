package piashsotware.ltd.movietune;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import piashsotware.ltd.movietune.adapter.TabFragmentPageAdapter;

public class MainActivity extends AppCompatActivity {

    private TabFragmentPageAdapter mAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

        mAdapter = new TabFragmentPageAdapter(getSupportFragmentManager(), getApplication());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
