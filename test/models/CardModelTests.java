package models;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Before;
import org.junit.Test;
import play.libs.Json;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tleblanc on 9/1/16.
 */
public class CardModelTests {
    Card subject;

    @Before
    public void initialize() { subject = new Card(); }

    @Test
    public void typeGetter() {
        assertThat(subject.getRecordType()).isEqualTo("card");
    }

    @Test
    public void idAccessors() {
        subject.setId("1001");
        assertThat(subject.getId()).isEqualTo("1001");

        subject.setId(1002L);
        assertThat(subject.getId()).isEqualTo("1002");
    }

    @Test
    public void nameAccessor() {
        subject.setName("Chell Johnson");
        assertThat(subject.getName()).isEqualTo("Chell Johnson");
    }

    @Test
    public void colorAccessor() {
        // Create an object for card colors
        List<Color> colors = new ArrayList<>();
        Color cardColors = new Color();
        cardColors.setColor1("Beige");
        colors.add(cardColors);

        subject.setColors(colors);
        assertThat(subject.getColors().get(0).getColor1()).isEqualTo("Beige");
    }

    @Test
    public void manaCostAccessor() {
        subject.setManaCost(100);
        assertThat(subject.getManaCost()).isEqualTo(100);
    }

    @Test
    public void convertedCostAccessor() {
        subject.setConvertedManaCost(200);
        assertThat(subject.getConvertedManaCost()).isEqualTo(200);
    }

    @Test
    public void typeAccessor() {
        subject.setType("Prisoner");
        assertThat(subject.getType()).isEqualTo("Prisoner");
    }

    @Test
    public void subTypeAccessor() {
        subject.setType("Human");
        assertThat(subject.getType()).isEqualTo("Human");
    }

    @Test
    public void textAccessor() {
        subject.setText("She thinks it's cake.");
        assertThat(subject.getText()).isEqualTo("She thinks it's cake.");
    }

    @Test
    public void flavorTextAccessor() {
        subject.setFlavorText("The cake is a lie! - Chell");
        assertThat(subject.getFlavorText()).isEqualTo("The cake is a lie! - Chell");
    }

    @Test
    public void expansionAccessor() {
        subject.setExpansion("Portal");
        assertThat(subject.getExpansion()).isEqualTo("Portal");
    }

    @Test
    public void powerAccessor() {
        subject.setPower(5000);
        assertThat(subject.getPower()).isEqualTo(5000);
    }

    @Test
    public void toughnessAccessor() {
        subject.setToughness(5000);
        assertThat(subject.getToughness()).isEqualTo(5000);
    }

    @Test
    public void rarityAccessor() {
        subject.setRarity("SuperRare");
        assertThat(subject.getRarity()).isEqualTo("SuperRare");
    }

    @Test
    public void quantityAccessor() {
        subject.setQuantity(6);
        assertThat(subject.getQuantity()).isEqualTo(6);
    }

    @Test
    public void cardNumberAccessor() {
        subject.setCardNumber("10x");
        assertThat(subject.getCardNumber()).isEqualTo("10x");
    }

    @Test
    public void jsonFormat() {
        // Create an object for card colors
        List<Color> colors = new ArrayList<>();
        Color cardColors = new Color();
        cardColors.setColor1("Beige");
        colors.add(cardColors);

        subject.setId(1001L);
        subject.setName("Chell Johnson");
        subject.setColors(colors);
        subject.setManaCost(100);
        subject.setConvertedManaCost(200);
        subject.setType("Prisoner");
        subject.setSubType("Human");
        subject.setText("She though it was cake.");
        subject.setFlavorText("The cake is a lie - Chell");
        subject.setExpansion("Portal");
        subject.setPower(5000);
        subject.setToughness(5000);
        subject.setRarity("Super Rare");
        subject.setQuantity(1);
        subject.setCardNumber("100x");

        JsonNode json = Json.toJson(subject);

        assertThat(json.get("id").asText()).isEqualTo("1001");
        assertThat(json.get("name").asText()).isEqualTo("Chell Johnson");
        assertThat(json.get("colors").get(0).get("color_1").asText()).isEqualTo("Beige");
        assertThat(json.get("mana_cost").asInt()).isEqualTo(100);
        assertThat(json.get("converted_mana_cost").asInt()).isEqualTo(200);
        assertThat(json.get("type").asText()).isEqualTo("Prisoner");
        assertThat(json.get("sub_type").asText()).isEqualTo("Human");
        assertThat(json.get("text").asText()).isEqualTo("She though it was cake.");
        assertThat(json.get("flavor_text").asText()).isEqualTo("The cake is a lie - Chell");
        assertThat(json.get("expansion").asText()).isEqualTo("Portal");
        assertThat(json.get("power").asInt()).isEqualTo(5000);
        assertThat(json.get("toughness").asInt()).isEqualTo(5000);
        assertThat(json.get("rarity").asText()).isEqualTo("Super Rare");
        assertThat(json.get("quantity").asInt()).isEqualTo(1);
        assertThat(json.get("card_number").asText()).isEqualTo("100x");
    }

}
