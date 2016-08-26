package stores;

import models.Card;

import java.util.List;

/**
 * Created by astrocaribe on 8/25/16.
 */
public interface DataStore {
    /**
     * Returns the card collection
     * @return cardCollection
     */
    List<Card> getAllCards();
}
