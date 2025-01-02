package de.leancoders.magento.client.services;

import de.leancoders.magento.client.model.internal.MageAuthContext;
import de.leancoders.magento.client.model.internal.MageConfig;
import de.leancoders.magento.common.model.category.Category;
import de.leancoders.magento.common.model.search.CustomerPage;
import de.leancoders.magento.common.request.CategoryProductAssignRequest;
import io.restassured.http.ContentType;
import lombok.NonNull;

import javax.annotation.Nonnull;

import java.time.LocalDate;

import static de.leancoders.magento.client.services.MagePaths.CATEGORIES_V1;
import static de.leancoders.magento.client.services.MagePaths.CATEGORIES_V1_PRODUCT_ASSIGN;
import static de.leancoders.magento.client.services.MagePaths.CUSTOMERS_V1_SEARCH;

public class CustomerClientService extends BaseClientService {

    public CustomerClientService(@Nonnull final MageConfig config,
                                 @NonNull final MageAuthContext mageAuthContext) {
        super(config, mageAuthContext);
    }

    @Nonnull
    public CustomerPage customers(final int currentPage,
                                  final int pageSize,
                                  final LocalDate lastEditedSince) {

        return request()
            .queryParam("searchCriteria[currentPage]", currentPage)
            .queryParam("searchCriteria[pageSize]", pageSize)
            // filter last edited since
            // https://developer.adobe.com/commerce/webapi/rest/use-rest/performing-searches/
            // https://developer.adobe.com/commerce/webapi/rest/use-rest/performing-searches/#simple-search-using-a-timestamp
            .queryParam("searchCriteria[filterGroups][0][filters][0][field]", "updated_at")
            .queryParam("searchCriteria[filterGroups][0][filters][0][conditionType]", "gteq")
            .queryParam("searchCriteria[filterGroups][0][filters][0][value]", "2016-07-01 00:00:00")
            // sorting by last edited
            .queryParam("searchCriteria[sortOrders][0][direction]", "ASC")
            .queryParam("searchCriteria[sortOrders][0][field]", "updated_at")
            .log().all()
            .expect().statusCode(200)
            .log().all()
            .when()
            .get(CUSTOMERS_V1_SEARCH)
            .as(CustomerPage.class);
    }

}

