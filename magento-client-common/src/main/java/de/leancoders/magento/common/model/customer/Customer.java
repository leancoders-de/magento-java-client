package de.leancoders.magento.common.model.customer;


import com.fasterxml.jackson.annotation.JsonProperty;
import de.leancoders.magento.common.model.base.MagentoBase;
import de.leancoders.magento.common.model.account.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;


/**
 *
 */
@Getter
@Setter
public class Customer extends MagentoBase {

    @JsonProperty("dob")
    private String dob;
    @JsonProperty("email")
    private String email;
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("middlename")
    private String middleName;
    @JsonProperty("prefix")
    private String prefix;
    @JsonProperty("suffix")
    private String suffix;

    @JsonProperty("gender")
    private long gender;

    @JsonProperty("orig_order_id")
    private long origOrderId;
    @JsonProperty("customer_is_guest")
    private boolean customerIsGuest;
    @JsonProperty("customer_note_notify")
    private boolean customer_note_notify;
    @JsonProperty("customer_tax_class_id")
    private long customerTaxClassId;
    @JsonProperty("store_id")
    private long storeId;
    @JsonProperty("website_id")
    private long websiteId;
    @JsonProperty("taxvat")
    private String taxVAT;

    @JsonProperty("group_id")
    private long groupId;
    @JsonProperty("default_billing")
    private String defaultBilling;
    @JsonProperty("default_shipping")
    private String defaultShipping;
    @JsonProperty("confirmation")
    private String confirmation;

    @JsonProperty("billing_address")
    private Address billing_address;

    @JsonProperty("currency")
    private Currency currency;

    @JsonProperty("addresses")
    private List<Address> addresses;

    @JsonProperty("custom_attributes")
    private List<Map<String, Object>> customAttributes;

    @JsonProperty("extension_attributes")
    private Map<String, Object> extensionAttributes;


}
