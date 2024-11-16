package de.leancoders.magento.client.models.internal.search;


import de.leancoders.magento.client.models.internal.FilterGroup;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
@Setter
@Getter
public class SearchCriteria {
    private List<FilterGroup> filter_groups = new ArrayList<>();
    private int page_size = 10;
    private int current_page = 0;
}
