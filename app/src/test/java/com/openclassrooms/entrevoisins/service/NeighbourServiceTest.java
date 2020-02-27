package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void addNeighbourWithSuccess() {

        Neighbour neighbourToAdd = new Neighbour(13,"Paul","https://i.pravatar.cc/400","","","",false);
        service.createNeighbour(neighbourToAdd);

        assertTrue("Impossible de créer et d'ajouter un voisin à la liste contacts",service.getNeighbours().contains(neighbourToAdd));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse("Impossible de supprimer un voisin de la liste contacts",service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void addNeighbourFavouriteWithSuccess() {
Neighbour favouriteNeighbourToAdd = service.getNeighbours().get(6);
        service.addFavouriteNeighbour(favouriteNeighbourToAdd);
        assertTrue("Impossible d'ajouter un voisin à la liste favoris",service.getFavouriteNeighbours().contains(favouriteNeighbourToAdd));


    }

    @Test
    public void deleteNeighbourFromFavouriteListWithSuccess() {
        Neighbour neighbourToDelete =service.getFavouriteNeighbours().get(0);
        service.deleteFavouriteNeighbour(neighbourToDelete);
        assertFalse("Impossible de supprimer un voisin de la liste favoris",service.getFavouriteNeighbours().contains(neighbourToDelete));



    }
}
