package de.leancoders.magento.client.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import de.leancoders.magento.client.MagentoClient;
import de.leancoders.magento.client.models.internal.MagentoAttributeType;
import de.leancoders.magento.client.models.internal.MagentoType;
import de.leancoders.magento.client.models.internal.product.Product;
import de.leancoders.magento.client.models.internal.search.ProductAttributePage;
import de.leancoders.magento.client.models.internal.search.ProductPage;
import de.leancoders.magento.client.models.request.ProductUpdateRequest;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import javax.annotation.Nonnull;
import java.util.List;

import static de.leancoders.magento.client.services.MagePaths.PRODUCTS_V1;


/**
 *
 */
@Log4j2
public class MagentoProductManager extends MagentoHttpComponent {


    private final MagentoClient client;

    public MagentoProductManager(MagentoClient client) {
        super(client.getHttpComponent());
        this.client = client;
    }

    @Override
    public String token() {
        return client.token();
    }


    @Override
    public String baseUri() {
        return client.baseUri();
    }


    public ProductPage page(int pageIndex, int pageSize) {
        String uri = baseUri() + PRODUCTS_V1
            + "?searchCriteria[currentPage]=" + pageIndex
            + "&searchCriteria[pageSize]=" + pageSize;
        String json = getSecured(uri);
        if (!validate(json)) {
            return null;
        }

        return JSON.parseObject(json, ProductPage.class);
    }


    public Product getProductBySku(String sku) {
        String uri = baseUri() + PRODUCTS_V1 + encode(sku);
        String json = getSecured(uri);

        if (!validate(json)) {
            return null;
        }

        System.out.println("output: " + json);

        return JSON.parseObject(json, Product.class);
    }


    public List<MagentoAttributeType> getProductAttributeTypes() {
        String uri = baseUri() + PRODUCTS_V1 + "attributes/types";

        String json = getSecured(uri);

        if (!validate(json)) {
            return null;
        }

        return JSON.parseArray(json, MagentoAttributeType.class);
    }

    public ProductAttributePage getProductAttributes(int pageIndex, int pageSize) {
        String uri = baseUri() + PRODUCTS_V1 + "attributes"
            + "?searchCriteria[currentPage]=" + pageIndex
            + "&searchCriteria[pageSize]=" + pageSize;

        String json = getSecured(uri);

        if (!validate(json)) {
            return null;
        }

        return JSON.parseObject(json, ProductAttributePage.class);

    }

    public boolean hasProduct(String sku) {
        return getProductBySku(sku) != null;
    }

    @Nonnull
    public Product saveProduct(@NonNull final Product product) throws JsonProcessingException {
        final String sku = product.getSku();
        final String url = baseUri() + PRODUCTS_V1 + encode(sku);

        /*
        final Map<String, Object> detail = new HashMap<>();
        detail.put("sku", product.getSku());
        detail.put("name", product.getName());
        detail.put("price", product.getPrice());
        detail.put("status", product.getStatus().getMagentoRepresentation());
        detail.put("type_id", product.getProductType().getMagentoRepresentation());
        detail.put("attribute_set_id", product.getAttributeSetId());
        detail.put("weight", product.getWeight());
        detail.put("visibility", product.getVisibility().getMagentoRepresentation());
        detail.put("status", product.getStatus().getMagentoRepresentation());
        */

        final ProductUpdateRequest productUpdateRequest = ProductUpdateRequest.of(product);

        final String body = OBJECT_MAPPER.writeValueAsString(productUpdateRequest);

        log.info("posting:\r\n{}", body);
        final String response = putSecure(url, body);

        if (!validate(response)) {
            return null;
        }

        return JSON.parseObject(response, Product.class);
    }


    public String page(String name, String value, String condition_type) {
        String uri = baseUri() + "/" + PRODUCTS_V1
            + "?searchCriteria[filter_groups][0][filters][0][field]=category_gear"
            + "&searchCriteria[filter_groups][0][filters][0][value]=86"
            + "&searchCriteria[filter_groups][0][filters][0][condition_type]=finset";
        return getSecured(uri);
    }

    public List<MagentoType> listProductTypes() {
        String uri = baseUri() + PRODUCTS_V1 + "types"
            + "?searchCriteria[currentPage]=0"
            + "&searchCriteria[pageSize]=1000";
        String json = getSecured(uri);
        return JSON.parseArray(json, MagentoType.class);
    }

    public List<MagentoType> listProductTypes(final int page,
                                              final int pageSize) {
        final String uri = baseUri() + PRODUCTS_V1 + "types"
            + "?searchCriteria[currentPage]=" + page
            + "&searchCriteria[pageSize]=" + pageSize;
        final String json = getSecured(uri);
        return JSON.parseArray(json, MagentoType.class);
    }

    @Nonnull
    public String deleteProduct(@NonNull final String sku) {
        String url = baseUri() + PRODUCTS_V1 + encode(sku);
        return deleteSecure(url);
    }


}
