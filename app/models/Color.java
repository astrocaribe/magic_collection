package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by tleblanc on 9/7/16.
 */
public class Color {

    // Create class entities
    @JsonProperty("color_1")
    private String color1;
    @JsonProperty("color_2")
    private String color2;
    @JsonProperty("color_3")
    private String color3;

    // Getters
    public String getColor1() { return color1; }
    public String getColor2() { return color2; }
    public String getColor3() { return color3; }

    // Setters
    public void setColor1(String color1) { this.color1 = color1; }
    public void setColor2(String color2) { this.color2 = color2; }
    public void setColor3(String color3) { this.color3 = color3; }
}
