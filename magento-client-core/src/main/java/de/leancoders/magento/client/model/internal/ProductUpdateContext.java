package de.leancoders.magento.client.model.internal;

import de.leancoders.magento.common.model.product.Product;
import de.leancoders.magento.common.request.ProductUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor(staticName = "of")
@Getter
public class ProductUpdateContext {

    @NonNull
    private final ProductUpdateRequest updateRequest;
    @NonNull
    private final String sku;
    @NonNull
    private final Product product;

}
