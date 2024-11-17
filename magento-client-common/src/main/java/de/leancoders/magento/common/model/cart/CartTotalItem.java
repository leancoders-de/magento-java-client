package de.leancoders.magento.common.model.cart;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;


/**
 *
 */
@Getter
@Setter
public class CartTotalItem {

    @JsonProperty("item_id")
    private long itemId;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("base_price")
    private BigDecimal basePrice;
    @JsonProperty("qty")
    private int qty;
    @JsonProperty("row_total")
    private BigDecimal rowTotal;
    @JsonProperty("base_row_total")
    private BigDecimal baseRowTotal;
    @JsonProperty("row_total_with_discount")
    private BigDecimal rowTotalWithDiscount;
    @JsonProperty("tax_amount")
    private BigDecimal taxAmount;
    @JsonProperty("base_tax_amount")
    private BigDecimal baseTaxAmount;
    @JsonProperty("tax_percent")
    private BigDecimal taxPercent;
    @JsonProperty("discount_amount")
    private BigDecimal discountAmount;
    @JsonProperty("base_discount_amount")
    private BigDecimal baseDiscountAmount;
    @JsonProperty("discount_percent")
    private BigDecimal discountPercent;
    @JsonProperty("price_incl_tax")
    private BigDecimal priceInclTax;
    @JsonProperty("base_price_incl_tax")
    private BigDecimal basePriceInclTax;
    @JsonProperty("row_total_incl_tax")
    private BigDecimal rowTotalInclTax;
    @JsonProperty("base_row_total_incl_tax")
    private BigDecimal baseRowTotalInclTax;
    @JsonProperty("weee_tax_applied_amount")
    private BigDecimal weeeTaxAppliedAmount;
    @JsonProperty("weee_tax_applied")
    private String weeeTaxApplied;
    @JsonProperty("name")
    private String name;
    @JsonProperty("options")
    private String options;

    @JsonProperty("extension_attributes")
    private Map<String, Object> extensionAttributes;

}