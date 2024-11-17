package de.leancoders.magento.common.model.customer;


import com.fasterxml.jackson.annotation.JsonProperty;
import de.leancoders.magento.common.model.base.MagentoBase;
import de.leancoders.magento.common.model.account.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;


/**
 *
 */
@Getter
@Setter
public class Customer extends MagentoBase {

    @JsonProperty("email")
    private String email;
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("orig_order_id")
    private long origOrderId;
    @JsonProperty("customer_is_guest")
    private boolean customerIsGuest;
    @JsonProperty("customer_note_notify")
    private boolean customer_note_notify;
    @JsonProperty("customer_tax_class_id")
    private long customerTaxClassId;
    @JsonProperty("store_id")
    private long store_id;

    @JsonProperty("billing_address")
    private Address billing_address;

    @JsonProperty("currency")
    private Currency currency;

    @JsonProperty("extension_attributes")
    private Map<String, Object> extensionAttributes;
}
