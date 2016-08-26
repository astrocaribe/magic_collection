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
        Logger.info(String.format("Starting request - (%s) [%s]", request().method(), request().path()));

        try {
            final List<Card> result = dataStore.getAllCards();
            HashMap<String, List<Card>> response = new HashMap<>();

            response.put("data", result);
            JsonNode content = Json.toJson(response);

            return ok(content);

        } finally {
            Logger.info(String.format("Finished request - (%s) [%s]", request().method(), request().path()));
        }
    }

}
