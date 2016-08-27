package ioc;


import play.Configuration;
import play.db.DB;
import stores.DataStore;
import stores.JdbcDataStore;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by astrocaribe on 8/26/16.
 */
public class DataStoreProvider implements Provider<DataStore> {
    private final Configuration configuration;

    @Inject
    public DataStoreProvider(final Configuration configuration) { this.configuration = configuration; }

    @Override
    public DataStore get() {
        return "jdbc".equalsIgnoreCase(this.configuration.getString("datastore.datasource"))
                ? new JdbcDataStore(DB.getDataSource())
                : null;
    }
}
