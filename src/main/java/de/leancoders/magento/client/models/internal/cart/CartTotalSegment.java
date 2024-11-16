package de.leancoders.magento.client.models.internal.cart;


import lombok.Getter;
import lombok.Setter;


/**
 *
 */
@Getter
@Setter
public class CartTotalSegment {


    private String code = "";
    private String title = "";
    private double value = 0;
    private String area = "";
    private CartTotalSegmentExtensionAttributes extension_attributes = new CartTotalSegmentExtensionAttributes();

}
