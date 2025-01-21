package de.leancoders.magento.client.services;

import de.leancoders.magento.client.model.internal.MageAuthContext;
import de.leancoders.magento.client.model.internal.MageConfig;
import de.leancoders.magento.common.model.attributeset.AttributeList;
import de.leancoders.magento.common.model.attributeset.AttributeSet;
import de.leancoders.magento.common.model.search.AttributeSetPage;
import lombok.NonNull;

import javax.annotation.Nonnull;

import static de.leancoders.magento.client.services.MagePaths.ATTRIBUTES_V1_BY_ID;
import static de.leancoders.magento.client.services.MagePaths.ATTRIBUTES_V1_BY_ID_ATTRIBUTES;
import static de.leancoders.magento.client.services.MagePaths.ATTRIBUTES_V1_SEARCH;

public class AttributeSetClientService extends BaseClientService {

    public AttributeSetClientService(@Nonnull final MageConfig config,
                                     @NonNull final MageAuthContext mageAuthContext) {
        super(config, mageAuthContext);
    }

    @Nonnull
    public AttributeSetPage attributeSets(final int currentPage,
                                          final int pageSize) {
        // rest/V1/products/attribute-sets/sets/list/?searchCriteria[pageSize]=1&searchCriteria[pageSize]=100
        return request()
            .queryParam("searchCriteria[currentPage]", currentPage)
            .queryParam("searchCriteria[pageSize]", pageSize)
            .log().all()
            .expect().statusCode(200)
            .log().all()
            .when()
            .get(ATTRIBUTES_V1_SEARCH)
            .as(AttributeSetPage.class);
    }

    @Nonnull
    public AttributeSet attributeSetById(final long attributeSetId) {
        return request()
            .log().all()
            .expect().statusCode(200)
            .log().all()
            .when()
            .get(ATTRIBUTES_V1_BY_ID, attributeSetId)
            .as(AttributeSet.class);
    }

    @Nonnull
    public AttributeList attributesByAttributeSetId(final long attributeSetId) {
        return request()
            .log().all()
            .expect().statusCode(200)
            .log().all()
            .when()
            .get(ATTRIBUTES_V1_BY_ID_ATTRIBUTES, attributeSetId)
            .as(AttributeList.class);
    }

}

