package de.leancoders.magento.common.model.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TierPrices {

    @JsonProperty("customer_group_id")
    private long customer_group_id;
    @JsonProperty("qty")
    private long qty;
    @JsonProperty("value")
    private BigDecimal value;
}
