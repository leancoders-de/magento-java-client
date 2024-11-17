package de.leancoders.magento.common.model.cart;


import com.fasterxml.jackson.annotation.JsonProperty;
import de.leancoders.magento.common.model.enums.EProductType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;


/**
 *
 */
@Setter
@Getter
public class CartItem {
    @JsonProperty("item_id")
    private int itemId;
    @JsonProperty("sku")
    private String sku;
    @JsonProperty("qty")
    private int qty;
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("product_type")
    private EProductType productType;
    @JsonProperty("quote_id")
    private String quoteId;

    @JsonProperty("product_option")
    private CartItemProductOption productOption;
    @JsonProperty("extension_attributes")
    private Map<String, Object> extensionAttributes;
}


