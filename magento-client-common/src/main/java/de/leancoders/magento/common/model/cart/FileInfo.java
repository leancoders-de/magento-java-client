package de.leancoders.magento.common.model.cart;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


/**
 *
 */
@Getter
@Setter
public class FileInfo {
    @JsonProperty("base64_encoded_data")
    private String base64EncodedData;
    @JsonProperty("type")
    private String type;
    @JsonProperty("name")
    private String name;
}
