package de.leancoders.magento.common.model.cart;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;


/**
 *
 */
@Setter
@Getter
public class CartItemProductBundleOption {
    @JsonProperty("option_id")
    private long optionId;
    @JsonProperty("option_qty")
    private int optionQty;
    @JsonProperty(value = "option_selections")
    private List<Integer> optionSelections;
    @JsonProperty("extension_attributes")
    private Map<String, Object> extensionAttributes;
}
