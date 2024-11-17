package de.leancoders.magento.common.model.cart;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


/**
 *
 */
@Getter
@Setter
public class CartItemProductOption {

    @JsonProperty("extension_attributes")
    private CartItemProductOptionExtensionAttributes extensionAttributes;

}
