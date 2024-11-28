package de.leancoders.magento.common;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import de.leancoders.magento.client.MagentoClient;
import de.leancoders.magento.client.helper.jackson.ObjectMapperFactory;
import de.leancoders.magento.client.model.internal.MageConfig;
import de.leancoders.magento.client.model.internal.ProductMediaUpdateContext;
import de.leancoders.magento.client.model.internal.ProductUpdateContext;
import de.leancoders.magento.client.services.MageClientService;
import de.leancoders.magento.client.services.ProductClientService;
import de.leancoders.magento.client.services.ProductMediaClientService;
import de.leancoders.magento.client.services.v1.MagentoProductManager;
import de.leancoders.magento.common.model.MagentoAttributeType;
import de.leancoders.magento.common.model.category.Category;
import de.leancoders.magento.common.model.enums.EMediaType;
import de.leancoders.magento.common.model.enums.EMimeType;
import de.leancoders.magento.common.model.enums.EProductStatus;
import de.leancoders.magento.common.model.enums.EProductType;
import de.leancoders.magento.common.model.enums.EProductVisibility;
import de.leancoders.magento.common.model.product.Product;
import de.leancoders.magento.common.model.product.ProductMedia;
import de.leancoders.magento.common.model.product.ProductMediaContent;
import de.leancoders.magento.common.model.search.ProductAttributePage;
import de.leancoders.magento.common.model.search.ProductPage;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;


/**
 *
 */
@Log4j2
public class MagentoClientProductUnitTest {

    private static final ObjectMapper OBJECT_MAPPER = ObjectMapperFactory.createDefaultObjectMapper();
    public static final String SKU = "PROD-1";

    @Test
    public void test_login_admin2() {
        final MageClientService clientService = new MageClientService(
            MageConfig.of(
                "https://design.dev.mr-hear.leancoders.de/",
                443
            )
        );
        clientService.loginAsAdmin("admin", "admin123");


        final ProductClientService products = clientService.products();

        final ProductPage productsPage = products.products(0, 10);
        System.out.println("productsPage = " + productsPage);

        final Product productBySKU = products.bySKU("PROD-1");
        System.out.println("productBySKU = " + productBySKU);
    }

    @Test
    public void test_categories() {
        final MageClientService clientService = new MageClientService(
            MageConfig.of(
                "https://design.dev.mr-hear.leancoders.de/",
                443
            )
        );
        clientService.loginAsAdmin("admin", "admin123");


        final Category category = clientService.categories().categories(0, 100);

        System.out.println("category = " + category);

    }

    @Test
    public void test_upload() throws IOException {
        final MageClientService clientService = new MageClientService(
            MageConfig.of(
                "https://design.dev.mr-hear.leancoders.de/",
                443
            )
        );
        clientService.loginAsAdmin("admin", "admin123");

        final ProductClientService productClientService = clientService.products();
        final Product productBySKU = productClientService.bySKU(SKU);
        System.out.println("productBySKU = " + productBySKU);

        final ProductMediaClientService productMediaClientService = clientService.productMedia();

        final List<ProductMedia> productMediaEntries = productBySKU.getProductMediaEntries();

        productMediaEntries.forEach(item -> productMediaClientService.deleteProductMedia(SKU, item.getId()));

        // productMediaClientService.deleteProductMedia(SKU);

        final InputStream image1 = MagentoClientProductUnitTest.class.getResourceAsStream("/media/images/leancoders_neg_RGB_h46.png");
        final String image1Base64Content = ProductMediaClientService.base64Content(image1);

        final ProductMedia productMedia = new ProductMedia();
        final ProductMediaContent content = new ProductMediaContent();
        productMedia.setDisabled(false);
        // productMedia.setFile("file");
        productMedia.setLabel("label");
        productMedia.setMediaType(EMediaType.IMAGE);
        productMedia.setTypes(ImmutableList.of(EMediaType.IMAGE, EMediaType.SMALL_IMAGE, EMediaType.THUMBNAIL, EMediaType.SWATCH_IMAGE));
        productMedia.setPosition(2);
        //
        content.setEMimeType(EMimeType.PNG);
        content.setName("leancoders_neg_RGB_h46.png");
        content.setBase64EncodedData(image1Base64Content);

        productMedia.setContent(
            content
        );

        final ProductMediaUpdateContext productMediaUpdateContext1 = productMediaClientService.save(SKU, productMedia);
        System.out.println("productMediaUpdateContext1 = " + productMediaUpdateContext1);

        final Long entryId = productMediaUpdateContext1.getEntryId();

        // modify existing product
        productMedia.setId(entryId);

        final InputStream image2 = MagentoClientProductUnitTest.class.getResourceAsStream("/media/images/leancoders_logo_black.png");
        final String image2Base64Content = ProductMediaClientService.base64Content(image2);
        content.setName("leancoders_logo_black.png");
        content.setBase64EncodedData(image2Base64Content);

        final ProductMediaUpdateContext productMediaUpdateContext2 = productMediaClientService.update(SKU, productMediaUpdateContext1.getEntryId(), productMedia);
        System.out.println("productMediaUpdateContext2 = " + productMediaUpdateContext2);


    }

