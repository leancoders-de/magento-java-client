package de.leancoders.magento.common.model.cart;


import com.fasterxml.jackson.annotation.JsonProperty;
import de.leancoders.magento.common.model.base.MagentoBase;
import de.leancoders.magento.common.model.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 *
 */
@Getter
@Setter
public class Cart extends MagentoBase {

    @JsonProperty("is_active")
    private boolean active;
    @JsonProperty("is_virtual")
    private boolean virtual;
    @JsonProperty("items_count")
    private int itemsCount;
    @JsonProperty("items_qty")
    private int itemsQty;

    @JsonProperty("customer")
    private Customer customer;

    @JsonProperty("items")
    private List<CartItem> items;

}
