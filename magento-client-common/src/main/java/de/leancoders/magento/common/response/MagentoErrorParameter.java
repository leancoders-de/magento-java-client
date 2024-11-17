package de.leancoders.magento.common.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


/**
 *
 */
@Getter
@Setter
public class MagentoErrorParameter {

    @JsonProperty("resources")
    private String resources;
    @JsonProperty("fieldName")
    private String fieldName;
    @JsonProperty("fieldValue")
    private String fieldValue;

}