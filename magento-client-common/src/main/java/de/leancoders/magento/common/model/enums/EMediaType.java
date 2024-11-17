package de.leancoders.magento.common.model.enums;

import lombok.Getter;
import lombok.NonNull;

@Getter
public enum EMediaType {

    IMAGE("image");

    private final String magentoRepresentation;

    EMediaType(@NonNull final String magentoRepresentation) {
        this.magentoRepresentation = magentoRepresentation;
    }
}
