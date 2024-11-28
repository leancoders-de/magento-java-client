package de.leancoders.magento.client.services;

import de.leancoders.magento.client.model.internal.MageAuthContext;
import de.leancoders.magento.client.model.internal.MageConfig;
import de.leancoders.magento.client.model.internal.ProductUpdateContext;
import de.leancoders.magento.common.model.product.Product;
import de.leancoders.magento.common.model.search.ProductPage;
import de.leancoders.magento.common.request.ProductUpdateRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.NonNull;

import javax.annotation.Nonnull;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static de.leancoders.magento.client.services.MagePaths.PRODUCTS_V1_BY_SKU;
import static de.leancoders.magento.client.services.MagePaths.PRODUCTS_V1_PAGE;

public class ProductClientService extends BaseClientService {

    private final MageConfig config;

    @Nonnull
    public ProductClientService(@Nonnull final MageConfig config,
                                @NonNull final MageAuthContext mageAuthContext) {
        super(mageAuthContext);
        this.config = config;
    }

    @Nonnull
    public ProductPage products(final int currentPage,
                                final int pageSize) {

        return request()
            .queryParam("searchCriteria[currentPage]", currentPage)
            .queryParam("searchCriteria[pageSize]", pageSize)
            .expect().statusCode(200)
            .log().all()
            .when()
            .get(PRODUCTS_V1_PAGE)
            .as(ProductPage.class);
    }

    @Nonnull
    public Product bySKU(@Nonnull final String sku) {

        return request()
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
                .expect().statusCode(200)
                .log().all()
                .when()
                .put(PRODUCTS_V1_BY_SKU, encode(sku))
                .as(Product.class);

        return ProductUpdateContext.of(productUpdateRequest, sku, productResponse);
    }

}

