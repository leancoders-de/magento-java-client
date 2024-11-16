package de.leancoders.magento.client.models.internal.product;


import com.fasterxml.jackson.annotation.JsonProperty;
import de.leancoders.magento.client.enums.EMediaType;
import de.leancoders.magento.client.models.internal.MagentoBase;
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

    @JsonProperty("type")
    private List<String> types;
}
