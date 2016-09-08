package mappers;

import models.Card;

import models.Color;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
/**
 * Created by astrocaribe on 8/28/16.
 */
public class CardMapperTests {
    private static Card expectedCard;
    private static ResultSet mockResultsSet;
    private static CardMapper mapper;

    @BeforeClass
    public static void initClass() throws SQLException {
        expectedCard = createMockCard();
        mockResultsSet = createMockResultSet(expectedCard);
        mapper = new CardMapper();
    }

    private static Card createMockCard() {
        final Card card = new Card();

        // Create an object for card colors
        List<Color> colors = new ArrayList<>();
        Color cardColors = new Color();
        cardColors.setColor1("Black");
        colors.add(cardColors);

        card.setId("1000");
        card.setName("Vagenda of Manocide");
        card.setColors(colors);
        card.setType("Enchantment");
        card.setText("Shroud. This permanent or player cannot be in the target of spells or abilities.");
        card.setFlavorText("It is going to be yuggge!");
        card.setManaCost(6);
        card.setConvertedManaCost(10);
        card.setExpansion("RealLife");
        card.setPower(5);
        card.setToughness(5);
        card.setRarity("Rare");
        card.setQuantity(1);
        card.setCardNumber("1000");

        return card;
    }

    private static ResultSet createMockResultSet(final Card resultCard) throws SQLException {
        final ResultSet resultSet = Mockito.mock(ResultSet.class);

        final Long cardIdLong = Long.parseLong(expectedCard.getId());
        Mockito.when(resultSet.getLong("id")).thenReturn(cardIdLong);

        setupStringFieldMap(resultSet, "name", resultCard.getName());
//        setupStringFieldMap(resultSet, "color", resultCard.getColors());
        setupStringFieldMap(resultSet, "type", resultCard.getType());
        setupStringFieldMap(resultSet, "text", resultCard.getText());
        setupStringFieldMap(resultSet, "flavor_text", resultCard.getFlavorText());
        setupIntegerFieldMap(resultSet, "mana_cost", resultCard.getManaCost());
        setupIntegerFieldMap(resultSet, "converted_mana_cost", resultCard.getConvertedManaCost());
        setupStringFieldMap(resultSet, "expansion", resultCard.getExpansion());
        setupIntegerFieldMap(resultSet, "power", resultCard.getPower());
        setupIntegerFieldMap(resultSet, "toughness", resultCard.getToughness());
        setupStringFieldMap(resultSet, "rarity", resultCard.getRarity());
        setupIntegerFieldMap(resultSet, "quantity", resultCard.getQuantity());
        setupStringFieldMap(resultSet, "card_number", resultCard.getCardNumber());

        final Color color = resultCard.getColors().get(0);
        setupStringFieldMap(resultSet, "color_1", color.getColor1());

        return resultSet;

    }

    private static void setupIntegerFieldMap(final ResultSet resultSet,
                                             final String fieldName,
                                             final Integer value) throws SQLException {
        Mockito.when(resultSet.getInt(fieldName)).thenReturn(value);
    }

    private static void setupStringFieldMap(final ResultSet resultSet,
                                             final String fieldName,
                                             final String value) throws SQLException {
        Mockito.when(resultSet.getString(fieldName)).thenReturn(value);
    }


    @Test
    public void testIdMapsCorrectly() { assertThatFieldMapsCorrectly(Card::getId); }

    @Test
    public void testNameMapsCorrectly() { assertThatFieldMapsCorrectly(Card::getName); }

    @Test
    public void testColorsMapsCorrectly() { assertThatListMapsCorrectly(Card::getColors); }

    @Test
    public void testTypeMapsCorrectly() { assertThatFieldMapsCorrectly(Card::getType); }

    @Test
    public void testTextMapsCorrectly() { assertThatFieldMapsCorrectly(Card::getText); }

    @Test
    public void testFlavorTextMapsCorrectly() { assertThatFieldMapsCorrectly(Card::getFlavorText); }

    @Test
    public void testManaCostMapsCorrectly() { assertThatFieldMapsCorrectly(Card::getManaCost); }

    @Test
    public void testConvertedManaCostMapsCorrectly() { assertThatFieldMapsCorrectly(Card::getConvertedManaCost); }

    @Test
    public void testExpansionMapsCorrectly() { assertThatFieldMapsCorrectly(Card::getExpansion); }

    @Test
    public void testPowerMapsCorrectly() { assertThatFieldMapsCorrectly(Card::getPower); }

    @Test
    public void testToughnessMapsCorrectly() { assertThatFieldMapsCorrectly(Card::getToughness); }

    @Test
    public void testRarityMapsCorrectly() { assertThatFieldMapsCorrectly(Card::getRarity); }

    @Test
    public void testQuantityMapsCorrectly() { assertThatFieldMapsCorrectly(Card::getQuantity); }

    @Test
    public void testCardNumberMapsCorrectly() { assertThatFieldMapsCorrectly(Card::getCardNumber); }



    private void assertThatFieldMapsCorrectly(final Function<Card, ?> valueSupplier) {
        final Card mapped;
        try {
            mapped = mapper.map(0, mockResultsSet, null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        final Object expectedValue = valueSupplier.apply(expectedCard);
        final Object actualValue = valueSupplier.apply(mapped);
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    private void assertThatListMapsCorrectly(Function<Card, List<?>> valueSupplier) {
        final Card mapped;
        try {
            mapped = mapper.map(0, mockResultsSet, null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        final List<?> expectedValue = valueSupplier.apply(expectedCard);
        final List<?> actualValue = valueSupplier.apply(mapped);
        assertThat(actualValue).usingFieldByFieldElementComparator().containsOnlyElementsOf(expectedValue);
    }

}
