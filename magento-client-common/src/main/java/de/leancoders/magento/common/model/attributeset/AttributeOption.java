package de.leancoders.magento.common.model.attributeset;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AttributeOption {

    @JsonProperty("label")
    private String label;
    @JsonProperty("value")
    private String value;


}
