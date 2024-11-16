package de.leancoders.magento.client.models;

import de.leancoders.magento.client.models.product.Product;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Log4j2
public class ProductJsonUnitTest {

    @Test
    public void testJsonDeserialization() {
        String json = readStream(ProductJsonUnitTest.class.getClassLoader().getResourceAsStream("product.json"));
        log.info("json: {}", json);
        Product product = JSON.parseObject(json, Product.class);

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
