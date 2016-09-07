package mappers;

import models.Card;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        card.setId("1000");
        card.setName("Vagenda of Manocide");
        card.setColor("Black");
        card.setType("Enchantment");
        card.setText("Shroud. This permanent or player cannot be in the target of spells or abilities.");
        card.setManaCost(6);
        card.setConvertedManaCost(10);
        card.setExpansion("RealLife");
        card.setPower(5);
        card.setToughness(5);
        card.setRarity("Rare");
        card.setQuantity(1);

        return card;
    }

    private static ResultSet createMockResultSet(final Card resultCard) throws SQLException {
        final ResultSet resultSet = Mockito.mock(ResultSet.class);

        final Long cardIdLong = Long.parseLong(expectedCard.getId());
        Mockito.when(resultSet.getLong("id")).thenReturn(cardIdLong);

        setupStringFieldMap(resultSet, "name", resultCard.getName());
        setupStringFieldMap(resultSet, "color", resultCard.getColor());
        setupStringFieldMap(resultSet, "type", resultCard.getType());
        setupStringFieldMap(resultSet, "text", resultCard.getText());
        setupIntegerFieldMap(resultSet, "mana_cost", resultCard.getManaCost());
        setupIntegerFieldMap(resultSet, "converted_mana_cost", resultCard.getConvertedManaCost());
        setupStringFieldMap(resultSet, "expansion", resultCard.getExpansion());
        setupIntegerFieldMap(resultSet, "power", resultCard.getPower());
        setupIntegerFieldMap(resultSet, "toughness", resultCard.getToughness());
        setupStringFieldMap(resultSet, "rarity", resultCard.getRarity());
        setupIntegerFieldMap(resultSet, "quantity", resultCard.getQuantity());

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
    public void testColorMapsCorrectly() { assertThatFieldMapsCorrectly(Card::getColor); }

    @Test
    public void testTypeMapsCorrectly() { assertThatFieldMapsCorrectly(Card::getType); }

    @Test
    public void testTextMapsCorrectly() { assertThatFieldMapsCorrectly(Card::getText); }

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

}
