package de.leancoders.magento.common.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


/**
 *
 */
@Getter
@Setter
public class MagentoAttributeType {

    @JsonProperty("value")
    private String value;
    @JsonProperty("label")
    private String label;

}
