package de.leancoders.magento.common.model.enums;

import lombok.Getter;

@Getter
public enum EProductType {

    SIMPLE("simple"),
    CONFIGURABLE("configurable"),
    VIRTUAL("virtual"),
    GROUPED("grouped"),
    BUNDLE("bundle"),
    DOWNLOADABLE("Downloadable");

    private final String magentoRepresentation;

    EProductType(final String magentoRepresentation) {
        this.magentoRepresentation = magentoRepresentation;
    }
}
