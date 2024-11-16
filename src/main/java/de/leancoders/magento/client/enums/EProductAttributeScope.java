package de.leancoders.magento.client.enums;

import lombok.Getter;

@Getter
public enum EProductAttributeScope {

    STORE("store");

    private final String magentoRepresentation;

    EProductAttributeScope(final String magentoRepresentation) {
        this.magentoRepresentation = magentoRepresentation;
    }
}
