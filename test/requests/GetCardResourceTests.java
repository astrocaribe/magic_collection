package requests;


import com.fasterxml.jackson.databind.JsonNode;
import models.Card;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.Json;
import play.mvc.Result;
import stores.DataStore;
import stores.MockDataStore;

import static org.assertj.core.api.Assertions.assertThat;
import static play.inject.Bindings.bind;
import static play.test.Helpers.*;

/**
 * Created by astrocaribe on 9/8/16.
 */
public class GetCardResourceTests {
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
            Result result = route(fakeRequest(GET, "/card/1001"));
            assertThat(result.status()).isEqualTo(OK);
        });
    }

    @Test
    public void responseContentType() {
        running(testApp, () -> {
            Result result = route(fakeRequest(GET, "/card/1001"));
            assertThat(result.contentType()).isEqualTo("application/json");
        });
    }

    @Test
    public void responseCharType() {
        running(testApp, () -> {
            Result result = route(fakeRequest(GET, "/card/1001"));
            assertThat(result.charset()).isEqualTo("utf-8");
        });
    }

    @Test
    public void responseMessageIsJSON() {
        running(testApp, () -> {
            Result result = route(fakeRequest(GET, "/card/1001"));
            assertThat(Json.parse(contentAsString(result))).isInstanceOf(JsonNode.class);
        });
    }

    @Test
    public void jsonResponseTest() {
        running(testApp, () -> {
            Result result = route(fakeRequest(GET, "/card/1001"));

            // Assure there is only one data node.
            JsonNode fullJson = Json.parse(contentAsString(result));
            assertThat(fullJson.fieldNames()).containsExactly("data");

            // Assure that object is an object, and that there are 16 items.
            // TODO: Please revisit this!
            JsonNode innerJson = fullJson.get("data");
            assertThat(innerJson.isObject()).isTrue();
            assertThat(innerJson).hasSize(16);

//            JsonNode item = innerJson.get(0);
            Card parsedItem = Json.fromJson(innerJson, Card.class);

            assertThat(innerJson.fieldNames()).containsOnly(
                    "id", "name", "colors", "type", "sub_type", "text", "flavor_text", "mana_cost", "converted_mana_cost",
                    "recordType", "power", "toughness", "expansion", "rarity", "quantity", "card_number"
            );

            // Hold all assertions until all test in this block are complete
            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(parsedItem.getId()).isEqualTo("1001");
            softly.assertThat(parsedItem.getName()).isEqualTo("Dogmeat");
            softly.assertThat(parsedItem.getColors().get(0).getColor1()).isEqualTo("Brown");
            softly.assertThat(parsedItem.getColors().get(0).getColor2()).isEqualTo("Black");
            softly.assertThat(parsedItem.getColors().get(0).getColor3()).isEqualTo("Green");
            softly.assertThat(parsedItem.getRecordType()).isEqualTo("card");
            softly.assertThat(parsedItem.getType()).isEqualTo("Creature");
            softly.assertThat(parsedItem.getSubType()).isEqualTo("Dog");
            softly.assertThat(parsedItem.getText()).isEqualTo("Untap target attacking creature. Then Dogmeat eats your lunch.");
            softly.assertThat(parsedItem.getFlavorText()).isEqualTo("Go get him, boy! - The Vault Dweller");
            softly.assertThat(parsedItem.getExpansion()).isEqualTo("Fallout IV");
            softly.assertThat(parsedItem.getPower()).isEqualTo(5);
            softly.assertThat(parsedItem.getToughness()).isEqualTo(5);
            softly.assertThat(parsedItem.getManaCost()).isEqualTo(10);
            softly.assertThat(parsedItem.getConvertedManaCost()).isEqualTo(10);
            softly.assertThat(parsedItem.getRarity()).isEqualTo("Rare");
            softly.assertThat(parsedItem.getQuantity()).isEqualTo(3);
            softly.assertThat(parsedItem.getCardNumber()).isEqualTo("10c");

            softly.assertAll();
        });
    }
}
