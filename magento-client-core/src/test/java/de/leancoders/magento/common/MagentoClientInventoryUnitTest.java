package de.leancoders.magento.common;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.leancoders.magento.client.MagentoClient;
import de.leancoders.magento.client.helper.jackson.ObjectMapperFactory;
import de.leancoders.magento.common.model.stock.StockItems;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;


/**
 *
 */
@Log4j2
public class MagentoClientInventoryUnitTest {

    private final ObjectMapper MAPPER = ObjectMapperFactory.createDefaultObjectMapper();

    @Test
    public void test_getStockItems() throws JsonProcessingException {
        String productSku = "product_dynamic_571";

        MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);

        log.info("stock item: {}", MAPPER.writeValueAsString(client.inventory().getStockItems(productSku)));

        productSku = "B203-SKU";
        log.info("stock item: {}", MAPPER.writeValueAsString(client.inventory().getStockItems(productSku)));
    }

    @Test
    public void test_saveStockItems() throws JsonProcessingException {
        String productSku = "product_dynamic_571";

        MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);

        productSku = "B203-SKU";
        StockItems si = client.inventory().getStockItems(productSku);
        si.setQty(2);
        String stockId = client.inventory().saveStockItems(productSku, si);
        log.info("stock item saved: {}", stockId);
    }
}
