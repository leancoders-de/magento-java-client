package de.leancoders.magento.common;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.leancoders.magento.client.MagentoClient;
import de.leancoders.magento.client.helper.jackson.ObjectMapperFactory;
import de.leancoders.magento.common.model.category.Category;
import de.leancoders.magento.common.model.category.CategoryProduct;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import java.util.List;


/**
 *
 */
@Log4j2
public class MagentoClientCategoryUnitTest {

    private static final ObjectMapper OBJECT_MAPPER = ObjectMapperFactory.createDefaultObjectMapper();

    @Test
    public void test_get_category_by_id() throws JsonProcessingException {
        long id = 15;

        MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);
        Category category = client.categories().getCategoryByIdClean(id);
        log.info("category:\r\n{}", OBJECT_MAPPER.writeValueAsString(category));

        category = client.categories().getCategoryByIdWithChildren(id);
        log.info("category:\r\n{}", OBJECT_MAPPER.writeValueAsString(category));
    }

    @Test
    public void test_delete_category_by_id() throws JsonProcessingException {
        long id = 15;

        MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);
        boolean deleted = client.categories().deleteCategory(id);
        log.info("category deleted: {}", deleted);
    }

    @Test
    public void test_list_categories() throws JsonProcessingException {
        MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);

        Category page = client.categories().all();
        log.info("categories: {}\r\n", OBJECT_MAPPER.writeValueAsString(page));
    }

    @Test
    public void test_list_products_in_category() throws JsonProcessingException {
        long id = 15;
        MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);

        List<CategoryProduct> products = client.categories().getProductsInCategory(id);
        log.info("products in category 15:\r\n{}", OBJECT_MAPPER.writeValueAsString(products));
    }

    @Test
    public void add_product_to_category() throws JsonProcessingException {
        final long categoryId = 15;
        final MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);

        final String productSku = "B202-SKU";
        final boolean added = client.categories().addProductToCategory(categoryId, productSku, 1);
        log.info("added ? {}", added);
    }

    @Test
    public void delete_product_from_category() throws JsonProcessingException {
        final long categoryId = 15;
        final MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);

        final String productSku = "B202-SKU";
        final boolean removed = client.categories().removeProductFromCategory(categoryId, productSku);
        log.info("removed ? {}", removed);
    }

}
