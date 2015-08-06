
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
    "station_id",
    "station",
    "date",
    "src_date"
})
public class Till {

    @JsonProperty("station_id")
    private String stationId;
    @JsonProperty("station")
    private String station;
    @JsonProperty("date")
    private Integer date;
    @JsonProperty("src_date")
    private String srcDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The stationId
     */
    @JsonProperty("station_id")
    public String getStationId() {
        return stationId;
    }

    /**
     * 
     * @param stationId
     *     The station_id
     */
    @JsonProperty("station_id")
    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    /**
     * 
     * @return
     *     The station
     */
    @JsonProperty("station")
    public String getStation() {
        return station;
    }

    /**
     * 
     * @param station
     *     The station
     */
    @JsonProperty("station")
    public void setStation(String station) {
        this.station = station;
    }

    /**
     * 
     * @return
     *     The date
     */
    @JsonProperty("date")
    public Integer getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The date
     */
    @JsonProperty("date")
    public void setDate(Integer date) {
        this.date = date;
    }

    /**
     * 
     * @return
     *     The srcDate
     */
    @JsonProperty("src_date")
    public String getSrcDate() {
        return srcDate;
    }

    /**
     * 
     * @param srcDate
     *     The src_date
     */
    @JsonProperty("src_date")
    public void setSrcDate(String srcDate) {
        this.srcDate = srcDate;
    }

    @Override
    public String toString() {
        return "Till{" +
                "stationId='" + stationId + '\'' +
                ", station='" + station + '\'' +
                ", date=" + date +
                ", srcDate='" + srcDate + '\'' +
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
