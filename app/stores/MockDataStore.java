package stores;

import models.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by astrocaribe on 8/28/16.
 *
 * Mock datastore to simulate interaction with a real, live datasource.
 */
public class MockDataStore implements DataStore {
    public MockDataStore() { }

    /**
     * {@inheritDoc}
     */
    public List<Card> getAllCards() {
        List<Card> cardListResults = new ArrayList<>();

        Card mockReturnValue = mockCard();
        cardListResults.add(mockReturnValue);

        // Add another card to the response
        Card anotherMockReturnValue = anotherMockCard();
        cardListResults.add(anotherMockReturnValue);


        return cardListResults;
    }

    /**
     * {@inheritDoc}
     */
    public List<Card> searchByFilters(String colorFilter, String typeFilter, String rarityFilter) {
        List<Card> cardResults = new ArrayList<>();

        // Add first card
        Card mockReturnValue = mockCard();
        cardResults.add(mockReturnValue);

        return cardResults;
    }

    /**
     * Build a mock card object
     * @return a mock {@List<Card>} object
     */
    private static Card mockCard() {
        Card resource = new Card();

        // Place static mock values in the response
        resource.setId("1001");
        resource.setName("Dogmeat");
        resource.setColor("Brown");
        resource.setType("Creature Dog");
        resource.setText("Untap target attacking creature. Then Dogmeat eats your lunch.");
        resource.setExpansion("Fallout IV");
        resource.setPower(5);
        resource.setToughness(5);
        resource.setRarity("Rare");
        resource.setManaCost(10);
        resource.setConvertedManaCost(10);

        return resource;
    }

    private static Card anotherMockCard() {
        Card resource = new Card();

        // Place static mock values in the response
        resource.setId("1002");
        resource.setName("GlaDOS");
        resource.setColor("White");
        resource.setType("Summon AI");
        resource.setText("Blow the world up. Then make cake!");
        resource.setExpansion("Portal");
        resource.setPower(6);
        resource.setToughness(4);
        resource.setRarity("Super Rare");
        resource.setManaCost(4);
        resource.setConvertedManaCost(7);

        return resource;
    }

}
