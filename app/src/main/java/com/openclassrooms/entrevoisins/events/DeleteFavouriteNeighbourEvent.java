package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Neighbour
 */
public class DeleteFavouriteNeighbourEvent {

    /**
     * Neighbour to delete
     */
    public final Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public DeleteFavouriteNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
