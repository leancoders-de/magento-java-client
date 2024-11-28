package de.leancoders.magento.common.model.product;


import com.fasterxml.jackson.annotation.JsonProperty;
import de.leancoders.magento.common.model.base.MagentoBase;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;


/**
 *
 */
@Getter
@Setter
public class ProductLink extends MagentoBase {

    @JsonProperty("sku")
    private String sku;
    @JsonProperty("position")
    private int position;
    @JsonProperty("category_id")
    private String categoryId;

    @JsonProperty("extension_attributes")
    private Map<String, Object> extensionAttributes;

}
