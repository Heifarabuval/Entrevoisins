package com.openclassrooms.entrevoisins.utils;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;

import java.util.List;

public class InitList {
    final List<Neighbour> normalUser =  DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
    final List<Neighbour> favouriteUser =  DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
    final int sizeList = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.size();
    Neighbour mNeighbour;


    public List<Neighbour> initList(){
        for (int i = 0; i <sizeList ; i++) {
            mNeighbour = normalUser.get(i);
            if(mNeighbour.getFavourite()==true){
                favouriteUser.add(mNeighbour);

            }

        }return favouriteUser;

    }
}
