package stores;

import dao.CardDAO;
import models.Card;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.skife.jdbi.v2.DBI;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by astrocaribe on 9/2/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(JdbcDataStore.class)
public class JdbcDataStoreTests {

    JdbcDataStore subject;
    DataSource mockDS;
    CardDAO mockDao;
    DBI mockDBI;

    @Before
    public void initialize() throws Exception {
        mockDS = Mockito.mock(DataSource.class);
        mockDao = Mockito.mock(CardDAO.class);
        mockDBI = Mockito.mock(DBI.class);

        PowerMockito.whenNew(DBI.class).withAnyArguments().thenReturn(mockDBI);
        Mockito.when(mockDBI.onDemand(CardDAO.class)).thenReturn(mockDao);

        subject = new JdbcDataStore(mockDS);
    }

    @Test
    public void implementDataStore() { assertThat(subject).isInstanceOf(DataStore.class); }

    @Test
    public void getAllCardsReturnsDaoResults() {
        List<Card> mockList = new ArrayList<>();
        Mockito.when(mockDao.getAllCards()).thenReturn(mockList);
        assertThat(subject.getAllCards()).isEqualTo(mockList);
        Mockito.verify(mockDao).getAllCards();
    }

    @Test
    public void searchByFilterWithNullFilters() {
        List<Card> mockList = new ArrayList<>();
        Mockito.when(mockDao.searchByFilters(null, null, null)).thenReturn(mockList);
        assertThat(subject.searchByFilters(null, null, null)).isEqualTo(mockList);
        Mockito.verify(mockDao).searchByFilters(null, null, null);
    }

    @Test
    public void getFilteredByAllFiltersReturnsDaoResults() {
        List<Card> mockList = new ArrayList<>();
        Mockito.when(mockDao.searchByFilters("red", "monster", "rare")).thenReturn(mockList);
        assertThat(subject.searchByFilters("red", "monster", "rare")).isEqualTo(mockList);
        Mockito.verify(mockDao).searchByFilters("red", "monster", "rare");
    }

    @Test
    public void getFilteredByColorReturnsDaoResult() {
        List<Card> mockList = new ArrayList<>();
        Mockito.when(mockDao.searchByFilters("red", null, null)).thenReturn(mockList);
        assertThat(subject.searchByFilters("red", null, null)).isEqualTo(mockList);
        Mockito.verify(mockDao).searchByFilters("red", null, null);
    }

    @Test
    public void getFilteredByTypeReturnsDaoResults() {
        List<Card> mockList = new ArrayList<>();
        Mockito.when(mockDao.searchByFilters(null, "monster", null)).thenReturn(mockList);
        assertThat(subject.searchByFilters(null, "monster", null)).isEqualTo(mockList);
        Mockito.verify(mockDao).searchByFilters(null, "monster", null);
    }

    @Test
    public void getFilteredByRarityReturnsDaoResults() {
        List<Card> mockList = new ArrayList<>();
        Mockito.when(mockDao.searchByFilters(null, null, "rare")).thenReturn(mockList);
        assertThat(subject.searchByFilters(null, null, "rare")).isEqualTo(mockList);
        Mockito.verify(mockDao).searchByFilters(null, null, "rare");
    }
}
