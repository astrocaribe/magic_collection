package stores;

import models.Card;

import java.util.List;

/**
 * Created by astrocaribe on 8/25/16.
 */
public interface DataStore {
    /**
     * Returns the card collection.
     * @return a {@Link List<Card>} in the database
     */
    List<Card> getAllCards();

    /**
     * Return a card collection filtered by the following:
     * @param colorFilter color to filter by
     * @param typeFilter type to filter by
     * @param rarityFilter rarity to filter by
     * @return a filtered {@Link List<Card>} collection
     */
    List<Card> searchByFilters(String colorFilter, String typeFilter, String rarityFilter);
}
