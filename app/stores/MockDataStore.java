package stores;

import models.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by astrocaribe on 8/28/16.
 *
 * Mock datastore to simulate interaction with a real, live datasource.
 */
public class MockDataStore implements DataStore {
    /**
     * Acts as the data store for the mock data.
     */
    private static final Map<String, Card> resources = initializeResources(mockCard(), anotherMockCard());

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

        final List<Card> cardResults =
                resources.values().stream()
                .filter(card -> colorFilter == null || colorFilter.equals(card.getColor().toLowerCase()))
                .filter(card -> typeFilter == null || typeFilter.equals(card.getType().toLowerCase()))
                .filter(card -> rarityFilter == null || rarityFilter.equals(card.getRarity().toLowerCase()))
                .collect(Collectors.toList());

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
        resource.setType("Creature");
        resource.setSubType("Dog");
        resource.setText("Untap target attacking creature. Then Dogmeat eats your lunch.");
        resource.setExpansion("Fallout IV");
        resource.setPower(5);
        resource.setToughness(5);
        resource.setRarity("Rare");
        resource.setManaCost(10);
        resource.setConvertedManaCost(10);
        resource.setQuantity(3);

        return resource;
    }

    private static Card anotherMockCard() {
        Card resource = new Card();

        // Place static mock values in the response
        resource.setId("1002");
        resource.setName("GlaDOS");
        resource.setColor("White");
        resource.setType("Summon");
        resource.setSubType("AI");
        resource.setText("Blow the world up. Then make cake!");
        resource.setExpansion("Portal");
        resource.setPower(6);
        resource.setToughness(4);
        resource.setRarity("SuperRare");
        resource.setManaCost(4);
        resource.setConvertedManaCost(7);
        resource.setQuantity(2);

        return resource;
    }

    private static Map<String, Card> initializeResources(Card card1, Card card2) {
        HashMap<String, Card> mockResources = new HashMap<>();

        mockResources.put("1", card1);
        mockResources.put("2", card2);

        return mockResources;
    }

}
