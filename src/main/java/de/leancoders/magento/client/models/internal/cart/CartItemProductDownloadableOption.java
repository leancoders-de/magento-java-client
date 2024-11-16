package de.leancoders.magento.client.models.internal.cart;


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
    private List<Integer> downloadable_links = new ArrayList<>();

}
