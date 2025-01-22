package de.leancoders.magento.client.services;

import de.leancoders.magento.client.model.internal.MageAuthContext;
import de.leancoders.magento.client.model.internal.MageConfig;
import de.leancoders.magento.common.model.category.Category;
import de.leancoders.magento.common.model.search.CustomerPage;
import de.leancoders.magento.common.request.CategoryProductAssignRequest;
import io.restassured.http.ContentType;
import lombok.NonNull;

import javax.annotation.Nonnull;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import static de.leancoders.magento.client.services.MagePaths.CATEGORIES_V1;
import static de.leancoders.magento.client.services.MagePaths.CATEGORIES_V1_PRODUCT_ASSIGN;
import static de.leancoders.magento.client.services.MagePaths.CUSTOMERS_V1_SEARCH;
import static java.time.temporal.ChronoField.*;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;

public class CustomerClientService extends BaseClientService {

    private static final DateTimeFormatter ISO_DATE_TIME =
        new DateTimeFormatterBuilder()
            .appendValue(YEAR, 4)
            .appendLiteral('-')
            .appendValue(MONTH_OF_YEAR, 2)
            .appendLiteral('-')
            .appendValue(DAY_OF_MONTH, 2)
            .appendLiteral(' ')
            .appendValue(HOUR_OF_DAY, 2)
            .appendLiteral(':')
            .appendValue(MINUTE_OF_HOUR, 2)
            .appendLiteral(':')
            .appendValue(SECOND_OF_MINUTE, 2)
            .toFormatter();

    public CustomerClientService(@Nonnull final MageConfig config,
                                 @NonNull final MageAuthContext mageAuthContext) {
        super(config, mageAuthContext);
    }

    @Nonnull
    public CustomerPage customers(final int currentPage,
                                  final int pageSize,
                                  final LocalDateTime lastEditedSince) {

        final String lastEditedSinceStr = ISO_DATE_TIME.format(lastEditedSince);


        return request()
            .queryParam("searchCriteria[currentPage]", currentPage)
            .queryParam("searchCriteria[pageSize]", pageSize)
            // filter last edited since
            // https://developer.adobe.com/commerce/webapi/rest/use-rest/performing-searches/
            // https://developer.adobe.com/commerce/webapi/rest/use-rest/performing-searches/#simple-search-using-a-timestamp
            .queryParam("searchCriteria[filterGroups][0][filters][0][field]", "updated_at")
            .queryParam("searchCriteria[filterGroups][0][filters][0][conditionType]", "gteq")
            .queryParam("searchCriteria[filterGroups][0][filters][0][value]", lastEditedSinceStr)
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

