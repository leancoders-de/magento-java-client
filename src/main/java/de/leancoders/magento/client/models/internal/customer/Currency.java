package de.leancoders.magento.client.models.internal.customer;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


/**
 *
 */
@Getter
@Setter
public class Currency {

    @JsonProperty("global_currency_code")
    private String globalCurrencyCode;
    @JsonProperty("base_currency_code")
    private String baseCurrencyCode;
    @JsonProperty("store_currency_code")
    private String storeCurrencyCode;
    @JsonProperty("quote_currency_code")
    private String quoteCurrencyCode;
    @JsonProperty("store_to_base_rate")
    private double storeToBaseRate;
    @JsonProperty("store_to_quote_rate")
    private double storeToQuoteRate;
    @JsonProperty("base_to_global_rate")
    private double baseToGlobalRate;
    @JsonProperty("base_to_quote_rate")
    private double baseToQuoteRate;

}
