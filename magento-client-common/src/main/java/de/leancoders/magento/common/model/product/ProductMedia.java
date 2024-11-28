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
    @JsonProperty("file")
    private String file;

    @JsonProperty("content")
    private ProductMediaContent content;

    @JsonProperty("types")
    private List<EMediaType> types;

}
