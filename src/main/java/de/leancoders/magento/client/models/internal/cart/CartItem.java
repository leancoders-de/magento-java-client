package de.leancoders.magento.client.models.internal.cart;


import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


/**
 *
 */
@Setter
@Getter
public class CartItem {
    private int item_id;
    private String sku;
    private int qty;
    private String name;
    private double price;
    private String product_type;
    private String quote_id;

    private CartItemProductOption product_option = new CartItemProductOption();
    private Map<String, Object> extension_attributes = new HashMap<>();
}


