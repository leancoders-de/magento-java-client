package de.leancoders.magento.common.model.product;


import com.fasterxml.jackson.annotation.JsonProperty;
import de.leancoders.magento.common.model.base.MagentoBase;
import de.leancoders.magento.common.model.enums.EProductStatus;
import de.leancoders.magento.common.model.enums.EProductType;
import de.leancoders.magento.common.model.enums.EProductVisibility;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.print.attribute.standard.Media;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
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

    @JsonProperty("extension_attributes")
    private Map<String, Object> extensionAttributes;
    @JsonProperty("product_links")
    private List<String> productLinks;
    @JsonProperty("tier_prices")
    private List<TierPrices> tierPrices;

    @JsonProperty("custom_attributes")
    private List<MagentoAttribute> customAttributes;

    @JsonProperty("media_gallery_entries")
    private List<ProductMedia> productMediaEntries;

}


