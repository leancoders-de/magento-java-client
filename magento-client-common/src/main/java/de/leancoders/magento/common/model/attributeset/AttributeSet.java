package de.leancoders.magento.common.model.attributeset;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AttributeSet {

    @JsonProperty("attribute_set_id")
    private long attributeSetId;
    @JsonProperty("attribute_set_name")
    private String attributeSetName;
    @JsonProperty("sort_order")
    private long sortOrder;
    @JsonProperty("entity_type_id")
    private long entityTypeId;

}
