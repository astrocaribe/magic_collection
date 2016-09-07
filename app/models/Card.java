package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    private String color;
    @JsonProperty("mana_cost")
    private Integer manaCost;
    @JsonProperty("converted_mana_cost")
    private Integer convertedManaCost;
    private String type;
    @JsonProperty("sub_type")
    private String subType;
    private String text;
    private String expansion;
    private Integer power;
    private Integer toughness;
    private String rarity;
    private Integer quantity;

    // Getters
    public String getId() { return id; }
    public String getRecordType() { return this.recordType; }
    public String getName() { return this.name; }
    public String getColor() { return this.color; }
    public Integer getManaCost() { return this.manaCost; }
    public Integer getConvertedManaCost() { return this.convertedManaCost; }
    public String getType() { return this.type; }
    public String getSubType() { return this.subType; }
    public String getText() { return this.text; }
    public String getExpansion() { return this.expansion; }
    public Integer getPower() { return this.power; }
    public Integer getToughness() { return this.toughness; }
    public String getRarity() { return this.rarity; }
    public Integer getQuantity() { return this.quantity; }

    // Setters
    @JsonIgnore
    public void setId(Long id) { setId(id.toString()); }
    @JsonProperty
    public void setId(String id) { this.id = id; }

    public void setName(String name) { this.name = name; }
    public void setColor(String color) { this.color = color; }
    public void setManaCost(Integer manaCost) { this.manaCost = manaCost; }
    public void setConvertedManaCost(Integer convertedManaCost) { this.convertedManaCost = convertedManaCost; }
    public void setType(String type) { this.type = type; }
    public void setSubType(String subType) { this.subType = subType; }
    public void setText(String text) { this.text = text; }
    public void setExpansion(String expansion) { this.expansion = expansion; }
    public void setPower(Integer power) { this.power = power; }
    public void setToughness(Integer toughness) { this.toughness = toughness; }
    public void setRarity(String rarity) { this.rarity = rarity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
