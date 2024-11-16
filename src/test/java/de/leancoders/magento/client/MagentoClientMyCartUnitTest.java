package de.leancoders.magento.client;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import de.leancoders.magento.client.models.cart.Cart;
import de.leancoders.magento.client.models.cart.CartItem;
import de.leancoders.magento.client.models.cart.CartTotal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


/**
 */
public class MagentoClientMyCartUnitTest {
   private static final Logger logger = LoggerFactory.getLogger(MagentoClientMyCartUnitTest.class);

   @Test
   public void test_newCart(){
      MagentoClient client = new MagentoClient(Mediator.url);
      String token = client.loginAsClient(Mediator.customerUsername, Mediator.customerPassword);
      String cartId = client.myCart().newQuote();
      Cart cart = client.myCart().getCart();
      CartTotal cartTotal = client.myCart().getCartTotal();

      logger.info("token: {}", token);
      logger.info("cart: \r\n{}", JSON.toJSONString(cart, SerializerFeature.PrettyFormat));
      logger.info("cartTotal: \r\n{}", JSON.toJSONString(cartTotal, SerializerFeature.PrettyFormat));
   }

   @Test
   public void test_addItemToCart(){
      MagentoClient client = new MagentoClient(Mediator.url);
      client.loginAsClient(Mediator.customerUsername, Mediator.customerPassword);
      String quoteId = client.myCart().newQuote();

      CartItem item = new CartItem();
      item.setQty(1);
      item.setSku("product_dynamic_758");

      System.out.println(quoteId);

      item = client.myCart().addItemToCart(quoteId, item);

      Cart cart = client.myCart().getCart();
      CartTotal cartTotal = client.myCart().getCartTotal();

      logger.info("cartItem: \r\n{}", JSON.toJSONString(item, SerializerFeature.PrettyFormat));
      logger.info("cart: \r\n{}", JSON.toJSONString(cart, SerializerFeature.PrettyFormat));
      logger.info("cartTotal: \r\n{}", JSON.toJSONString(cartTotal, SerializerFeature.PrettyFormat));
   }

   @Test
   public void test_updateItemInCart(){
      MagentoClient client = new MagentoClient(Mediator.url);
      client.loginAsClient(Mediator.customerUsername, Mediator.customerPassword);
      String quoteId = client.myCart().newQuote();

      CartItem item = new CartItem();
      item.setQty(1);
      item.setSku("product_dynamic_758");

      item = client.myCart().addItemToCart(quoteId, item);
      item.setQty(3);
      item = client.myCart().updateItemInCart(quoteId, item);

      Cart cart = client.myCart().getCart();
      CartTotal cartTotal = client.myCart().getCartTotal();

      logger.info("cartItem: \r\n{}", JSON.toJSONString(item, SerializerFeature.PrettyFormat));
      logger.info("cart: \r\n{}", JSON.toJSONString(cart, SerializerFeature.PrettyFormat));
      logger.info("cartTotal: \r\n{}", JSON.toJSONString(cartTotal, SerializerFeature.PrettyFormat));
   }

   @Test
   public void test_deleteItemInCart(){
      MagentoClient client = new MagentoClient(Mediator.url);
      client.loginAsClient(Mediator.customerUsername, Mediator.customerPassword);
      String quoteId = client.myCart().newQuote();

      CartItem item = new CartItem();
      item.setQty(1);
      item.setSku("product_dynamic_758");

      item = client.myCart().addItemToCart(quoteId, item);
      boolean result = client.myCart().deleteItemInCart(item.getItem_id());


      Cart cart = client.myCart().getCart();
      CartTotal cartTotal = client.myCart().getCartTotal();

      logger.info("result: {}", result);
      logger.info("cart: \r\n{}", JSON.toJSONString(cart, SerializerFeature.PrettyFormat));
      logger.info("cartTotal: \r\n{}", JSON.toJSONString(cartTotal, SerializerFeature.PrettyFormat));
   }

   @Test
   public void test_transferGuestCartToMyCart(){
      MagentoClient client = new MagentoClient(Mediator.url);

      String cartId = client.guestCart().newCart();

      CartItem item = new CartItem();
      item.setQty(1);
      item.setSku("product_dynamic_758");

      item = client.guestCart().addItemToCart(cartId, item);

      client.loginAsClient(Mediator.customerUsername, Mediator.customerPassword);
      boolean result = client.myCart().transferGuestCartToMyCart(cartId);

      Cart cart = client.myCart().getCart();
      CartTotal cartTotal = client.myCart().getCartTotal();

      logger.info("result: {}", result);
      logger.info("cart: \r\n{}", JSON.toJSONString(cart, SerializerFeature.PrettyFormat));
      logger.info("cartTotal: \r\n{}", JSON.toJSONString(cartTotal, SerializerFeature.PrettyFormat));
   }
}
