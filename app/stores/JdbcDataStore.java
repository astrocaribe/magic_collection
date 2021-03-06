package stores;

import dao.CardDAO;
import models.Card;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.IDBI;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by astrocaribe on 8/25/16.
 *
 * SQL datastore to perform SQl queries against the database.
 *
 * <p>A CardDAO instance is used to to execute queries. </p>
 *
 * @see dao.CardDAO
 */
public class JdbcDataStore implements DataStore {
    /**
     * Data access object to perform the SQL query
     */
    private IDBI dbi;

    /**
     * Constructor accepting a data source.
     * @param dataSource SQL data connection
     */
    public JdbcDataStore(final DataSource dataSource) { this.dbi = new DBI(dataSource); }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Card> getAllCards() {
        final CardDAO dao = this.dbi.onDemand(CardDAO.class);
        final List<Card> cards = dao.getAllCards();
        return cards;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Card> searchByFilters(String colorFilter, String typeFilter, String rarityFilter) {
        final CardDAO dao = this.dbi.onDemand(CardDAO.class);
        final List<Card> cards = dao.searchByFilters(colorFilter, typeFilter, rarityFilter);
        return cards;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Card searchById(String cardId) {
        final CardDAO dao = this.dbi.onDemand(CardDAO.class);

        return dao.searchById(cardId);
    }


}
