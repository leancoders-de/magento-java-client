package de.leancoders.magento.client.models.internal.cart;


import lombok.Getter;
import lombok.Setter;


/**
 *
 */
@Getter
@Setter
public class CartItemProductCustomOption {

    private String option_id = "";
    private String option_value = "";
    private CartItemProductCustomOptionExtensionAttributes extension_attributes = new CartItemProductCustomOptionExtensionAttributes();

}
