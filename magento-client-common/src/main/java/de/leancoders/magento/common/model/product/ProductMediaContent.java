package de.leancoders.magento.common.model.product;


import com.fasterxml.jackson.annotation.JsonProperty;
import de.leancoders.magento.common.model.base.MagentoBase;
import de.leancoders.magento.common.model.enums.EMimeType;
import lombok.Getter;
import lombok.Setter;


/**
 *
 */
@Getter
@Setter
public class ProductMediaContent extends MagentoBase {

    @JsonProperty("base64_encoded_data")
    private String base64EncodedData;
    @JsonProperty("type")
    private EMimeType EMimeType;
    @JsonProperty("name")
    private String name;

}
