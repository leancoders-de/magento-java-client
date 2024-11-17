package de.leancoders.magento.common.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 *
 */
@Setter
@Getter
public class MagentoError {

    @JsonProperty("message")
    private String message;
    @JsonProperty("parameters")
    private List<MagentoErrorParameter> parameters;
}
