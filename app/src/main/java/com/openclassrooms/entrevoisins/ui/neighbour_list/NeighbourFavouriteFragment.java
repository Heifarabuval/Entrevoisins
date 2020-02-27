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
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.profile.NeighbourProfileActivity;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;


public class NeighbourFavouriteFragment<itemClickedPosition> extends Fragment implements OnNeighbourListenner {

    private NeighbourApiService mApiService;
    private List<Neighbour> mFavouriteNeighbour;
    private RecyclerView mRecyclerView;
    private static int itemClickedPosition ;
    private Neighbour neighbour;
    private static final String TAG = NeighbourFavouriteFragment.class.getSimpleName();




    /**
     * Create and return a new instance
     * @return @{@link NeighbourFavouriteFragment}
     */
    public static NeighbourFavouriteFragment newInstance() {
        return new NeighbourFavouriteFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: fragment");
        super.onCreate(savedInstanceState);

        mFavouriteNeighbour=new ArrayList<>();


        mApiService = DI.getNeighbourApiService();




    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView() called ");
        View view = inflater.inflate(R.layout.fragment_neighbour_favourite_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        initFavouriteList();
        return view;
    }

    /**
     * Init the List of favourite neighbours
     */
    private void initFavouriteList() {
        Log.d(TAG, "initFavouriteList: méthode call");

        mFavouriteNeighbour.clear();
        for (int i = 0; i < mApiService.getNeighbours().size(); i++) {
            neighbour = (mApiService.getNeighbours().get(i));
            if ((neighbour.getFavourite()) == true) {
                Log.d(TAG, "initFavouriteList: le voisin est favoris");
                mFavouriteNeighbour.add((mApiService.getNeighbours().get(i)));
            }
        }mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mFavouriteNeighbour,this,true));

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

        Log.d(TAG, "onDeleteFavouriteNeighbour");
        initFavouriteList();

    }



    @Override
    public void NeighbourClick(int position) {
        itemClickedPosition = position;
        neighbour = mFavouriteNeighbour.get(position);
        Intent intent= new Intent(getContext(), NeighbourProfileActivity.class);
        intent.putExtra("tabValue",1);
        startActivity(intent);

    }
    public static int getItemClickedPosition() {

        return itemClickedPosition;
    }


    @Override
    public void onStart() {
        initFavouriteList();
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