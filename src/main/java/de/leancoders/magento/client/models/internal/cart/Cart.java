package de.leancoders.magento.client.models.internal.cart;


import de.leancoders.magento.client.models.internal.MagentoBase;
import de.leancoders.magento.client.models.internal.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 *
 */
@Getter
@Setter
public class Cart extends MagentoBase {

    private boolean is_active = true;
    private boolean is_virtual = false;
    private int items_count = 0;
    private int items_qty = 0;

    private Customer customer;

    private List<CartItem> items;

}
