package de.leancoders.magento.common.model.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Setter
@Getter
public class CartItemProductCustomOptionExtensionAttributes {

    @JsonProperty("file_info")
    private FileInfo fileInfo;

}
