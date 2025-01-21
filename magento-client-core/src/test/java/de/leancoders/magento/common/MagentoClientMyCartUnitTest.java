package de.leancoders.magento.common;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.leancoders.magento.client.MagentoClient;
import de.leancoders.magento.client.helper.jackson.ObjectMapperFactory;
import de.leancoders.magento.common.model.cart.Cart;
import de.leancoders.magento.common.model.cart.CartItem;
import de.leancoders.magento.common.model.cart.CartTotal;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;


/**
 *
 */
@Log4j2
public class MagentoClientMyCartUnitTest {

    private static final ObjectMapper OBJECT_MAPPER = ObjectMapperFactory.createDefaultObjectMapper();
    private static final Mediator LOCAL_CUSTOMER = Mediator.localCustomer();

    @Test
    public void test_newCart() throws JsonProcessingException {
        final MagentoClient client = new MagentoClient(LOCAL_CUSTOMER.getUrl());
        final String token = client.loginAsClient(LOCAL_CUSTOMER.getUsername(), LOCAL_CUSTOMER.getPassword());
        final String cartId = client.myCart().newQuote();
        final Cart cart = client.myCart().getCart();
        final CartTotal cartTotal = client.myCart().getCartTotal();

        log.info("token: {}", token);
        log.info("cart: \r\n{}", OBJECT_MAPPER.writeValueAsString(cart));
        log.info("cartTotal: \r\n{}", OBJECT_MAPPER.writeValueAsString(cartTotal));
    }

    @Test
    public void test_addItemToCart() throws JsonProcessingException {
        final MagentoClient client = new MagentoClient(LOCAL_CUSTOMER.getUrl());
        client.loginAsClient(LOCAL_CUSTOMER.getUsername(), LOCAL_CUSTOMER.getPassword());
        final String quoteId = client.myCart().newQuote();

        CartItem item = new CartItem();
        item.setQty(1);
        item.setSku("product_dynamic_758");

        System.out.println(quoteId);

        item = client.myCart().addItemToCart(quoteId, item);

        final Cart cart = client.myCart().getCart();
        final CartTotal cartTotal = client.myCart().getCartTotal();

        log.info("cartItem: \r\n{}", OBJECT_MAPPER.writeValueAsString(item));
        log.info("cart: \r\n{}", OBJECT_MAPPER.writeValueAsString(cart));
        log.info("cartTotal: \r\n{}", OBJECT_MAPPER.writeValueAsString(cartTotal));
    }

    @Test
    public void test_updateItemInCart() throws JsonProcessingException {
        final MagentoClient client = new MagentoClient(LOCAL_CUSTOMER.getUrl());
        client.loginAsClient(LOCAL_CUSTOMER.getUsername(), LOCAL_CUSTOMER.getPassword());
        final String quoteId = client.myCart().newQuote();

        CartItem item = new CartItem();
        item.setQty(1);
        item.setSku("product_dynamic_758");

        item = client.myCart().addItemToCart(quoteId, item);
        item.setQty(3);
        item = client.myCart().updateItemInCart(quoteId, item);

        final Cart cart = client.myCart().getCart();
        final CartTotal cartTotal = client.myCart().getCartTotal();

        log.info("cartItem: \r\n{}", OBJECT_MAPPER.writeValueAsString(item));
        log.info("cart: \r\n{}", OBJECT_MAPPER.writeValueAsString(cart));
        log.info("cartTotal: \r\n{}", OBJECT_MAPPER.writeValueAsString(cartTotal));
    }

    @Test
    public void test_deleteItemInCart() throws JsonProcessingException {
        final MagentoClient client = new MagentoClient(LOCAL_CUSTOMER.getUrl());
        client.loginAsClient(LOCAL_CUSTOMER.getUsername(), LOCAL_CUSTOMER.getPassword());
        final String quoteId = client.myCart().newQuote();

        CartItem item = new CartItem();
        item.setQty(1);
        item.setSku("product_dynamic_758");

        item = client.myCart().addItemToCart(quoteId, item);
        final boolean result = client.myCart().deleteItemInCart(item.getItemId());

        final Cart cart = client.myCart().getCart();
        final CartTotal cartTotal = client.myCart().getCartTotal();

        log.info("result: {}", (Object) result);
        log.info("cart: \r\n{}", OBJECT_MAPPER.writeValueAsString(cart));
        log.info("cartTotal: \r\n{}", OBJECT_MAPPER.writeValueAsString(cartTotal));
    }

    @Test
    public void test_transferGuestCartToMyCart() throws JsonProcessingException {
        final MagentoClient client = new MagentoClient(LOCAL_CUSTOMER.getUrl());

        final String cartId = client.guestCart().newCart();

        final CartItem item = new CartItem();
        item.setQty(1);
        item.setSku("product_dynamic_758");

        final CartItem itemResponse = client.guestCart().addItemToCart(cartId, item);

        client.loginAsClient(LOCAL_CUSTOMER.getUsername(), LOCAL_CUSTOMER.getPassword());
        final boolean result = client.myCart().transferGuestCartToMyCart(cartId);

        final Cart cart = client.myCart().getCart();
        final CartTotal cartTotal = client.myCart().getCartTotal();

        log.info("result: {}", (Object) result);
        log.info("cart: \r\n{}", OBJECT_MAPPER.writeValueAsString(cart));
        log.info("cartTotal: \r\n{}", OBJECT_MAPPER.writeValueAsString(cartTotal));
    }
}
