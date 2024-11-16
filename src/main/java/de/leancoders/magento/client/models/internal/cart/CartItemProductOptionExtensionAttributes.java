package de.leancoders.magento.client.models.internal.cart;


import de.leancoders.magento.client.models.internal.ConfigurableItemOption;
import de.leancoders.magento.client.models.internal.GiftCardItemOption;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
@Getter
@Setter
public class CartItemProductOptionExtensionAttributes {

    private CartItemProductCustomOption custom_options = new CartItemProductCustomOption();
    private List<CartItemProductBundleOption> bundle_options = new ArrayList<>();
    private CartItemProductDownloadableOption downloadable_option = new CartItemProductDownloadableOption();
    private GiftCardItemOption giftcard_item_option = new GiftCardItemOption();
    private List<ConfigurableItemOption> configurable_item_options = new ArrayList<>();
}
