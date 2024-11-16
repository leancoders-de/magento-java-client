package de.leancoders.magento.client.models.internal.product;


import com.fasterxml.jackson.annotation.JsonProperty;
import de.leancoders.magento.client.enums.EBackendType;
import de.leancoders.magento.client.enums.EProductAttributeScope;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 *
 */
@Getter
@Setter
public class ProductAttribute {

    @JsonProperty("is_wysiwyg_enabled")
    private boolean wysiwygEnabled;
    @JsonProperty("is_html_allowed_on_front")
    private boolean htmlAllowedOnFront;
    @JsonProperty("used_for_sort_by")
    private boolean usedForSortBy;
    @JsonProperty("is_filterable")
    private boolean filterable;
    @JsonProperty("is_filterable_in_search")
    private boolean filterableInSearch;
    @JsonProperty("is_used_in_grid")
    private boolean usedInGrid;
    @JsonProperty("is_visible_in_grid")
    private boolean visibleInGrid;
    @JsonProperty("is_filterable_in_grid")
    private boolean filterableInGrid;
    @JsonProperty("position")
    private int position;
    @JsonProperty("is_searchable")
    private boolean searchable;
    @JsonProperty("is_visible_in_advanced_search")
    private boolean visibleInAdvancedSearch;
    @JsonProperty("is_comparable")
    private boolean comparable;
    @JsonProperty("is_used_for_promo_rules")
    private boolean usedForPromoRules;
    @JsonProperty("is_visible_on_front")
    private boolean visibleOnFront;
    @JsonProperty("used_in_product_listing")
    private boolean usedInProductListing;
    @JsonProperty("is_visible")
    private boolean visible;
    @JsonProperty("scope")
    private EProductAttributeScope scope;
    @JsonProperty("attribute_id")
    private long attributeId;
    @JsonProperty("attribute_code")
    private String attributeCode;
    @JsonProperty("frontend_input")
    private String frontendInput;
    @JsonProperty("entity_type_id")
    private String entityTypeId;
    @JsonProperty("is_required")
    private boolean required;
    @JsonProperty("is_user_defined")
    private boolean userDefined;
    @JsonProperty("default_frontend_label")
    private String defaultFrontendLabel;
    @JsonProperty("frontend_labels")
    private String frontendLabels;
    @JsonProperty("backend_type")
    private EBackendType backendType;
    @JsonProperty("is_unique")
    private boolean unique;
    @JsonProperty("frontend_class")
    private String frontendClass;

    @JsonProperty("apply_to")
    private List<String> applyTo;
    @JsonProperty("validation_rules")
    private List<String> validationRules;
    @JsonProperty("options")
    private List<String> options;
}
