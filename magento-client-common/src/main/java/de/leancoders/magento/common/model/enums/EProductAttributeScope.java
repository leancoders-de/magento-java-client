package de.leancoders.magento.common.model.enums;

import lombok.Getter;

@Getter
public enum EProductAttributeScope {

    STORE("store");

    private final String magentoRepresentation;

    EProductAttributeScope(final String magentoRepresentation) {
        this.magentoRepresentation = magentoRepresentation;
    }
}
