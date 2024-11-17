package de.leancoders.magento.common.model.enums;

import lombok.Getter;
import lombok.NonNull;

@Getter
public enum EBackendType {

    VARCHAR("varchar");

    private final String magentoRepresentation;

    EBackendType(@NonNull final String magentoRepresentation) {
        this.magentoRepresentation = magentoRepresentation;
    }
}
