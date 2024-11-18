package de.leancoders.magento.common;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.leancoders.magento.client.MagentoClient;
import de.leancoders.magento.client.helper.jackson.ObjectMapperFactory;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 *
 */
@Log4j2
public class MagentoClientProductMediaUnitTest {

    private static final ObjectMapper OBJECT_MAPPER = ObjectMapperFactory.createDefaultObjectMapper();

    @Test
    public void test_get_product_media_list() throws JsonProcessingException {
        String productSku = "B202-SKU";
        MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);
        log.info("media list: \r\n{}", OBJECT_MAPPER.writeValueAsString(client.media().getProductMediaList(productSku)));
    }

    @Test
    public void test_get_product_media_urls() throws JsonProcessingException {
        String productSku = "B202-SKU";
        MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);
        log.info("media absolute urls: \r\n{}", OBJECT_MAPPER.writeValueAsString(client.media().getProductMediaAbsoluteUrls(productSku)));
        log.info("media relative urls: \r\n{}", OBJECT_MAPPER.writeValueAsString(client.media().getProductMediaRelativeUrls(productSku)));
    }

    @Test
    public void test_get_product_media_url() throws JsonProcessingException {
        String productSku = "B202-SKU";
        MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);
        long entryId = 1L;
        log.info("media absoluate url: \r\n{}", OBJECT_MAPPER.writeValueAsString(client.media().getProductMediaAbsoluteUrl(productSku, entryId)));
        log.info("media relative url: \r\n{}", OBJECT_MAPPER.writeValueAsString(client.media().getProductMediaRelativeUrl(productSku, entryId)));
    }

    @Test
    public void test_get_product_media() throws JsonProcessingException {
        String productSku = "B202-SKU";
        MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);
        long entryId = 1L;
        log.info("media: \r\n{}", OBJECT_MAPPER.writeValueAsString(client.media().getProductMedia(productSku, entryId)));
    }

    @Test
    public void test_delete_product_media() throws JsonProcessingException {
        String productSku = "B202-SKU";
        MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);
        long entryId = 2L;
        log.info("media deleted: \r\n{}", OBJECT_MAPPER.writeValueAsString(client.media().deleteProductMedia(productSku, entryId)));
    }

    @Test
    public void test_upload_image() throws IOException {
        String productSku = "B202-SKU";

        MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);

        String filename = "/m/b/mb01-blue-0.png";
        int position = 1;
        String type = "image/png";
        String imageFileName = "new_image.png";

        InputStream inputStream = MagentoClientProductUnitTest.class.getClassLoader().getResourceAsStream("sample.png");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int length;
        byte[] bytes = new byte[1024];
        while ((length = inputStream.read(bytes, 0, 1024)) > 0) {
            baos.write(bytes, 0, length);
        }
        bytes = baos.toByteArray();
        log.info("uploaded image id: {}", client.media().uploadProductImage(productSku, position, filename, bytes, type, imageFileName));
    }

    @Test
    public void test_update_image() throws IOException {
        String productSku = "B202-SKU";

        MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);

        String filename = "/m/b/mb01-blue-0.png";
        int position = 1;
        String type = "image/png";
        String imageFileName = "new_image.png";

        InputStream inputStream = MagentoClientProductUnitTest.class.getClassLoader().getResourceAsStream("sample.png");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int length;
        byte[] bytes = new byte[1024];
        while ((length = inputStream.read(bytes, 0, 1024)) > 0) {
            baos.write(bytes, 0, length);
        }
        bytes = baos.toByteArray();
        long entryId = 3L;
        log.info("image updated: {}", client.media().updateProductImage(productSku, entryId, position, filename, bytes, type, imageFileName));
    }

}
