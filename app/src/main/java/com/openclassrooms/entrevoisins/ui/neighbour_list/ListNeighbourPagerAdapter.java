package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


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
                Bundle data = new Bundle();
                NeighbourFragmentFusion contact = new NeighbourFragmentFusion();
                data.putInt("tabPosition",position);
                contact.setArguments(data);
                return contact;

            case 1 :
                Bundle data2 = new Bundle();
                NeighbourFragmentFusion favoris = new NeighbourFragmentFusion();
                data2.putInt("tabPosition",position);
                favoris.setArguments(data2);
                return  favoris;
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