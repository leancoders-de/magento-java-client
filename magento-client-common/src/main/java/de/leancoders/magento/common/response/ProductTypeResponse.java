package de.leancoders.magento.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.leancoders.magento.common.model.enums.EProductType;
import lombok.Data;

@Data
public class ProductTypeResponse {

    // "simple"
    @JsonProperty("name")
    private EProductType productType;
    // "simple product"
    @JsonProperty("label")
    private String label;

}
