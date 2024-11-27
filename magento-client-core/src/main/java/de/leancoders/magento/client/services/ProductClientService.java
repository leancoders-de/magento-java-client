package de.leancoders.magento.client.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Charsets;
import com.google.common.base.Utf8;
import de.leancoders.magento.client.helper.jackson.ObjectMapperFactory;
import de.leancoders.magento.client.model.internal.MageAuthContext;
import de.leancoders.magento.client.model.internal.MageConfig;
import de.leancoders.magento.client.model.internal.ProductUpdateContext;
import de.leancoders.magento.common.model.auth.UserLoginRequest;
import de.leancoders.magento.common.model.product.Product;
import de.leancoders.magento.common.model.search.ProductPage;
import de.leancoders.magento.common.request.ProductUpdateRequest;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.NonNull;

import javax.annotation.Nonnull;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static de.leancoders.magento.client.services.MagePaths.PRODUCTS_V1;
import static de.leancoders.magento.client.services.MagePaths.PRODUCTS_V1_BY_SKU;
import static de.leancoders.magento.client.services.MagePaths.PRODUCTS_V1_PAGE;
import static io.restassured.internal.http.URIBuilder.encode;

public class ProductClientService {

    private final MageConfig config;
    private final MageAuthContext mageAuthContext;

    @Nonnull
    public ProductClientService(@Nonnull final MageConfig config,
                                @NonNull final MageAuthContext mageAuthContext) {
        this.config = config;
        this.mageAuthContext = mageAuthContext;
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

    private RequestSpecification request() {
        if (mageAuthContext.isAuthenticated()) {
            return authorizedRequest();
        } else {
            return unauthorizedRequest();
        }
    }

    private RequestSpecification authorizedRequest() {
        return RestAssured.given()
            .accept(ContentType.JSON)
            .header("Authorization", "Bearer " + mageAuthContext.getToken());
    }

    private RequestSpecification unauthorizedRequest() {
        return RestAssured.given()
            .accept(ContentType.JSON);
    }

    @Nonnull
    public String encode(@NonNull final String text) {
        return URLEncoder.encode(text, StandardCharsets.UTF_8);
    }

}

