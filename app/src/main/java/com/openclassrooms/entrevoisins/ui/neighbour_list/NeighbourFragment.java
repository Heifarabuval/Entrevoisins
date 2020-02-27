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
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.profile.NeighbourProfileActivity;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.List;

public class NeighbourFragment<itemClickedPosition> extends Fragment implements OnNeighbourListenner  {

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;
    private static int itemClickedPosition ;


    private static final String TAG =NeighbourFragment.class.getSimpleName();









    /**
     * Create and return a new instance
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance() {
        NeighbourFragment fragment = new NeighbourFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: fragment");
        mApiService = DI.getNeighbourApiService();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        initList();
        return view;




    }

    /**
     * Init the List of neighbours
     */
    private void initList() {

        mNeighbours = mApiService.getNeighbours();
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours,this,false));

    }







    private void initUser(){
        mNeighbours=mApiService.getNeighbours();
    }


    /**
     * Fired if the user clicks on a delete button
     * @param event
     */


    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        Log.d(TAG, "onDeleteNeighbour ");
        mApiService.deleteNeighbour(event.neighbour);
        initList();

    }



    @Override
    public void NeighbourClick(int position) {
        itemClickedPosition = position;
        Intent intent= new Intent(getContext(), NeighbourProfileActivity.class);
        intent.putExtra("value",0);
        startActivity(intent);

    }

    public static int getItemClickedPosition() {
        return itemClickedPosition;
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: fragment ");
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: fragment ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: fragment ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: fragment");
    }


}