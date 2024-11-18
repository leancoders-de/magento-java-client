package de.leancoders.magento.common;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.leancoders.magento.client.MagentoClient;
import de.leancoders.magento.client.helper.jackson.ObjectMapperFactory;
import de.leancoders.magento.common.model.enums.EProductStatus;
import de.leancoders.magento.common.model.enums.EProductType;
import de.leancoders.magento.common.model.enums.EProductVisibility;
import de.leancoders.magento.common.model.MagentoAttributeType;
import de.leancoders.magento.common.model.product.Product;
import de.leancoders.magento.common.model.search.ProductAttributePage;
import de.leancoders.magento.common.model.search.ProductPage;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;


/**
 *
 */
@Log4j2
public class MagentoClientProductUnitTest {
    private static final ObjectMapper OBJECT_MAPPER = ObjectMapperFactory.createDefaultObjectMapper();

    @Test
    public void test_login_admin() throws JsonProcessingException {
        final MagentoClient client = new MagentoClient(Mediator.url);
        final String token = client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);
        log.info("my account:\r\n{}", OBJECT_MAPPER.writeValueAsString(client.getMyAccount()));
        log.info("product types:\r\n{}", OBJECT_MAPPER.writeValueAsString(client.products().listProductTypes()));
    }

    @Test
    public void test_login_client() throws JsonProcessingException {
        final MagentoClient client = new MagentoClient(Mediator.url);
        final String token = client.loginAsClient(Mediator.customerUsername, Mediator.customerPassword);
        log.info("my account:\r\n{}", OBJECT_MAPPER.writeValueAsString(client.getMyAccount()));
        log.info("product types:\r\n{}", OBJECT_MAPPER.writeValueAsString(client.products().listProductTypes()));
    }

    @Test
    public void test_list_product() throws JsonProcessingException {
        MagentoClient client = new MagentoClient(Mediator.url);
        String token = client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);
        log.info("account with id = 1: {}", client.getAccountById(1));
        log.info("product types: \r\n{}", OBJECT_MAPPER.writeValueAsString(client.products().listProductTypes()));

        ProductPage page = client.products().page(0, 10);
        log.info("product page: \r\n{}", OBJECT_MAPPER.writeValueAsString(page));
        Product p1 = page.getItems().get(0);
        Product p2 = client.products().getProductBySku(p1.getSku());
        log.info("product:\r\n{}", OBJECT_MAPPER.writeValueAsString(p2));
    }

    @Test
    public void test_get_product() throws JsonProcessingException {
        final MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);

        final Product p1 = client.products().getProductBySku("B201-SKU");
        log.info("product:\r\n{}", OBJECT_MAPPER.writeValueAsString(p1));
        final Product p2 = client.products().getProductBySku("B202-SKU");
        log.info("product:\r\n{}", OBJECT_MAPPER.writeValueAsString(p2));
    }

    @Test
    public void test_delete_product() throws JsonProcessingException {
        final MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);

        final String sku = "B203-SKU";
        log.info("product exists ? {}", client.products().hasProduct(sku));
        log.info("client.deleteProduct(sku): {}", client.products().deleteProduct(sku));
        log.info("product exists ? {}", client.products().hasProduct(sku));
    }

    @Test
    public void test_list_product_attribute_types() throws JsonProcessingException {
        MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);

        List<MagentoAttributeType> attributeTypes = client.products().getProductAttributeTypes();
        log.info("product attribute types:\r\n{}", OBJECT_MAPPER.writeValueAsString(attributeTypes));
    }

    @Test
    public void test_list_product_attributes() throws JsonProcessingException {
        MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);

        ProductAttributePage page = client.products().getProductAttributes(0, 10);
        log.info("product attribute types:\r\n{}", OBJECT_MAPPER.writeValueAsString(page));
    }

    @Test
    public void test_add_product() throws JsonProcessingException {
        final MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);

        final String sku = "B203-SKU";
        if (client.products().hasProduct(sku)) {
            log.info("Deleting {}", sku);
            client.products().deleteProduct(sku);
            try {
                Thread.sleep(3000L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        final Product product = new Product();
        product.setSku(sku);
        product.setName("B203");
        product.setPrice(BigDecimal.valueOf(30.00));
        product.setStatus(EProductStatus.STATUS_ENABLED);
        product.setProductType(EProductType.SIMPLE);
        product.setAttributeSetId(4);
        product.setWeight(BigDecimal.ONE);
        product.setVisibility(EProductVisibility.VISIBILITY_BOTH);
        product.setStatus(EProductStatus.STATUS_ENABLED);

        log.info("add product result: {}", OBJECT_MAPPER.writeValueAsString(client.products().saveProduct(product)));
    }


}
