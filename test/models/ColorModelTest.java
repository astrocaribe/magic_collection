package models;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Before;
import org.junit.Test;
import play.libs.Json;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tleblanc on 9/7/16.
 */
public class ColorModelTest {
    Color subject;

    @Before
    public void initialize() { subject = new Color(); }

    @Test
    public void color1Accessor() {
        subject.setColor1("White");
        assertThat(subject.getColor1()).isEqualTo("White");
    }

    @Test
    public void color2Accessor() {
        subject.setColor2("Black");
        assertThat(subject.getColor2()).isEqualTo("Black");
    }

    @Test
    public void color3Accessor() {
        subject.setColor3("Gray");
        assertThat(subject.getColor3()).isEqualTo("Gray");
    }

    @Test
    public void jsonFormat() {
        subject.setColor1("Gray");
        subject.setColor2("White");
        subject.setColor3("Black");

        JsonNode json = Json.toJson(subject);

        assertThat(json.get("color_1").asText()).isEqualTo("Gray");
        assertThat(json.get("color_2").asText()).isEqualTo("White");
        assertThat(json.get("color_3").asText()).isEqualTo("Black");
    }

}
