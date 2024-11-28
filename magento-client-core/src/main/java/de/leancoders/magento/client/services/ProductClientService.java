package de.leancoders.magento.client.services;

import de.leancoders.magento.client.model.internal.MageAuthContext;
import de.leancoders.magento.client.model.internal.MageConfig;
import de.leancoders.magento.client.model.internal.ProductUpdateContext;
import de.leancoders.magento.common.model.product.Product;
import de.leancoders.magento.common.model.search.ProductPage;
import de.leancoders.magento.common.request.ProductStockUpdateRequest;
import de.leancoders.magento.common.request.ProductUpdateRequest;
import io.restassured.http.ContentType;
import lombok.NonNull;

import javax.annotation.Nonnull;

import static de.leancoders.magento.client.services.MagePaths.PRODUCTS_V1_BY_SKU;
import static de.leancoders.magento.client.services.MagePaths.PRODUCTS_V1_BY_SKU_STOCK_ITEMS_BY_ID;
import static de.leancoders.magento.client.services.MagePaths.PRODUCTS_V1_PAGE;

public class ProductClientService extends BaseClientService {

    @Nonnull
    public ProductClientService(@Nonnull final MageConfig config,
                                @NonNull final MageAuthContext mageAuthContext) {
        super(config, mageAuthContext);
    }

    @Nonnull
    public ProductPage products(final int currentPage,
                                final int pageSize) {

        return request()
            .queryParam("searchCriteria[currentPage]", currentPage)
            .queryParam("searchCriteria[pageSize]", pageSize)
            .log().all()
            .expect().statusCode(200)
            .log().all()
            .when()
            .get(PRODUCTS_V1_PAGE)
            .as(ProductPage.class);
    }

    @Nonnull
    public Product bySKU(@Nonnull final String sku) {

        return request()
            .log().all()
            .expect().statusCode(200)
            .log().all()
            .when()
            .get(PRODUCTS_V1_BY_SKU, encode(sku))
            .as(Product.class);

    }

    @Nonnull
    public ProductUpdateContext save(@Nonnull final Product product) {

        final String sku = product.getSku();

        final ProductUpdateRequest productUpdateRequest = ProductUpdateRequest.of(product);

        final Product productResponse =
            request()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(productUpdateRequest)
                .log().all()
                .expect().statusCode(200)
                .log().all()
                .when()
                .put(PRODUCTS_V1_BY_SKU, encode(sku))
                .as(Product.class);

        return ProductUpdateContext.of(productUpdateRequest, sku, productResponse);
    }

    @Nonnull
    public Integer save(@NonNull final String sku,
                        @NonNull final Integer stockId,
                        @Nonnull final ProductStockUpdateRequest stockUpdateRequest) {

        final Integer response =
            request()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(stockUpdateRequest)
                .log().all()
                .expect().statusCode(200)
                .log().all()
                .when()
                .put(PRODUCTS_V1_BY_SKU_STOCK_ITEMS_BY_ID, encode(sku), stockId)
                .as(Integer.class);

        return response;
    }

}

