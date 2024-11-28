package de.leancoders.magento.common.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.leancoders.magento.common.model.product.ProductLink;
import lombok.Data;

@Data
public class CategoryProductAssignRequest {

    @JsonProperty("productLink")
    protected ProductLink productLink;

}
