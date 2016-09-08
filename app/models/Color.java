package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by tleblanc on 9/7/16.
 */
public class Color {

    // Create class entities
//    private String id;
//    @JsonProperty("card_id")
//    private Double cardId;
    @JsonProperty("color_1")
    private String color1;
    @JsonProperty("color_2")
    private String color2;
    @JsonProperty("color_3")
    private String color3;



    // Getters
//    public String getId() { return id; }
//    public Double getCardId() { return cardId; }
    public String getColor1() { return color1; }
    public String getColor2() { return color2; }
    public String getColor3() { return color3; }

    // Setters
//    @JsonProperty
//    public void setId(String id) { this.id = id; }
//    @JsonIgnore
//    public void setCardId(Long id) { setId(id.toString()); }

//    public void setCardId(Double cardId) { this.cardId = cardId; }
    public void setColor1(String color1) { this.color1 = color1; }
    public void setColor2(String color2) { this.color2 = color2; }
    public void setColor3(String color3) { this.color3 = color3; }
}
