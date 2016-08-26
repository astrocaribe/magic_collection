package dao;

import mappers.CardMapper;
import models.Card;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

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
     * @return requested card collection
     */
    @SqlQuery(
            "SELECT c.id, c.name, c.color, c.mana_cost, c.converted_mana_cost, c.type, c.text, c.expansion, c.power, c.toughness, c.rarity\n" +
                    "FROM cards c;"
    )
    Card getAllCards();

}
