package de.leancoders.magento.common.model.tax;


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
public class TaxGrandTotalDetail {

    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("group_id")
    private long groupId;
    @JsonProperty("rates")
    private List<TaxGrandTotalDetailRate> rates;
}
