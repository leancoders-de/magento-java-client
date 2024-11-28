package de.leancoders.magento.client.services;

import de.leancoders.magento.client.model.internal.MageAuthContext;
import de.leancoders.magento.client.model.internal.MageConfig;
import de.leancoders.magento.client.model.internal.ProductMediaUpdateContext;
import de.leancoders.magento.common.model.product.ProductMedia;
import de.leancoders.magento.common.model.product.ProductMediaList;
import de.leancoders.magento.common.request.ProductMediaUpdateRequest;
import io.restassured.http.ContentType;
import lombok.NonNull;
import org.apache.commons.codec.binary.Base64;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;

import static de.leancoders.magento.client.services.MagePaths.PRODUCTS_V1_BY_SKU;
import static de.leancoders.magento.client.services.MagePaths.PRODUCTS_V1_BY_SKU_MEDIA;
import static de.leancoders.magento.client.services.MagePaths.PRODUCTS_V1_BY_SKU_MEDIA_BY_ID;

public class ProductMediaClientService extends BaseClientService {

    private final MageConfig config;

    @Nonnull
    public ProductMediaClientService(@Nonnull final MageConfig config,
                                     @NonNull final MageAuthContext mageAuthContext) {
        super(mageAuthContext);
        this.config = config;
    }

    @Nonnull
    public ProductMediaList getBySku(@NonNull final String sku) {

        return request()
            .expect().statusCode(200)
            .log().all()
            .when()
            .get(PRODUCTS_V1_BY_SKU_MEDIA, encode(sku))
            .as(ProductMediaList.class);

    }

    @Nonnull
    public ProductMedia getBySkuAndId(@NonNull final String sku,
                                      final long mediaId) {

        return request()
            .expect().statusCode(200)
            .log().all()
            .when()
            .get(PRODUCTS_V1_BY_SKU_MEDIA_BY_ID, encode(sku), mediaId)
            .as(ProductMedia.class);
    }

    public boolean deleteProductMedia(@NonNull final String sku,
                                      final long mediaId) {

        return request()
            .expect().statusCode(200)
            .log().all()
            .when()
            .delete(PRODUCTS_V1_BY_SKU_MEDIA_BY_ID, encode(sku), mediaId)
            .as(Boolean.class);
    }

    @Nonnull
    public ProductMediaUpdateContext save(@NonNull final String sku,
                                          @NonNull final ProductMedia productMedia) {

        //
        productMedia.setId(null);

        final ProductMediaUpdateRequest updateRequest = ProductMediaUpdateRequest.of(productMedia);

        final ProductMedia response =
            request()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(updateRequest)
                .expect().statusCode(200)
                .log().all()
                .when()
                .put(PRODUCTS_V1_BY_SKU_MEDIA, encode(sku))
                .as(ProductMedia.class);

        return ProductMediaUpdateContext.of(updateRequest, sku, productMedia);
    }

    @Nonnull
    public ProductMediaUpdateContext update(@NonNull final String sku,
                                            @NonNull final Long entryId,
                                            @NonNull final ProductMedia productMedia) {

        //
        productMedia.setId(entryId);

        final ProductMediaUpdateRequest updateRequest = ProductMediaUpdateRequest.of(productMedia);

        final ProductMedia response =
            request()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(updateRequest)
                .expect().statusCode(200)
                .log().all()
                .when()
                .put(PRODUCTS_V1_BY_SKU_MEDIA_BY_ID, encode(sku), entryId)
                .as(ProductMedia.class);

        return ProductMediaUpdateContext.of(updateRequest, sku, productMedia);
    }

    // helpers
    @Nonnull
    public static String relativeUrl(@NonNull final ProductMedia productMedia) {
        return url(productMedia);
    }

    @Nonnull
    public static String absoluteUrl(@Nonnull final MageConfig config,
                                     @NonNull final ProductMedia productMedia) {
        return url(config.getBaseUrl(), productMedia);
    }

    @Nonnull
    protected static String url(@NonNull final ProductMedia productMedia) {
        return url("", productMedia.getFile());
    }

    @Nonnull
    protected static String url(@NonNull final String fileName) {
        return url("", fileName);
    }

    @Nonnull
    protected static String url(@Nonnull final String baseUrl,
                                @NonNull final ProductMedia productMedia) {
        return url(baseUrl, productMedia.getFile());
    }

    protected static String url(@Nonnull final String baseUrl,
                                @NonNull final String fileName) {
        return baseUrl + "/pub/media/catalog/product/" + fileName;
    }

    @Nonnull
    public static String base64Content(final byte[] buffer) {
        // String base64EncodedData = new String(Base64.encodeBase64(buffer), "UTF-8");
        return Base64.encodeBase64String(buffer);
    }

    @Nonnull
    public static String base64Content(@NonNull final InputStream inputStream) throws IOException {
        final byte[] buffer = inputStream.readAllBytes();
        return base64Content(buffer);
    }
}

