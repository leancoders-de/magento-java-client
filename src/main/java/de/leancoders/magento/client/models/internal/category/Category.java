package de.leancoders.magento.client.models.internal.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.leancoders.magento.client.models.internal.MagentoBase;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 */
@Setter
@Getter
public class Category extends MagentoBase {

    @JsonProperty("parent_id")
    private long parentId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("is_active")
    private boolean isActive;
    @JsonProperty("position")
    private int position;
    @JsonProperty("position")
    private int level;
    @JsonProperty("product_count")
    private int productCount;

    @JsonProperty("custom_attributes")
    private List<CategoryAttribute> customAttributes;
    @JsonProperty("children_data")
    private List<Category> childrenData;
}
