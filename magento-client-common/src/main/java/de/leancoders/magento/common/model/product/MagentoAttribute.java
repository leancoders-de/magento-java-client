package de.leancoders.magento.common.model.product;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Data
public class MagentoAttribute {

    @JsonProperty(value = "attribute_code")
    private String attributeCode;
    @JsonProperty(value = "value")
    private Object value;

}
