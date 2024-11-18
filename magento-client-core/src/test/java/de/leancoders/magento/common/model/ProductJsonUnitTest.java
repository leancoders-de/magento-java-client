package de.leancoders.magento.common.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.leancoders.magento.client.helper.jackson.ObjectMapperFactory;
import de.leancoders.magento.common.model.product.MagentoAttribute;
import de.leancoders.magento.common.model.product.Product;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Log4j2
public class ProductJsonUnitTest {

    private static final ObjectMapper OBJECT_MAPPER = ObjectMapperFactory.createDefaultObjectMapper();

    @Test
    public void testJsonDeserialization() throws JsonProcessingException {
        String json = readStream(ProductJsonUnitTest.class.getClassLoader().getResourceAsStream("product.json"));
        log.info("json: {}", json);
        Product product = OBJECT_MAPPER.readValue(json, Product.class);

        log.info("sku: {}", product.getSku());
        for (MagentoAttribute ma : product.getCustomAttributes()) {
            log.info("custom attribute: key = {}, value = {}", ma.getAttribute_code(), ma.getValue());
        }
        for (MagentoAttribute ma : product.getExtensionAttributes()) {
            log.info("extension attribute: key = {}, value = {}", ma.getAttribute_code(), ma.getValue());
        }
    }

    private String readStream(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        catch (IOException ioex) {
            log.error("Failed to read stream", ioex);
        }
        return sb.toString();
    }

}
