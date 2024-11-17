package de.leancoders.magento.common.model.customer;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


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
    private BigDecimal storeToBaseRate;
    @JsonProperty("store_to_quote_rate")
    private BigDecimal storeToQuoteRate;
    @JsonProperty("base_to_global_rate")
    private BigDecimal baseToGlobalRate;
    @JsonProperty("base_to_quote_rate")
    private BigDecimal baseToQuoteRate;

}
