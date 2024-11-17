package de.leancoders.magento.common.model.enums;

import lombok.Getter;

/*
 */
@Getter
public enum EProductVisibility {

    VISIBILITY_NOT_VISIBLE(1),
    VISIBILITY_IN_CATALOG(2),
    VISIBILITY_IN_SEARCH(3),
    VISIBILITY_BOTH(4);

    private final int magentoRepresentation;

    EProductVisibility(final int magentoRepresentation) {
        this.magentoRepresentation = magentoRepresentation;
    }
}
