package de.leancoders.magento.client;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import de.leancoders.magento.client.models.StockItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


/**
 */
public class MagentoClientInventoryUnitTest {

   private static final Logger logger = LoggerFactory.getLogger(MagentoClientInventoryUnitTest.class);
   @Test
   public void test_getStockItems(){
      String productSku = "product_dynamic_571";

      MagentoClient client = new MagentoClient(Mediator.url);
      client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);
      logger.info("stock item: {}", JSON.toJSONString(client.inventory().getStockItems(productSku), SerializerFeature.PrettyFormat));

      productSku = "B203-SKU";
      logger.info("stock item: {}", JSON.toJSONString(client.inventory().getStockItems(productSku), SerializerFeature.PrettyFormat));
   }

   @Test
   public void test_saveStockItems(){
      String productSku = "product_dynamic_571";

      MagentoClient client = new MagentoClient(Mediator.url);
      client.loginAsAdmin(Mediator.adminUsername, Mediator.adminPassword);

      productSku = "B203-SKU";
      StockItems si = client.inventory().getStockItems(productSku);
      si.setQty(2);
      String stockId = client.inventory().saveStockItems(productSku, si);
      logger.info("stock item saved: {}", stockId);
   }
}
