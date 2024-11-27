package de.leancoders.magento.common.model.search;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 *
 */
@Data
public class FilterGroup {
    private String name;
    private String value;
    private String condition_type;
}
