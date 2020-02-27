package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.openclassrooms.entrevoisins.di.DI;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {




    public ListNeighbourPagerAdapter(FragmentManager fm, TabLayout tabLayout) {
        super(fm);



    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0 :
                return new NeighbourFragment();
            case 1 :
                return new NeighbourFavouriteFragment();
                default:
                    return fragment;
        }

    }





    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {

        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }




}