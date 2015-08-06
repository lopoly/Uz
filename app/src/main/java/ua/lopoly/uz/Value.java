
package ua.lopoly.uz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
    "num",
    "model",
    "category",
    "from",
    "till",
    "types",
    "reserve_error"
})
public class Value {

    @JsonProperty("num")
    private String num;
    @JsonProperty("model")
    private Integer model;
    @JsonProperty("category")
    private Integer category;
    @JsonProperty("from")
    private From from;
    @JsonProperty("till")
    private Till till;
    @JsonProperty("types")
    private List<Type> types = new ArrayList<Type>();
    @JsonProperty("reserve_error")
    private String reserveError;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The num
     */
    @JsonProperty("num")
    public String getNum() {
        return num;
    }

    /**
     * 
     * @param num
     *     The num
     */
    @JsonProperty("num")
    public void setNum(String num) {
        this.num = num;
    }

    /**
     * 
     * @return
     *     The model
     */
    @JsonProperty("model")
    public Integer getModel() {
        return model;
    }

    /**
     * 
     * @param model
     *     The model
     */
    @JsonProperty("model")
    public void setModel(Integer model) {
        this.model = model;
    }

    /**
     * 
     * @return
     *     The category
     */
    @JsonProperty("category")
    public Integer getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *     The category
     */
    @JsonProperty("category")
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * 
     * @return
     *     The from
     */
    @JsonProperty("from")
    public From getFrom() {
        return from;
    }

    /**
     * 
     * @param from
     *     The from
     */
    @JsonProperty("from")
    public void setFrom(From from) {
        this.from = from;
    }

    /**
     * 
     * @return
     *     The till
     */
    @JsonProperty("till")
    public Till getTill() {
        return till;
    }

    /**
     * 
     * @param till
     *     The till
     */
    @JsonProperty("till")
    public void setTill(Till till) {
        this.till = till;
    }

    /**
     * 
     * @return
     *     The types
     */
    @JsonProperty("types")
    public List<Type> getTypes() {
        return types;
    }

    /**
     * 
     * @param types
     *     The types
     */
    @JsonProperty("types")
    public void setTypes(List<Type> types) {
        this.types = types;
    }

    /**
     * 
     * @return
     *     The reserveError
     */
    @JsonProperty("reserve_error")
    public String getReserveError() {
        return reserveError;
    }

    /**
     * 
     * @param reserveError
     *     The reserve_error
     */
    @JsonProperty("reserve_error")
    public void setReserveError(String reserveError) {
        this.reserveError = reserveError;
    }

    @Override
    public String toString() {
        return "Value{" +
                "num='" + num + '\'' +
                ", model=" + model +
                ", category=" + category +
                ", from=" + from +
                ", till=" + till +
                ", types=" + types +
                ", reserveError='" + reserveError + '\'' +
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
