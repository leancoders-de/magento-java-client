package de.leancoders.magento.client.models.internal;


import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


/**
 *
 */
@Getter
@Setter
public class ConfigurableItemOption {
    private String option_id = "";
    private double option_value = 0;
    private Map<String, Object> extension_attributes = new HashMap<>();
}
