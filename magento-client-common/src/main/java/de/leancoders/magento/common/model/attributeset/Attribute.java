package de.leancoders.magento.common.model.attributeset;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Attribute {

    @JsonProperty("attribute_id")
    private Long attributeId;
    @JsonProperty("attribute_code")
    private String attributeCode;
    @JsonProperty("frontend_input")
    private String frontendInput;
    @JsonProperty("entity_type_id")
    private String entityTypeId;
    @JsonProperty("backend_type")
    private String backendType;
    @JsonProperty("default_frontend_label")
    private String defaultFrontendLabel;
    @JsonProperty("source_model")
    private String sourceModel;
    @JsonProperty("default_value")
    private String defaultValue;
    @JsonProperty("is_unique")
    private Long isUnique;

    @JsonProperty("is_required")
    private Boolean required;
    @JsonProperty("is_user_defined")
    private Boolean userDefined;

    @JsonProperty("options")
    private List<AttributeOption> options;

    // TODO
    //  frontend_labels
    //  validation_rules

}
