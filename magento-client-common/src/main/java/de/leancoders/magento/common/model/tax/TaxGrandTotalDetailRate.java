package de.leancoders.magento.common.model.tax;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


/**
 *
 */
@Getter
@Setter
public class TaxGrandTotalDetailRate {

    @JsonProperty("percent")
    private String percent;
    @JsonProperty("title")
    private String title;

}
