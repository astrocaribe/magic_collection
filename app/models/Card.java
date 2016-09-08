package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by astrocaribe on 8/25/16.
 *
 * Manages data representing a card resource.
 *
 * <p>It should be used by the DataStore Library to provide a
 * common data format to the application controller to build JSON
 * responses.</p>
 *
 * @see stores.DataStore
 * @see controllers.CardController
 */
public class Card {
    // Create class entities
    private String id;
    private final String recordType = "card";
    private String name;
    private List<Color> colors;
    @JsonProperty("mana_cost")
    private Integer manaCost;
    @JsonProperty("converted_mana_cost")
    private Integer convertedManaCost;
    private String type;
    @JsonProperty("sub_type")
    private String subType;
    private String text;
    @JsonProperty("flavor_text")
    private String flavorText;
    private String expansion;
    private Integer power;
    private Integer toughness;
    private String rarity;
    private Integer quantity;
    @JsonProperty("card_number")
    private String cardNumber;

    // Getters
    public String getId() { return id; }
    public String getRecordType() { return this.recordType; }
    public String getName() { return this.name; }
    public List<Color> getColors() { return this.colors; }
    public Integer getManaCost() { return this.manaCost; }
    public Integer getConvertedManaCost() { return this.convertedManaCost; }
    public String getType() { return this.type; }
    public String getSubType() { return this.subType; }
    public String getText() { return this.text; }
    public String getFlavorText() { return this.flavorText; }
    public String getExpansion() { return this.expansion; }
    public Integer getPower() { return this.power; }
    public Integer getToughness() { return this.toughness; }
    public String getRarity() { return this.rarity; }
    public Integer getQuantity() { return this.quantity; }
    public String getCardNumber() { return this.cardNumber; }

    // Setters
    @JsonIgnore
    public void setId(Long id) { setId(id.toString()); }
    @JsonProperty
    public void setId(String id) { this.id = id; }

    public void setName(String name) { this.name = name; }
    public void setColors(List<Color> colors) { this.colors = colors; }
    public void setManaCost(Integer manaCost) { this.manaCost = manaCost; }
    public void setConvertedManaCost(Integer convertedManaCost) { this.convertedManaCost = convertedManaCost; }
    public void setType(String type) { this.type = type; }
    public void setSubType(String subType) { this.subType = subType; }
    public void setText(String text) { this.text = text; }
    public void setFlavorText(String flavorText) { this.flavorText = flavorText; }
    public void setExpansion(String expansion) { this.expansion = expansion; }
    public void setPower(Integer power) { this.power = power; }
    public void setToughness(Integer toughness) { this.toughness = toughness; }
    public void setRarity(String rarity) { this.rarity = rarity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setCardNumber(String cardNumber ) { this.cardNumber = cardNumber; }
}
