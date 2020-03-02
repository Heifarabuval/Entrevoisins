package com.openclassrooms.entrevoisins.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

import butterknife.ButterKnife;

public  class NeighbourProfileActivity extends AppCompatActivity {


    private List<Neighbour> mNeighbours;
    List<Neighbour> favouriteNeighbours;
    private static final String PREFS_ARRAY = "PREFS_ARRAY";
    private static final String TAG = NeighbourProfileActivity.class.getSimpleName();
    private final static String facebook = " www.facebook.fr/";
    private NeighbourApiService mApiService;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour);
        mApiService = DI.getNeighbourApiService();

        /**
        Get tab et click position
         */
        final Intent intent = getIntent();
        int tabsPosition = intent.getIntExtra("tabPosition", 0);
        int clickPosition = intent.getIntExtra("clicPosition",0);

        /**
         Link XML and JAVA
         */
        FloatingActionButton floatingActionButton = findViewById(R.id.item_favoris);
        ImageView imageView = findViewById(R.id.item_avatar);
        TextView textViewSecond = findViewById(R.id.item_name_second);
        TextView textView = findViewById(R.id.item_name);
        TextView textViewSocial = findViewById(R.id.social);
        TextView textViewPhone = findViewById(R.id.phone);
        TextView textViewAdress = findViewById(R.id.position);
        TextView textViewAboutMe = findViewById(R.id.about_me);

/**
 * Add neighbour to favourite List
 Case 0 : Contact tab
 Case 1 : Favourite tab
 */
        switch (tabsPosition) {
            case 0:
                mNeighbours = mApiService.getNeighbours();
                int position = clickPosition;
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

                    }else{
                        Toast.makeText(getApplicationContext(), "Déja dans vos  favoris", Toast.LENGTH_LONG).show();
                    }
                });

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                ButterKnife.bind(this);
                break;

            case 1:
                int itemClickedPosition = clickPosition;
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

                    if (favouriteNeighbours.contains(neighbour) && neighbour.getFavourite() == false) {

                        mApiService.addFavouriteNeighbour(neighbour);
                        Toast.makeText(getApplicationContext(), "Ajouté aux favoris", Toast.LENGTH_LONG).show();

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
