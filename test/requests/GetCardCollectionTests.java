package requests;

import com.fasterxml.jackson.databind.JsonNode;
import ioc.Module;
import models.Card;
import org.junit.Before;
import org.junit.Test;
import play.Application;

import org.assertj.core.api.SoftAssertions;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.Json;
import play.mvc.Result;
import stores.DataStore;
import stores.MockDataStore;

import static org.assertj.core.api.Assertions.assertThat;

import static play.inject.Bindings.bind;
import static play.test.Helpers.*;

/**
 * Created by tleblanc on 8/30/16.
 */
public class GetCardCollectionTests {
    Application testApp;

    @Before
    public void initialize() {
        testApp = new GuiceApplicationBuilder()
                .overrides(bind(DataStore.class).to(MockDataStore.class))
                .build();
    }

    @Test
    public void responseOk() {
        running(testApp, () -> {
            Result result = route(fakeRequest(GET, "/cards"));
            assertThat(result.status()).isEqualTo(OK);
        });
    }

    @Test
    public void responseContentType() {
        running(testApp, () -> {
            Result result = route(fakeRequest(GET, "/cards"));
            assertThat(result.contentType()).isEqualTo("application/json");
        });
    }

    @Test
    public void responseCharType() {
        running(testApp, () -> {
            Result result = route(fakeRequest(GET, "/cards"));
            assertThat(result.charset()).isEqualTo("utf-8");
        });
    }

    @Test
    public void responseMessageIsJSON() {
        running(testApp, () -> {
            Result result = route(fakeRequest(GET, "/cards"));
            assertThat(Json.parse(contentAsString(result))).isInstanceOf(JsonNode.class);
        });
    }

    @Test
    public void jsonResponseTest() {
        running(testApp, () -> {
            Result result = route(fakeRequest(GET, "/cards"));

            // Assure there is only one data node.
            JsonNode fullJson = Json.parse(contentAsString(result));
            assertThat(fullJson.fieldNames()).containsExactly("data");

            // Assure that object is an array, and that there are 2 records.
            JsonNode innerJson = fullJson.get("data");
            assertThat(innerJson.isArray()).isTrue();
            assertThat(innerJson).hasSize(2);

            JsonNode item = innerJson.get(0);
            Card parsedItem = Json.fromJson(item, Card.class);

            assertThat(item.fieldNames()).containsOnly(
                    "id", "name", "color", "type", "text", "mana_cost", "converted_mana_cost",
                    "recordType", "power", "toughness", "expansion", "rarity"
            );

            // Hold all assertions until all test in this block are complete
            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(parsedItem.getId()).isEqualTo("1001");
            softly.assertThat(parsedItem.getName()).isEqualTo("Dogmeat");
            softly.assertThat(parsedItem.getColor()).isEqualTo("Brown");
            softly.assertThat(parsedItem.getRecordType()).isEqualTo("card");
            softly.assertThat(parsedItem.getType()).isEqualTo("Creature Dog");
            softly.assertThat(parsedItem.getText()).isEqualTo("Untap target attacking creature. Then Dogmeat eats your lunch.");
            softly.assertThat(parsedItem.getExpansion()).isEqualTo("Fallout IV");
            softly.assertThat(parsedItem.getPower()).isEqualTo(5);
            softly.assertThat(parsedItem.getToughness()).isEqualTo(5);
            softly.assertThat(parsedItem.getManaCost()).isEqualTo(10);
            softly.assertThat(parsedItem.getConvertedManaCost()).isEqualTo(10);
            softly.assertThat(parsedItem.getRarity()).isEqualTo("Rare");

            softly.assertAll();
        });
    }
}
