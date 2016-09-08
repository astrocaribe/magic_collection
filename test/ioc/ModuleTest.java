package ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import play.Configuration;
import stores.DataStore;

import static org.assertj.core.api.Assertions.fail;

/**
 * Created by tleblanc on 9/1/16.
 */
public class ModuleTest {
    private Configuration mockConfiguration;

    @Before
    public void initialize() { mockConfiguration = Mockito.mock(Configuration.class); }

    @Test
    public void testDataStoreProviderIsBoundCorrectly() {
        try {
            final Injector injector = getInjector();
            injector.getProvider(DataStore.class);
        } catch (Throwable t) {
            fail("Binding not found", t);
        }
    }


    private Injector getInjector() {
        final AbstractModule testApplicationModule = new AbstractModule() {
            @Override
            protected void configure() {
                bind(Configuration.class).toInstance(mockConfiguration);
            }
        };

        return Guice.createInjector(testApplicationModule, new Module());
    }
}
