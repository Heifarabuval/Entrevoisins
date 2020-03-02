package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteFavouriteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.profile.NeighbourProfileActivity;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class NeighbourFragmentFusion extends Fragment implements OnNeighbourListenner {

    private NeighbourApiService mApiService;
    private List<Neighbour> mFavouriteNeighbour;
    private List<Neighbour> mNeighbour;
    private RecyclerView mRecyclerView;
    private static int itemClickedPosition ;
    private Neighbour neighbour;
    private static final String TAG = NeighbourFragmentFusion.class.getSimpleName();
    int tabPosition;




    /**
     * Create and return a new instance
     * @return @{@link NeighbourFragmentFusion}
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: fragment");
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();

    }



    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            tabPosition=getArguments().getInt("tabPosition");

        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() called ");
        View view = inflater.inflate(R.layout.fragment_neighbour_recycler_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        readBundle(getArguments());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        switch (tabPosition){
            case 0 :
                mFavouriteNeighbour=mApiService.getNeighbours();
                break;
            case 1:
                mFavouriteNeighbour = mApiService.getFavouriteNeighbours();
                break; }

        return view;
    }

    /**
     * Init the List of favourite neighbours
     */

    private void initList() {
        Log.d(TAG, "initFavouriteList: méthode call"+tabPosition);


        switch (tabPosition){
            case 0 :
                mNeighbour=mApiService.getNeighbours();
                mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbour,this,false));
                break;
            case 1:
                mFavouriteNeighbour = mApiService.getFavouriteNeighbours();
                mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mFavouriteNeighbour,this,true));
                break; }

    }



    private void initUser(){
        mFavouriteNeighbour=mApiService.getNeighbours();
    }



    /**
     * Fired if the user clicks on a delete button
     * @param event
     */


    @Subscribe
    public void onDeleteFavouriteNeighbour(DeleteFavouriteNeighbourEvent event) {
        initList();

    }
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteNeighbour(event.neighbour);
        initList();

    }












    @Override
    public void NeighbourClick(int position) {
        itemClickedPosition = position;
        neighbour = mFavouriteNeighbour.get(position);

        Intent intent= new Intent(getContext(), NeighbourProfileActivity.class);
        intent.putExtra("clicPosition",itemClickedPosition);
        intent.putExtra("tabPosition",tabPosition);
        startActivity(intent);

    }



    @Override
    public void onStart() {
        initList();
        super.onStart();
        Log.d(TAG, "onStart: fragment");
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }





}