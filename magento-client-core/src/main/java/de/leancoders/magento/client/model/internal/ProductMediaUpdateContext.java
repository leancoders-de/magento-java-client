package de.leancoders.magento.client.model.internal;

import de.leancoders.magento.common.model.product.ProductMedia;
import de.leancoders.magento.common.request.ProductMediaUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class ProductMediaUpdateContext {

    private final ProductMediaUpdateRequest updateRequest;

    private final String response;
    private final ProductMedia productMedia;

}
