package de.leancoders.magento.client.services;

public interface MagePaths {

    String PRODUCTS_V1 = "/rest/V1/products/";
    String PRODUCTS_V1_PAGE = "/rest/V1/products/";
    String CARTS_V1 = "/rest/V1/carts/";
    String PRODUCTS_V1_BY_SKU = "/rest/V1/products/{sku}/";

    String CUSTOMER_AUTH_V1 = "/rest/V1/integration/customer/token/";
    String ADMIN_AUTH_V1 = "/rest/V1/integration/admin/token/";

}
