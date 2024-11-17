package de.leancoders.magento.common.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SearchBase<T> {

    @JsonProperty("search_criteria")
    private SearchCriteria searchCriteria;
    @JsonProperty("total_count")
    private int totalCount;
    @JsonProperty("items")
    private List<T> items;

}
