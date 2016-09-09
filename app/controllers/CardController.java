package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import models.Card;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import stores.DataStore;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by astrocaribe on 8/25/16.
 */


public class CardController extends Controller {
    private final DataStore dataStore;

    @Inject
    public CardController(final DataStore dataStore) { this.dataStore = dataStore; }

    /**
     * GET Card Collection
     *
     * <p>Returns the card collection. All cards in the collection are returned in JSON format.</p>
     */
    public Result getAllCards() {
        Logger.info(String.format("Starting request - [%s %s]", request().method(), request().path()));

        try {
            final List<Card> result = dataStore.getAllCards();
            HashMap<String, List<Card>> response = new HashMap<>();

            response.put("data", result);
            JsonNode content = Json.toJson(response);

            return ok(content);

        } finally {
            Logger.info(String.format("Finished request - [%s %s]", request().method(), request().path()));
        }
    }

    /**
     * GET Filtered Card Collection
     *
     * <p>Returns a filtered card collection, containing zero or more objects. All cards in the collection are
     * returned in JSON format.</p>
     */
    public Result getCardsByFilter() {
        Logger.info(String.format("Starting request - [%s %s]", request().method(), request().path()));

        try {
            final Map<String, String[]> queryString = request().queryString();

            final String colorFilter = getFilterValueFromQueryString("color", queryString);
            final String typeFilter = getFilterValueFromQueryString("type", queryString);
            final String rarityFilter = getFilterValueFromQueryString("rarity", queryString);

            final List<Card> result = dataStore.searchByFilters(colorFilter, typeFilter, rarityFilter);

            HashMap<String, List<Card>> response = new HashMap<>();

            response.put("data", result);
            JsonNode content = Json.toJson(response);

            return ok(content);

        } finally {
            Logger.info(String.format("Finished request - [%s %s]", request().method(), request().path()));
        }
    }

    /**
     * GET Card Resource
     *
     * <p>Returns a card resource by given id. One card is returned in the JSON format.</p>
     * @param cardId
     * @return
     */
    public Result getCardById(String cardId) {
        Logger.info(String.format("Starting request - [%s %s]", request().method(), request().path()));

        try {
            final Card result = dataStore.searchById(cardId);

            HashMap<String, Card> response = new HashMap<>();

            response.put("data", result);
            JsonNode content = Json.toJson(response);

            return ok(content);

        } finally {
            Logger.info(String.format("Starting request - [%s %s]", request().method(), request().path()));
        }
    }

    private static String getFilterValueFromQueryString(final String key, final Map<String, String[]> queryString) {
        final String filterKey = String.format("filter[%s]", key);
        final String value = queryString.containsKey(filterKey) ? queryString.get(filterKey)[0] : null;

        return value;
    }

}
