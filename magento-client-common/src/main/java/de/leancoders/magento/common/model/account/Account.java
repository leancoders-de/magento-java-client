package de.leancoders.magento.common.model.account;


import com.fasterxml.jackson.annotation.JsonProperty;
import de.leancoders.magento.common.model.base.MagentoBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 */
@Getter
@Setter
public class Account extends MagentoBase {

    @JsonProperty("group_id")
    private long groupId;
    @JsonProperty("created_in")
    private String createdIn;
    @JsonProperty("email")
    private String email;
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("store_id")
    private long storeId;
    @JsonProperty("website_id")
    private long websiteId;
    @JsonProperty("disable_auto_group_change")
    private int disableAutoGroupChange;

    @JsonProperty("addresses")
    private List<Address> addresses;

}
