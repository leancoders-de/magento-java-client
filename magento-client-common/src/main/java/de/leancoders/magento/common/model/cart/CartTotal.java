package de.leancoders.magento.common.model.cart;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


/**
 *
 */
@Getter
@Setter
public class CartTotal {

    @JsonProperty("grand_total")
    private BigDecimal grandTotal;
    @JsonProperty("base_grand_total")
    private BigDecimal baseGrandTotal;
    @JsonProperty("subtotal")
    private BigDecimal subtotal;
    @JsonProperty("base_subtotal")
    private BigDecimal baseSubtotal;
    @JsonProperty("discount_amount")
    private BigDecimal discountAmount;
    @JsonProperty("base_discount_amount")
    private BigDecimal baseDiscountAmount;
    @JsonProperty("subtotal_with_discount")
    private BigDecimal subtotalWithDiscount;
    @JsonProperty( "base_subtotal_with_discount")
    private BigDecimal baseSubtotalWithDiscount;
    @JsonProperty("shipping_amount")
    private BigDecimal shippingAmount;
    @JsonProperty( "base_shipping_amount")
    private BigDecimal baseShippingAmount;
    @JsonProperty( "shipping_discount_amount")
    private BigDecimal shippingDiscountAmount;
    @JsonProperty( "base_shipping_discount_amount")
    private BigDecimal baseShippingDiscountAmount;
    @JsonProperty( "tax_amount")
    private BigDecimal taxAmount;
    @JsonProperty( "base_tax_amount")
    private BigDecimal baseTaxAmount;
    @JsonProperty( "weee_tax_applied_amount")
    private BigDecimal weeeTaxAppliedAmount;
    @JsonProperty("shipping_tax_amount")
    private BigDecimal shippingTaxAmount;
    @JsonProperty( "base_shipping_tax_amount")
    private BigDecimal baseShippingTaxAmount;
    @JsonProperty("subtotal_incl_tax")
    private BigDecimal subtotalInclTax;
    @JsonProperty( "base_subtotal_incl_tax")
    private BigDecimal baseSubtotalInclTax;
    @JsonProperty( "shipping_incl_tax")
    private BigDecimal shippingInclTax;
    @JsonProperty( "base_shipping_incl_tax")
    private BigDecimal baseShippingInclTax;
    @JsonProperty( "base_currency_code")
    private String baseCurrencyCode;
    @JsonProperty( "quote_currency_code")
    private String quoteCurrencyCode;
    @JsonProperty( "coupon_code")
    private String couponCode;
    @JsonProperty( "items_qty")
    private int itemsQty;

    @JsonProperty( "items")
    private List<CartTotalItem> items;
    @JsonProperty( "total_segments")
    private List<CartTotalSegment> totalSegments;
    @JsonProperty( "extension_attributes")
    private CartTotalExtensionAttributes extensionAttributes;

}
