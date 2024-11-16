package de.leancoders.magento.client.models.internal.product;


import com.fasterxml.jackson.annotation.JsonProperty;
import de.leancoders.magento.client.enums.EProductStatus;
import de.leancoders.magento.client.enums.EProductType;
import de.leancoders.magento.client.enums.EProductVisibility;
import de.leancoders.magento.client.models.internal.MagentoAttribute;
import de.leancoders.magento.client.models.internal.MagentoBase;
import de.leancoders.magento.client.models.internal.TierPrices;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


/**
 *
 */
@Getter
@Setter
public class Product extends MagentoBase {

    @JsonProperty("sku")
    private String sku;
    @JsonProperty("name")
    private String name;
    @JsonProperty("attribute_set_id")
    private long attributeSetId;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("status")
    private EProductStatus status;
    @JsonProperty("visibility")
    private EProductVisibility visibility;
    @JsonProperty("type_id")
    private EProductType productType;

    @JsonProperty("weight")
    private BigDecimal weight;

    // @JSONField(deserializeUsing = ProductAttributeValueDeserializer.class)
    @JsonProperty("extension_attributes")
    private List<MagentoAttribute> extensionAttributes;
    @JsonProperty("product_links")
    private List<String> productLinks;
    @JsonProperty("tier_prices")
    private List<TierPrices> tierPrices;

    // @JSONField(deserializeUsing = ProductAttributeValueDeserializer.class)
    @JsonProperty("custom_attributes")
    private List<MagentoAttribute> customAttributes;

}


