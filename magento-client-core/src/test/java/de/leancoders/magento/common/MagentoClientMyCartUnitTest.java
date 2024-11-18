package de.leancoders.magento.common;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.leancoders.magento.client.MagentoClient;
import de.leancoders.magento.client.helper.jackson.ObjectMapperFactory;
import de.leancoders.magento.common.model.cart.Cart;
import de.leancoders.magento.common.model.cart.CartItem;
import de.leancoders.magento.common.model.cart.CartTotal;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;


/**
 *
 */
@Log4j2
public class MagentoClientMyCartUnitTest {

    private static final ObjectMapper OBJECT_MAPPER = ObjectMapperFactory.createDefaultObjectMapper();

    @Test
    public void test_newCart() throws JsonProcessingException {
        MagentoClient client = new MagentoClient(Mediator.url);
        String token = client.loginAsClient(Mediator.customerUsername, Mediator.customerPassword);
        String cartId = client.myCart().newQuote();
        Cart cart = client.myCart().getCart();
        CartTotal cartTotal = client.myCart().getCartTotal();

        log.info("token: {}", token);
        log.info("cart: \r\n{}", OBJECT_MAPPER.writeValueAsString(cart));
        log.info("cartTotal: \r\n{}", OBJECT_MAPPER.writeValueAsString(cartTotal));
    }

    @Test
    public void test_addItemToCart() throws JsonProcessingException {
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

        log.info("cartItem: \r\n{}", OBJECT_MAPPER.writeValueAsString(item));
        log.info("cart: \r\n{}", OBJECT_MAPPER.writeValueAsString(cart));
        log.info("cartTotal: \r\n{}", OBJECT_MAPPER.writeValueAsString(cartTotal));
    }

    @Test
    public void test_updateItemInCart() throws JsonProcessingException {
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

        log.info("cartItem: \r\n{}", OBJECT_MAPPER.writeValueAsString(item));
        log.info("cart: \r\n{}", OBJECT_MAPPER.writeValueAsString(cart));
        log.info("cartTotal: \r\n{}", OBJECT_MAPPER.writeValueAsString(cartTotal));
    }

    @Test
    public void test_deleteItemInCart() throws JsonProcessingException {
        MagentoClient client = new MagentoClient(Mediator.url);
        client.loginAsClient(Mediator.customerUsername, Mediator.customerPassword);
        String quoteId = client.myCart().newQuote();

        CartItem item = new CartItem();
        item.setQty(1);
        item.setSku("product_dynamic_758");

        item = client.myCart().addItemToCart(quoteId, item);
        boolean result = client.myCart().deleteItemInCart(item.getItemId());


        Cart cart = client.myCart().getCart();
        CartTotal cartTotal = client.myCart().getCartTotal();

        log.info("result: {}", result);
        log.info("cart: \r\n{}", OBJECT_MAPPER.writeValueAsString(cart));
        log.info("cartTotal: \r\n{}", OBJECT_MAPPER.writeValueAsString(cartTotal));
    }

    @Test
    public void test_transferGuestCartToMyCart() throws JsonProcessingException {
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

        log.info("result: {}", result);
        log.info("cart: \r\n{}", OBJECT_MAPPER.writeValueAsString(cart));
        log.info("cartTotal: \r\n{}", OBJECT_MAPPER.writeValueAsString(cartTotal));
    }
}
