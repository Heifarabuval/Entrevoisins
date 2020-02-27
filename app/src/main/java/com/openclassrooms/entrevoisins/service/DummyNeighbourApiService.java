package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private final List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public List getFavouriteNeighbours() {
        Neighbour neighbour;
        List  mFavouriteNeighbours = new ArrayList();

            for (int i = 0; i <neighbours.size(); i++) {


                neighbour = (neighbours.get(i));

                if ((neighbour.getFavourite()) == true) {
                    mFavouriteNeighbours.add((neighbours.get(i)));

                }
            }


        return mFavouriteNeighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public void deleteFavouriteNeighbour(Neighbour favouriteNeighbour) {
        favouriteNeighbour.setFavourite(false);

    }


    @Override
    public void createNeighbour(Neighbour neighbour) {neighbours.add(neighbour);

    }

    @Override
    public void addFavouriteNeighbour(Neighbour neighbour) {
        neighbour.setFavourite(true);


    }
}
