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
public class Region {

    @JsonProperty("region")
    private String region;
    @JsonProperty("region_id")
    private String regionId;
    @JsonProperty("region_code")
    private String regionCode;

}
