package de.leancoders.magento.client.models.internal;


import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Setter
@Getter
public class MagentoAttribute {

    private String attribute_code = "description";
    private Object value = "Full simple product Description 1";

    public MagentoAttribute() {

    }

    public MagentoAttribute(String attribute_code, Object value) {
        this.attribute_code = attribute_code;
        this.value = value;
    }
}
