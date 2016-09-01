package requests;

import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Result;
import stores.DataStore;
import stores.MockDataStore;

import static org.assertj.core.api.Assertions.assertThat;
import static play.inject.Bindings.bind;
import static play.test.Helpers.*;

/**
 * Created by astrocaribe on 8/29/16.
 */
public class GetPingResponseTest {
    Application testApp;

    @Before
    public void initialize() {
        testApp = new GuiceApplicationBuilder()
                .overrides(bind(DataStore.class).to(MockDataStore.class))
                .build();
    }

    @Test
    public void responseOk() {
        running(testApp, () -> {
            Result result = route(fakeRequest(GET, "/ping"));
            assertThat(result.status()).isEqualTo(OK);
        });
    }

    @Test
    public void responseMessageClassIsString() {
        running(testApp, () -> {
            Result result = route(fakeRequest(GET, "/ping"));
            assertThat(contentAsString(result)).isInstanceOf(String.class);
        });
    }


    @Test
    public void responseMessageReturned() {
        running(testApp, () -> {
            Result result = route(fakeRequest(GET, "/ping"));
            assertThat(contentAsString(result)).isEqualTo("\"This service is alive!\"");
        });
    }
}
