package de.leancoders.magento.client.mapper;


import com.fasterxml.jackson.databind.ObjectMapper;
import de.leancoders.magento.client.helper.jackson.ObjectMapperFactory;
import de.leancoders.magento.common.model.product.Product;

public class ProductMapper extends MagentoMapper<Product> {

    private static final ObjectMapper OBJECT_MAPPER = ObjectMapperFactory.createDefaultObjectMapper();


    public String toJson(Object obj) {
    }
}
