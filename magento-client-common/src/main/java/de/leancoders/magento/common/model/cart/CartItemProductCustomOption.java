package de.leancoders.magento.common.model.cart;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


/**
 *
 */
@Getter
@Setter
public class CartItemProductCustomOption {

    @JsonProperty("option_id")
    private String optionId;
    @JsonProperty("option_value")
    private String optionValue;

    @JsonProperty("extension_attributes")
    private CartItemProductCustomOptionExtensionAttributes extensionAttributes;

}
