package de.leancoders.magento.client.models.internal.cart;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 */
@Setter
@Getter
public class CartItemProductBundleOption {
    private long option_id = 0;
    private int option_qty = 0;
    private List<Integer> option_selections = new ArrayList<>();
    private Map<String, Object> extension_attributes = new HashMap<>();
}
