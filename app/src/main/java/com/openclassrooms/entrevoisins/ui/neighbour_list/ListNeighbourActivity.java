package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.profile.NeighbourProfileActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListNeighbourActivity extends AppCompatActivity implements OnNeighbourListenner {







    private static final String TAG="OnNeighbourListenner";
    private int mTabPosition;


    // UI Components
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    public
    ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called with: savedInstanceState");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);





        setSupportActionBar(mToolbar);
        ListNeighbourPagerAdapter pagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager(), mTabLayout);
        mViewPager.setAdapter(pagerAdapter);



        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mTabPosition=tab.getPosition();
                setTabPosition(tab.getPosition());
                mViewPager.setCurrentItem(mTabPosition);
                Log.d(TAG, "onTabSelected() called with: tab = [" + (mTabPosition)+ "]");


                Intent intent= new Intent(mViewPager.getContext(), NeighbourProfileActivity.class);
                intent.putExtra("tabPosition",mTabPosition);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


    }

    @OnClick(R.id.add_neighbour)
    void addNeighbour() {
        AddNeighbourActivity.navigate(this);
    }









    @Override
    public void NeighbourClick(int position) {

    }




    @Override
    protected void onStart() {

        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause() called");

        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop() called");

        super.onStop();
    }

    @Override
    protected void onDestroy() {

        Log.d(TAG, "onDestroy() called");
        super.onDestroy();
    }






    private void setTabPosition(int tabPosition) {
        this.mTabPosition = tabPosition;
        Log.d(TAG, "setTabPosition() called with: mTabPosition = [" + tabPosition + "]");

    }






}


