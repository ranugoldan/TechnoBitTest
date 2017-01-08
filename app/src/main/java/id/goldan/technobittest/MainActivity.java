package id.goldan.technobittest;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import id.goldan.technobittest.Database.DBHandler;
import id.goldan.technobittest.Fragment.DataFragment;
import id.goldan.technobittest.Fragment.FormFragment;

public class MainActivity extends AppCompatActivity implements FormFragment.OnFragmentInteractionListener, DataFragment.OnFragmentInteractionListener {

    public TabLayout tabLayout;
    ViewPager viewPager;
    MyViewPagerAdapter myViewPagerAdapter;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Toolbar mToolbar;
    public DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("haloo","hehe");
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        Toolbar toolbar = mToolbar;
        toolbar.setTitle("TechnoBit");
        setSupportActionBar(toolbar);

        dbHandler = new DBHandler(this);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Input"));
        tabLayout.addTab(tabLayout.newTab().setText("Data"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager = (ViewPager) findViewById(R.id.myViewPager);
        viewPager.setAdapter(myViewPagerAdapter);
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (position==1){
//                    DataFragment dataFragment = (DataFragment)myViewPagerAdapter.getItem(position);
//                    //dataFragment.loadData();
//                }
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if (position==1){
//                    DataFragment dataFragment = (DataFragment)myViewPagerAdapter.getItem(position);
//                    //dataFragment.loadData();
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(viewPager != null && viewPager.getAdapter() != null) {
            viewPager.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private class MyViewPagerAdapter extends FragmentStatePagerAdapter{
        int count;

        public MyViewPagerAdapter(FragmentManager fm, int tabCount) {
            super(fm);
            this.count = tabCount;
        }


        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new FormFragment();
                case 1:
                    return new DataFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return count;
        }
    }
}
