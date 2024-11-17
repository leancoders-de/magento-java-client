package de.leancoders.magento.common.model.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryAttribute {

    @JsonProperty("attribute_code")
    private String attributeCode;
    @JsonProperty("value")
    private String value;

}
