package com.openclassrooms.entrevoisins.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFavouriteFragment;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public  class NeighbourProfileActivity extends AppCompatActivity {


    private List<Neighbour> mNeighbours;

    private static final String PREFS_ARRAY = "PREFS_ARRAY";
    private static final String TAG = NeighbourProfileActivity.class.getSimpleName();
    private final static String facebook = " www.facebook.fr/";
    private NeighbourApiService mApiService;


    //Savegarde Favoris
    private void saveData(){
        SharedPreferences sharedPreferences=getSharedPreferences(PREFS_ARRAY,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json=gson.toJson(mNeighbours);
        editor.putString("Rank saver",json);
        editor.apply();

    }

    //Lecture Favoris
    private  void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_ARRAY,MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Rank saver",null);
        Type type = new TypeToken<ArrayList<Neighbour>>(){}.getType();
        mNeighbours=gson.fromJson(json,type);
        if (mNeighbours==null){
            mNeighbours= new ArrayList<>();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour);
        loadData();


        mApiService = DI.getNeighbourApiService();
        List<Neighbour> favouriteNeighbours;


        final Intent intent = getIntent();
        int tabsPosition = intent.getIntExtra("value", 1);



        mApiService = DI.getNeighbourApiService();
        FloatingActionButton floatingActionButton = findViewById(R.id.item_favoris);
        ImageView imageView = findViewById(R.id.item_avatar);
        TextView textViewSecond = findViewById(R.id.item_name_second);
        TextView textView = findViewById(R.id.item_name);
        TextView textViewSocial = findViewById(R.id.social);
        TextView textViewPhone = findViewById(R.id.phone);
        TextView textViewAdress = findViewById(R.id.position);
        TextView textViewAboutMe = findViewById(R.id.about_me);

        mNeighbours = mApiService.getNeighbours();
        switch (tabsPosition) {
            case 0:
                int position = NeighbourFragment.getItemClickedPosition();
                Neighbour neighbour = mNeighbours.get(position);
                textView.setText(neighbour.getName());
                textViewSecond.setText(neighbour.getName());
                textViewSocial.setText(facebook + neighbour.getName());
                textViewAdress.setText(neighbour.getAddress());
                textViewAboutMe.setText(neighbour.getAboutMe());
                textViewPhone.setText(neighbour.getPhoneNumber());
                Glide.with(imageView.getContext())
                        .load(neighbour.getAvatarUrl())
                        .apply(RequestOptions.centerCropTransform())
                        .into(imageView);

                floatingActionButton.setOnClickListener(v -> {

                    if (mNeighbours.contains(neighbour) && neighbour.getFavourite() == false) {
                        mApiService.addFavouriteNeighbour(neighbour);
                        Toast.makeText(getApplicationContext(), "Ajouté aux favoris", Toast.LENGTH_LONG).show();
                        saveData();
                    }else{
                        Toast.makeText(getApplicationContext(), "Déja dans vos  favoris", Toast.LENGTH_LONG).show();
                    }
                });

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                ButterKnife.bind(this);
                break;

            case 1:
                int itemClickedPosition = NeighbourFavouriteFragment.getItemClickedPosition();
                favouriteNeighbours=mApiService.getFavouriteNeighbours();
                neighbour = favouriteNeighbours.get(itemClickedPosition);
                textView.setText(neighbour.getName());
                textViewSecond.setText(neighbour.getName());
                textViewSocial.setText(facebook + neighbour.getName());
                textViewAdress.setText(neighbour.getAddress());
                textViewAboutMe.setText(neighbour.getAboutMe());
                textViewPhone.setText(neighbour.getPhoneNumber());
                Glide.with(imageView.getContext())
                            .load(neighbour.getAvatarUrl())
                            .apply(RequestOptions.centerCropTransform())
                            .into(imageView);

                floatingActionButton.setOnClickListener(v -> {

                    if (mNeighbours.contains(neighbour) && neighbour.getFavourite() == false) {

                        mApiService.addFavouriteNeighbour(neighbour);
                        Toast.makeText(getApplicationContext(), "Ajouté aux favoris", Toast.LENGTH_LONG).show();
                        saveData();
                    } else {
                        Toast.makeText(getApplicationContext(), "Déja dans vos  favoris", Toast.LENGTH_LONG).show();
                    }
                });
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                ButterKnife.bind(this);
                break;
        }
    }
}
