package mappers;

import models.Card;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        card.setColor(r.getString("color"));
        card.setManaCost(r.getInt("mana_cost"));
        card.setConvertedManCost(r.getInt("converted_mana_cost"));
        card.setType(r.getString("type"));
        card.setText(r.getString("text"));
        card.setExpansion(r.getString("expansion"));
        card.setPower(r.getInt("power"));
        card.setToughness(r.getInt("toughness"));
        card.setRarity(r.getString("rarity"));

        return card;
    };
}