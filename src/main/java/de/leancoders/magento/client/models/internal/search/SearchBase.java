package de.leancoders.magento.client.models.internal.search;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SearchBase<T> {

    @JsonProperty("search_criteria")
    private SearchCriteria searchCriteria;
    @JsonProperty("total_count")
    private int totalCount;
    @JsonProperty("items")
    private List<T> items;

}