    @Test
    public void test_login_admin() throws JsonProcessingException {
        final Mediator mediator = Mediator.localAdmin();
        final MagentoClient client = new MagentoClient(mediator.getUrl());
        final String token = client.loginAsAdmin(mediator.getUsername(), mediator.getPassword());
        log.info("my account:\r\n{}", OBJECT_MAPPER.writeValueAsString(client.getMyAccount()));
        log.info("product types:\r\n{}", OBJECT_MAPPER.writeValueAsString(client.products().listProductTypes()));
    }

    @Test
    public void test_login_client() throws JsonProcessingException {
        final Mediator mediator = Mediator.localCustomer();
        final MagentoClient client = new MagentoClient(mediator.getUrl());
        final String token = client.loginAsClient(mediator.getUsername(), mediator.getPassword());
        log.info("my account:\r\n{}", OBJECT_MAPPER.writeValueAsString(client.getMyAccount()));
        log.info("product types:\r\n{}", OBJECT_MAPPER.writeValueAsString(client.products().listProductTypes()));
    }

    @Test
    public void test_list_product() throws JsonProcessingException {
        final Mediator mediator = Mediator.localAdmin();
        final MagentoClient client = new MagentoClient(mediator.getUrl());
        final String token = client.loginAsAdmin(mediator.getUsername(), mediator.getPassword());
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
        final Mediator mediator = Mediator.localAdmin();
        final MagentoClient client = new MagentoClient(mediator.getUrl());
        client.loginAsAdmin(mediator.getUsername(), mediator.getPassword());

        final Product p1 = client.products().getProductBySku("B201-SKU");
        log.info("product:\r\n{}", OBJECT_MAPPER.writeValueAsString(p1));
        final Product p2 = client.products().getProductBySku("B202-SKU");
        log.info("product:\r\n{}", OBJECT_MAPPER.writeValueAsString(p2));
    }

    @Test
    public void test_delete_product() throws JsonProcessingException {
        final Mediator mediator = Mediator.localAdmin();
        final MagentoClient client = new MagentoClient(mediator.getUrl());
        client.loginAsAdmin(mediator.getUsername(), mediator.getPassword());

        final String sku = "B203-SKU";
        final MagentoProductManager products = client.products();
        log.info("product exists ? {}", (Boolean) products.hasProduct(sku));
        log.info("client.deleteProduct(sku): {}", client.products().deleteProduct(sku));
        log.info("product exists ? {}", (Boolean) client.products().hasProduct(sku));
    }

    @Test
    public void test_list_product_attribute_types() throws JsonProcessingException {
        final Mediator mediator = Mediator.localAdmin();
        final MagentoClient client = new MagentoClient(mediator.getUrl());
        client.loginAsAdmin(mediator.getUsername(), mediator.getPassword());

        List<MagentoAttributeType> attributeTypes = client.products().getProductAttributeTypes();
        log.info("product attribute types:\r\n{}", OBJECT_MAPPER.writeValueAsString(attributeTypes));
    }

    @Test
    public void test_list_product_attributes() throws JsonProcessingException {
        final Mediator mediator = Mediator.localAdmin();
        final MagentoClient client = new MagentoClient(mediator.getUrl());
        client.loginAsAdmin(mediator.getUsername(), mediator.getPassword());

        ProductAttributePage page = client.products().getProductAttributes(0, 10);
        log.info("product attribute types:\r\n{}", OBJECT_MAPPER.writeValueAsString(page));
    }

    @Test
    public void test_add_product() throws JsonProcessingException {
        final Mediator mediator = Mediator.localAdmin();
        final MagentoClient client = new MagentoClient(mediator.getUrl());
        client.loginAsAdmin(mediator.getUsername(), mediator.getPassword());

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

        final ProductUpdateContext productUpdateContext = client.products().saveProduct(product);
        log.info("add product result: {}", productUpdateContext.getSku());
    }


}
