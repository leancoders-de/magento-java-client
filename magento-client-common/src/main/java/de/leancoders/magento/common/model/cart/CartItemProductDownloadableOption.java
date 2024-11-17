package de.leancoders.magento.common.model.cart;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
@Getter
@Setter
public class CartItemProductDownloadableOption {

    @JsonProperty("downloadable_links")
    private List<Integer> downloadableLinks;

}
