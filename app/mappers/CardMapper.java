package mappers;

import models.Card;
import models.Color;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by astrocaribe on 8/25/16.
 *
 * Instruct the JDBI on how to map card results in the data access object.
 *
 * @see dao.CardDAO
 */
public class CardMapper implements ResultSetMapper<Card>{
    /**
     * Maps the JDBI result values to a card instance.
     * @param index current row of the JDBI results set
     * @param r result set that is being iterated
     * @param ctx statement context
     * @return card instance with mapped result values
     * @throws java.sql.SQLException when there is a database access error
     * @see models.Card
     */

    public Card map(int index, ResultSet r, StatementContext ctx) throws SQLException
    {
        Card card = new Card();

        card.setId(r.getLong("id"));
        card.setName(r.getString("name"));
//        card.setColors(r.getString("color"));
        card.setManaCost(r.getInt("mana_cost"));
        card.setConvertedManaCost(r.getInt("converted_mana_cost"));
        card.setType(r.getString("type"));
        card.setSubType(r.getString("sub_type"));
        card.setText(r.getString("text"));
        card.setFlavorText(r.getString("flavor_text"));
        card.setExpansion(r.getString("expansion"));
        card.setPower(r.getInt("power"));
        card.setToughness(r.getInt("toughness"));
        card.setRarity(r.getString("rarity"));
        card.setQuantity(r.getInt("quantity"));
        card.setCardNumber(r.getString("card_number"));

        // Create an object for card colors
        List<Color> colors = new ArrayList<>();
        Color cardColors = new Color();
        cardColors.setColor1(r.getString("color_1"));
        cardColors.setColor2(r.getString("color_2"));
        cardColors.setColor3(r.getString("color_3"));
        colors.add(cardColors);

        card.setColors(colors);

        return card;
    }
}
