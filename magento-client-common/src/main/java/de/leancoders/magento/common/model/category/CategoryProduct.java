package de.leancoders.magento.common.model.category;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Getter
@Setter
public class CategoryProduct {

    @JsonProperty("sku")
    private String sku;
    @JsonProperty("position")
    private int position;
    @JsonProperty("category_id")
    private long categoryId;

}
