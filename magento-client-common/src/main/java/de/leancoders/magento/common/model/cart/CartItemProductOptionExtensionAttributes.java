package de.leancoders.magento.common.model.cart;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 *
 */
@Getter
@Setter
public class CartItemProductOptionExtensionAttributes {

    @JsonProperty("custom_options")
    private CartItemProductCustomOption customOption;
    @JsonProperty("downloadable_option")
    private CartItemProductDownloadableOption downloadableOption;
    @JsonProperty("giftcard_item_option")
    private GiftCardItemOption giftcardItemOption;

    @JsonProperty("bundle_options")
    private List<CartItemProductBundleOption> bundleOptions;
    @JsonProperty("configurable_item_options")
    private List<CartItemProductConfigurableItemOption> cartItemProductConfigurableItemOptions;

}
