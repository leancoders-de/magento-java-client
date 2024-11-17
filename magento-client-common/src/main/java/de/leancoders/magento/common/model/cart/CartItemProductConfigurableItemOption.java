package de.leancoders.magento.common.model.cart;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;


/**
 *
 */
@Getter
@Setter
public class CartItemProductConfigurableItemOption {

    @JsonProperty("option_value")
    private BigDecimal optionValue;
    @JsonProperty("option_id")
    private String optionId;

    @JsonProperty("extension_attributes")
    private Map<String, Object> extensionAttributes;
}
