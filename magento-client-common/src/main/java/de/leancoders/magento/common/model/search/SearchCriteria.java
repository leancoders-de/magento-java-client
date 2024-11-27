package de.leancoders.magento.common.model.search;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 *
 */
@Data
public class SearchCriteria {

    @JsonProperty("filter_groups")
    private List<FilterGroup> filterGroups;
    @JsonProperty("page_size")
    private int pageSize;
    @JsonProperty("current_page")
    private int currentPage;

}
