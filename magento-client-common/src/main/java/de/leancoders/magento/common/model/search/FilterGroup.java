package de.leancoders.magento.common.model.search;


import lombok.Getter;
import lombok.Setter;


/**
 *
 */
@Getter
@Setter
public class FilterGroup {
    private String name;
    private String value;
    private String condition_type;
}
