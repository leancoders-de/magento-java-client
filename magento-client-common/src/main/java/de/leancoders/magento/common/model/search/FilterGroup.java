package de.leancoders.magento.common.model.search;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


/**
 *
 */
@Data
public class FilterGroup {
    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private String value;
    @JsonProperty("condition_type")
    private String conditionType;
}
