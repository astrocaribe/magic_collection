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
    public JdbcDataStore(final DataSource dataSource) {
        this.dbi = new DBI("jdbc");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Card> getAllCards() {
        final CardDAO dao = this.dbi.onDemand(CardDAO.class);
        final List<Card> cards = (List<Card>) dao.getAllCards();
        return cards;
    }
}
