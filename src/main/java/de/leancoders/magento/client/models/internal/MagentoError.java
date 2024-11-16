package de.leancoders.magento.client.models.internal;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
@Setter
@Getter
public class MagentoError {
    private String message = "";
    private List<MagentoParameter> parameters = new ArrayList<>();
}
