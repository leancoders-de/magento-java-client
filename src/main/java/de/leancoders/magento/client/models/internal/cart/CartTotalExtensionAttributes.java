package de.leancoders.magento.client.models.internal.cart;


import lombok.Getter;
import lombok.Setter;


/**
 *
 */
@Getter
@Setter
public class CartTotalExtensionAttributes {
    private double base_customer_balance_amount = 0;
    private double customer_balance_amount = 0;
    private double reward_points_balance = 0;
    private double reward_currency_amount = 0;
    private double base_reward_currency_amount = 0;
}
