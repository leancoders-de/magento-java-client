package de.leancoders.magento.common.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.leancoders.magento.common.model.product.Product;
import de.leancoders.magento.common.model.product.ProductMedia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Getter
public class ProductMediaUpdateRequest {

    @JsonProperty("entry")
    private ProductMedia productMedia;

}
