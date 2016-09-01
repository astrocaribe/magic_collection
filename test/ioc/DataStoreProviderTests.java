package ioc;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import play.Configuration;
import play.db.DB;
import stores.MockDataStore;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * Created by tleblanc on 9/1/16.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(DB.class)
public class DataStoreProviderTests {

    @Before
    public void initialize() {
        PowerMockito.mockStatic(DB.class);
        PowerMockito.when(DB.getDataSource()).thenReturn(Mockito.mock(DataSource.class));
    }

    @Test
    public void testWhenDataStoreIsConfigureForJdbc_ReturnsJdbcDataStore() {
        final SoftAssertions softly = new SoftAssertions();

        for (final String val : Arrays.asList("jdbc", "Jdbc", "JDBC")) {
            final DataStoreProvider provider = getProvider(val);
            softly.assertThat(provider.get()).isInstanceOf(MockDataStore.class);
        }

        softly.assertAll();
    }

    @Test
    public void testWhenDataStoreIsConfigureForMock_ReturnsMockDataStore() {
        final SoftAssertions softly = new SoftAssertions();

        for (final String val : Arrays.asList("mock", "Mock", "MOCK")) {
            final DataStoreProvider provider = getProvider(val);
            softly.assertThat(provider.get()).isInstanceOf(MockDataStore.class);
        }

        softly.assertAll();
    }

    @Test
    public void testWhenDataStoreIsConfigureForInvalidValues_ReturnsMockDataStore() {
        final SoftAssertions softly = new SoftAssertions();

        for (final String val : Arrays.asList("junk", "trunk", "dresser")) {
            final DataStoreProvider provider = getProvider(val);
            softly.assertThat(provider.get()).isInstanceOf(MockDataStore.class);
        }

        softly.assertAll();
    }

    @Test
    public void testWhenDataStoreIsNotConfigured_ReturnsMockDataStore() {
        final SoftAssertions softly = new SoftAssertions();

        for (final String val : Arrays.asList("", null)) {
            final DataStoreProvider provider = getProvider(val);
            softly.assertThat(provider.get()).isInstanceOf(MockDataStore.class);
        }

        softly.assertAll();
    }


    private DataStoreProvider getProvider(final String dataSource) {
        final Configuration mockConfiguration = Mockito.mock(Configuration.class);
        if (dataSource != null) Mockito.when(mockConfiguration.getString("datasource.datasource")).thenReturn(dataSource);

        return new DataStoreProvider(mockConfiguration);
    }
}
