package de.leancoders.magento.client.services;

import de.leancoders.magento.client.model.internal.MageAuthContext;
import de.leancoders.magento.client.model.internal.MageConfig;
import de.leancoders.magento.common.model.category.Category;
import de.leancoders.magento.common.request.CategoryProductAssignRequest;
import io.restassured.http.ContentType;
import lombok.NonNull;

import javax.annotation.Nonnull;

import static de.leancoders.magento.client.services.MagePaths.CATEGORIES_V1;
import static de.leancoders.magento.client.services.MagePaths.CATEGORIES_V1_PRODUCT_ASSIGN;

public class CategoryClientService extends BaseClientService {

    @Nonnull
    public CategoryClientService(@Nonnull final MageConfig config,
                                 @NonNull final MageAuthContext mageAuthContext) {
        super(config, mageAuthContext);
    }

    @Nonnull
    public Category categories(final int currentPage,
                               final int pageSize) {

        return request()
            .queryParam("searchCriteria[currentPage]", currentPage)
            .queryParam("searchCriteria[pageSize]", pageSize)
            .log().all()
            .expect().statusCode(200)
            .log().all()
            .when()
            .get(CATEGORIES_V1)
            .as(Category.class);
    }

    /*
    https://adobe-commerce.redoc.ly/2.4.7-admin/tag/categoriescategoryIdproducts#operation/PutV1CategoriesCategoryIdProducts
    */
    public boolean assign(@Nonnull final Integer categoryId,
                          @NonNull final CategoryProductAssignRequest assignRequest) {

        // /V1/categories/{categoryId}/products
        final Boolean response =
            request()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(assignRequest)
                .log().all()
                .expect().statusCode(200)
                .log().all()
                .when()
                .put(CATEGORIES_V1_PRODUCT_ASSIGN, categoryId)
                .as(Boolean.class);

        return response;
    }
}

