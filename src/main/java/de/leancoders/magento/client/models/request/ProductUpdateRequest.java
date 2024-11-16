package de.leancoders.magento.client.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.leancoders.magento.client.models.internal.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Getter
public class ProductUpdateRequest {

    @JsonProperty("product")
    private Product product;

}
