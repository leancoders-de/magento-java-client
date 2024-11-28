package de.leancoders.magento.common.model.product;


import com.fasterxml.jackson.annotation.JsonProperty;
import de.leancoders.magento.common.model.base.MagentoBase;
import de.leancoders.magento.common.model.enums.EMediaType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 *
 */
@Getter
@Setter
public class ProductMedia extends MagentoBase {

    @JsonProperty("media_type")
    private EMediaType mediaType;
    @JsonProperty("label")
    private String label;
    @JsonProperty("position")
    private int position;
    @JsonProperty("disabled")
    private boolean disabled;

    // relative file path of magento; e.g. /f/i/file-sku.png
    @JsonProperty(value = "file", access = JsonProperty.Access.READ_ONLY)
    private String file;

    @JsonProperty("content")
    private ProductMediaContent content;

    @JsonProperty("types")
    private List<EMediaType> types;

}
