package de.leancoders.magento.client.models.internal.account;


import com.fasterxml.jackson.annotation.JsonProperty;
import de.leancoders.magento.client.models.internal.MagentoBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 *
 */
@Getter
@Setter
public class Address extends MagentoBase {

    @JsonProperty("region")
    private String region;
    @JsonProperty("region_id")
    private String regionId;
    @JsonProperty("region_code")
    private String regionCode;
    @JsonProperty("country_id")
    private String countryId;

    @JsonProperty("telephone")
    private String telephone;
    @JsonProperty("postcode")
    private String postCode;
    @JsonProperty("city")
    private String city;
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("email")
    private String email;

    @JsonProperty("same_as_billing")
    private boolean sameAsBilling;
    @JsonProperty("save_in_address_book")
    private boolean saveInAddressBook;

    @JsonProperty("street")
    private List<String> street;

}
