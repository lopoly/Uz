
package ua.lopoly.uz;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
    "title",
    "letter",
    "places"
})
public class Type {

    @JsonProperty("title")
    private String title;
    @JsonProperty("letter")
    private String letter;
    @JsonProperty("places")
    private Integer places;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The letter
     */
    @JsonProperty("letter")
    public String getLetter() {
        return letter;
    }

    /**
     * 
     * @param letter
     *     The letter
     */
    @JsonProperty("letter")
    public void setLetter(String letter) {
        this.letter = letter;
    }

    /**
     * 
     * @return
     *     The places
     */
    @JsonProperty("places")
    public Integer getPlaces() {
        return places;
    }

    /**
     * 
     * @param places
     *     The places
     */
    @JsonProperty("places")
    public void setPlaces(Integer places) {
        this.places = places;
    }

    @Override
    public String toString() {
        return "Type{" +
                "title='" + title + '\'' +
                ", letter='" + letter + '\'' +
                ", places=" + places +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
