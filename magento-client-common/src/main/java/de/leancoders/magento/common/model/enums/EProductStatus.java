package de.leancoders.magento.common.model.enums;

import lombok.Getter;

@Getter
public enum EProductStatus {

    STATUS_DISABLED(2),
    STATUS_ENABLED(1);

    private final int magentoRepresentation;

    EProductStatus(final int magentoRepresentation) {
        this.magentoRepresentation = magentoRepresentation;
    }
}
