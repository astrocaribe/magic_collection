package requests;

import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Result;

import static org.assertj.core.api.Assertions.assertThat;
import static play.test.Helpers.*;

/**
 * Created by astrocaribe on 8/29/16.
 */
public class GetPingResponseTest {
    Application fakeApp;

    @Before
    public void initialize() {
        fakeApp = new GuiceApplicationBuilder().configure("datastore.datasource", "mock").build();
    }

    @Test
    public void responseOk() {
        running(fakeApp, () -> {
            Result result = route(fakeRequest(GET, "/ping"));
            assertThat(result.status()).isEqualTo(OK);
        });
    }

    @Test
    public void responseMessageClassIsString() {
        running(fakeApp, () -> {
            Result result = route(fakeRequest(GET, "/ping"));
            assertThat(contentAsString(result)).isInstanceOf(String.class);
        });
    }


    @Test
    public void responseMessageReturned() {
        running(fakeApp, () -> {
            Result result = route(fakeRequest(GET, "/ping"));
            assertThat(contentAsString(result)).isEqualTo("\"This service is alive!\"");
        });
    }
}
