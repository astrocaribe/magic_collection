package ioc;

import com.google.inject.AbstractModule;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import stores.DataStore;

/**
 * Created by astrocaribe on 8/26/16.
 *
 * Binders for the ILoggerFactory --> LoggerFactory and
 * DataStore --> DataStoreProvider (JdbcDataStore)
 */
public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(ILoggerFactory.class).toProvider(LoggerFactory::getILoggerFactory);
        bind(DataStore.class).toProvider(DataStoreProvider.class);
    }
}
