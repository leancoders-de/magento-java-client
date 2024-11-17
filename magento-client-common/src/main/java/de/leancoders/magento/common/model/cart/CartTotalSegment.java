package de.leancoders.magento.common.model.cart;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


/**
 *
 */
@Getter
@Setter
public class CartTotalSegment {

    @JsonProperty("code")
    private String code;
    @JsonProperty("title")
    private String title;
    @JsonProperty("value")
    private BigDecimal value;
    @JsonProperty("area")
    private String area;

    @JsonProperty("extension_attributes")
    private CartTotalSegmentExtensionAttributes extensionAttributes;

}
