package de.leancoders.magento.common.model.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.leancoders.magento.common.model.base.MagentoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Category extends MagentoBase {

    @JsonProperty("parent_id")
    private long parentId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("is_active")
    private boolean isActive;
    @JsonProperty("position")
    private int position;
    @JsonProperty("level")
    private int level;
    @JsonProperty("product_count")
    private int productCount;

    @JsonProperty("custom_attributes")
    private List<CategoryAttribute> customAttributes;
    @JsonProperty("children_data")
    private List<Category> childrenData;
}
