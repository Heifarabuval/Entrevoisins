package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteFavouriteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import org.greenrobot.eventbus.EventBus;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder>  {




    private final List<Neighbour> mNeighbours;
    final boolean favourite;
    private final OnNeighbourListenner mOnNeighbourListenner;
    public static final String TAG= MyNeighbourRecyclerViewAdapter.class.getSimpleName();



    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items, OnNeighbourListenner onNeighbourListenner,boolean fav) {
        mNeighbours = items;
        this.favourite=fav;
        this.mOnNeighbourListenner = onNeighbourListenner;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_neighbour, parent, false);

            return new ViewHolder(view, mOnNeighbourListenner);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);

        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);
        holder.mDeleteButton.setOnClickListener(v -> {

    if (favourite == false){
        System.out.println("Contact");
        EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
        EventBus.getDefault().post(new DeleteFavouriteNeighbourEvent(neighbour));

    }else{

        System.out.println("Favoris Va etre supprimé");
        mNeighbours.get(position).setFavourite(false);
         EventBus.getDefault().post(new DeleteFavouriteNeighbourEvent(neighbour));
    }
        });
            }












    @Override
    public int getItemCount() {
        return mNeighbours.size();

    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;



        final OnNeighbourListenner onNeighbourListenner;


        public ViewHolder(View view,OnNeighbourListenner onNeighbourListenner) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            this.onNeighbourListenner=onNeighbourListenner;
        }

        @Override
        public void onClick(View v) {
            onNeighbourListenner.NeighbourClick(getAdapterPosition());


        }

    }

    public List<Neighbour> getNeighbours() {
        return mNeighbours;
    }



}