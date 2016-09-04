package stores;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by astrocaribe on 9/2/16.
 */
public class MockDataStoreTests {

    MockDataStore subject;

    @Before
    public void initialize() { subject = new MockDataStore(); }

    @Test
    public void implementDataStore() { assertThat(DataStore.class).isAssignableFrom(MockDataStore.class); }

    @Test
    public void getAllCardsReturnsTwoResults() {
        assertThat(subject.getAllCards()).hasSize(2);
        assertThat(subject.getAllCards()).extracting("id").contains("1001", "1002");
    }

    @Test
    public void getAllResultsWithNullFilters() {
        assertThat(subject.searchByFilters(null, null, null)).hasSize(2);
        assertThat(subject.getAllCards()).extracting("id").contains("1001", "1002");
    }

    @Test
    public void getFilteredByColorReturnsOneResult() {
        assertThat(subject.searchByFilters("white", null, null)).hasSize(1);
        assertThat(subject.searchByFilters("white", null, null)).extracting("id").containsOnly("1002");
    }

    @Test
    public void getFilteredByTypeReturnsOneResults() {
        assertThat(subject.searchByFilters(null, "creaturedog", null)).hasSize(1);
        assertThat(subject.searchByFilters(null, "creaturedog", null)).extracting("id").containsOnly("1001");
    }

    @Test
    public void getFilteredByRarityReturnsOneResults() {
        assertThat(subject.searchByFilters(null, null, "rare")).hasSize(1);
        assertThat(subject.searchByFilters(null, null, "rare")).extracting("id").containsOnly("1001");
    }

    @Test
    public void getFilteredByAllFiltersReturnsNull() {
        assertThat(subject.searchByFilters("purple", "ai", "common")).hasSize(0);
    }

}
