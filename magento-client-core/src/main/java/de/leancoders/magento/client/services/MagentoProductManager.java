package de.leancoders.magento.client.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import de.leancoders.magento.client.MagentoClient;
import de.leancoders.magento.common.model.MagentoAttributeType;
import de.leancoders.magento.common.model.product.Product;
import de.leancoders.magento.common.model.search.ProductAttributePage;
import de.leancoders.magento.common.model.search.ProductPage;
import de.leancoders.magento.common.request.ProductUpdateRequest;
import de.leancoders.magento.common.response.ProductTypesResponse;
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


    @Nonnull
    public ProductPage page(final int page,
                            final int pageSize) throws JsonProcessingException {

        final String uri = baseUri() + PRODUCTS_V1
            + "?searchCriteria[currentPage]=" + page
            + "&searchCriteria[pageSize]=" + pageSize;

        final String response = getSecured(uri);
        if (!validate(response)) {
            return null;
        }

        return OBJECT_MAPPER.readValue(response, ProductPage.class);
    }


    @Nonnull
    public Product getProductBySku(@NonNull final String sku) throws JsonProcessingException {
        final String uri = baseUri() + PRODUCTS_V1 + encode(sku);
        final String json = getSecured(uri);

        if (!validate(json)) {
            return null;
        }

        return OBJECT_MAPPER.readValue(json, Product.class);
    }


    public List<MagentoAttributeType> getProductAttributeTypes() throws JsonProcessingException {
        final String uri = baseUri() + PRODUCTS_V1 + "attributes/types";

        final String json = getSecured(uri);

        if (!validate(json)) {
            return null;
        }

        return OBJECT_MAPPER.readValue(json, new TypeReference<>() {
        });
    }

    public boolean hasProduct(String sku) throws JsonProcessingException {
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

        return OBJECT_MAPPER.readValue(response, Product.class);
    }


    public String page(String name, String value, String condition_type) {
        String uri = baseUri() + "/" + PRODUCTS_V1
            + "?searchCriteria[filter_groups][0][filters][0][field]=category_gear"
            + "&searchCriteria[filter_groups][0][filters][0][value]=86"
            + "&searchCriteria[filter_groups][0][filters][0][condition_type]=finset";
        return getSecured(uri);
    }


    public ProductAttributePage getProductAttributes(int pageIndex, int pageSize) throws JsonProcessingException {
        String uri = baseUri() + PRODUCTS_V1 + "attributes"
            + "?searchCriteria[currentPage]=" + pageIndex
            + "&searchCriteria[pageSize]=" + pageSize;

        String json = getSecured(uri);

        if (!validate(json)) {
            return null;
        }

        return OBJECT_MAPPER.readValue(json, ProductAttributePage.class);

    }

    @Nonnull
    public ProductTypesResponse listProductTypes() throws JsonProcessingException {
        return listProductTypes(0, 1000);
    }

    @Nonnull
    public ProductTypesResponse listProductTypes(final int page,
                                                 final int pageSize) throws JsonProcessingException {
        final String uri = baseUri() + PRODUCTS_V1 + "types"
            + "?searchCriteria[currentPage]=" + page
            + "&searchCriteria[pageSize]=" + pageSize;
        final String json = getSecured(uri);

        // name, label pair
        return OBJECT_MAPPER.readValue(json, ProductTypesResponse.class);
    }

    @Nonnull
    public String deleteProduct(@NonNull final String sku) {
        String url = baseUri() + PRODUCTS_V1 + encode(sku);
        return deleteSecure(url);
    }


}
