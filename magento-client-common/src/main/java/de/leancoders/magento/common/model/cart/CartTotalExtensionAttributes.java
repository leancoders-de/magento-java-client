package de.leancoders.magento.common.model.cart;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


/**
 *
 */
@Getter
@Setter
public class CartTotalExtensionAttributes {

    @JsonProperty("base_customer_balance_amount")
    private BigDecimal baseCustomerBalanceAmount;
    @JsonProperty("customer_balance_amount")
    private BigDecimal customerBalanceAmount;
    @JsonProperty("reward_points_balance")
    private BigDecimal rewardPointsBalance;
    @JsonProperty("reward_currency_amount")
    private BigDecimal rewardCurrencyAmount;
    @JsonProperty("base_reward_currency_amount")
    private BigDecimal baseRewardCurrencyAmount;

}
