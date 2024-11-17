package de.leancoders.magento.common.model.cart;


import com.fasterxml.jackson.annotation.JsonProperty;
import de.leancoders.magento.common.model.tax.TaxGrandTotalDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 *
 */
@Getter
@Setter
public class CartTotalSegmentExtensionAttributes {

    @JsonProperty("gift_cards")
    private String giftCards;
    @JsonProperty("gw_order_id")
    private String gwOrderId;
    @JsonProperty("gw_allow_gift_receipt")
    private String gwAllowGiftReceipt;
    @JsonProperty("gw_add_card")
    private String gwAddCard;
    @JsonProperty("gw_price")
    private String gwPrice;
    @JsonProperty("gw_base_price")
    private String gwBasePrice;
    @JsonProperty("gw_items_price")
    private String gwItemsPrice;
    @JsonProperty("gw_items_base_price")
    private String gwItemsBasePrice;
    @JsonProperty("gw_card_price")
    private String gwCardPrice;
    @JsonProperty("gw_card_base_price")
    private String gwCardBasePrice;
    @JsonProperty("gw_base_tax_amount")
    private String gwBaseTaxAmount;
    @JsonProperty("gw_tax_amount")
    private String gwTaxAmount;
    @JsonProperty("gw_items_base_tax_amount")
    private String gwItemsBaseTaxAmount;
    @JsonProperty("gw_items_tax_amount")
    private String gwItemsTaxAmount;
    @JsonProperty("gw_card_base_tax_amount")
    private String gwCardBaseTaxAmount;
    @JsonProperty("gw_card_tax_amount")
    private String gwCardTaxAmount;
    @JsonProperty("gw_price_incl_tax")
    private String gwPriceInclTax;
    @JsonProperty("gw_base_price_incl_tax")
    private String gwBasePriceInclTax;
    @JsonProperty("gw_card_price_incl_tax")
    private String gwCardPriceInclTax;
    @JsonProperty("gw_card_base_price_incl_tax")
    private String gwCardBasePriceInclTax;
    @JsonProperty("gw_items_price_incl_tax")
    private String gwItemsPriceInclTax;
    @JsonProperty("gw_items_base_price_incl_tax")
    private String gwItemsBasePriceInclTax;

    @JsonProperty("gw_item_ids")
    private List<String> gwItemIds;
    @JsonProperty("tax_grandtotal_details")
    private List<TaxGrandTotalDetail> taxGrandTotalDetails;

}