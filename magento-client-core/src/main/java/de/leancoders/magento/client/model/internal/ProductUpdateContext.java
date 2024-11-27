package de.leancoders.magento.client.model.internal;

import de.leancoders.magento.common.model.product.Product;
import de.leancoders.magento.common.request.ProductUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class ProductUpdateContext {

    private final ProductUpdateRequest updateRequest;

    private final String response;
    private final Product product;

}
