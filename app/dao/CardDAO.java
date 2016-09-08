package dao;

import mappers.CardMapper;
import models.Card;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

import java.util.List;

/**
 * Created by astrocaribe on 8/25/16.
 *
 * Data access object for card resources using JDBI.
 *
 * <p>Uses CardMapper to handle the SQL results.</p>
 *
 * @see mappers.CardMapper
 */

@UseStringTemplate3StatementLocator
@RegisterMapper(CardMapper.class)

// Enable transaction support because the update methods are all part of a single atomic operation
public interface CardDAO extends Transactional<CardDAO> {
    /**
     * Returns the entire card collection
     * @return requested {@Link List<Card>} collection
     */
    @SqlQuery(
            "SELECT c.id, c.name, c.mana_cost, c.converted_mana_cost, c.type, c.sub_type, c.text, c.flavor_text, " +
            "       c.expansion, c.power, c.toughness, c.rarity, c.quantity, c.card_number, cc.color_1, cc.color_2, " +
            "       cc.color_3\n" +
            "FROM cards c\n" +
            "JOIN card_colors cc\n" +
            "WHERE cc.card_id = c.id;"
    )
    List<Card> getAllCards();



    /**
     * Returns a filtered card collection
     * @return filtered {@Link List<Card>} collection
     */
    @SqlQuery(
            "SELECT c.id, c.name, c.mana_cost, c.converted_mana_cost, c.type, c.sub_type, c.text, c.flavor_text, " +
            "       c.expansion, c.power, c.toughness, c.rarity, c.quantity, c.card_number, cc.color_1, cc.color_2, " +
            "       cc.color_3\n" +
            "FROM cards c\n" +
            "JOIN card_colors cc\n" +
            "WHERE cc.card_id = c.id\n" +
            "AND (:color IS NULL OR cc.color_1 = :color OR cc.color_2 = :color OR cc.color_3 = :color)\n" +
            "AND (:type IS NULL OR c.type = :type)\n" +
            "AND (:rarity IS NULL OR c.rarity = :rarity);"
    )
    List<Card> searchByFilters(@Bind("color") final String color,
                               @Bind("type") final String type,
                               @Bind("rarity") final String rarity);
}
