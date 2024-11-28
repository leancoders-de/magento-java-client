package de.leancoders.magento.client.services;

public interface MagePaths {

    String CATEGORIES_V1 = "/rest/V1/categories/";
    String CATEGORIES_V1_PRODUCT_ASSIGN = CATEGORIES_V1 + "{categoryId}/products/";

    String PRODUCTS_V1 = "/rest/V1/products/";
    String PRODUCTS_V1_PAGE = "/rest/V1/products/";
    String CARTS_V1 = "/rest/V1/carts/";
    String PRODUCTS_V1_BY_SKU = "/rest/V1/products/{sku}/";

    String PRODUCTS_V1_BY_SKU_MEDIA = PRODUCTS_V1_BY_SKU + "media/";
    String PRODUCTS_V1_BY_SKU_MEDIA_BY_ID = PRODUCTS_V1_BY_SKU_MEDIA + "{mediaId}/";

    String CUSTOMER_AUTH_V1 = "/rest/V1/integration/customer/token/";
    String ADMIN_AUTH_V1 = "/rest/V1/integration/admin/token/";

}
