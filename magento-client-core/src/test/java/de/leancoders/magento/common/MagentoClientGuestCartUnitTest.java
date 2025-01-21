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
public class MagentoClientGuestCartUnitTest {

    private static final ObjectMapper OBJECT_MAPPER = ObjectMapperFactory.createDefaultObjectMapper();
    private static final Mediator LOCAL_GUEST = Mediator.localGuest();

    @Test
    public void test_newCart() throws JsonProcessingException {
        final MagentoClient client = new MagentoClient(LOCAL_GUEST.getUrl());
        final String cartId = client.guestCart().newCart();
        final Cart cart = client.guestCart().getCart(cartId);
        final CartTotal cartTotal = client.getGuestCartManager().getCartTotal(cartId);

        log.info("cart: \r\n{}", OBJECT_MAPPER.writeValueAsString(cart));
        log.info("cartTotal: \r\n{}", OBJECT_MAPPER.writeValueAsString(cartTotal));
    }

    @Test
    public void test_addItemToCart() throws JsonProcessingException {
        final MagentoClient client = new MagentoClient(LOCAL_GUEST.getUrl());
        final String cartId = client.guestCart().newCart();

        CartItem item = new CartItem();
        item.setQty(1);
        item.setSku("product_dynamic_758");

        item = client.guestCart().addItemToCart(cartId, item);

        final Cart cart = client.guestCart().getCart(cartId);
        final CartTotal cartTotal = client.getGuestCartManager().getCartTotal(cartId);

        log.info("cartItem: \r\n{}", OBJECT_MAPPER.writeValueAsString(item));
        log.info("cart: \r\n{}", OBJECT_MAPPER.writeValueAsString(cart));
        log.info("cartTotal: \r\n{}", OBJECT_MAPPER.writeValueAsString(cartTotal));
    }

    @Test
    public void test_updateItemInCart() throws JsonProcessingException {
        final MagentoClient client = new MagentoClient(LOCAL_GUEST.getUrl());
        final String cartId = client.guestCart().newCart();

        CartItem item = new CartItem();
        item.setQty(1);
        item.setSku("product_dynamic_758");

        item = client.guestCart().addItemToCart(cartId, item);
        item.setQty(3);
        item = client.guestCart().updateItemInCart(cartId, item);


        final Cart cart = client.guestCart().getCart(cartId);
        final CartTotal cartTotal = client.getGuestCartManager().getCartTotal(cartId);

        log.info("cartItem: \r\n{}", OBJECT_MAPPER.writeValueAsString(item));
        log.info("cart: \r\n{}", OBJECT_MAPPER.writeValueAsString(cart));
        log.info("cartTotal: \r\n{}", OBJECT_MAPPER.writeValueAsString(cartTotal));
    }

    @Test
    public void test_deleteItemInCart() throws JsonProcessingException {
        final MagentoClient client = new MagentoClient(LOCAL_GUEST.getUrl());
        final String cartId = client.guestCart().newCart();

        CartItem item = new CartItem();
        item.setQty(1);
        item.setSku("product_dynamic_758");

        item = client.guestCart().addItemToCart(cartId, item);
        final boolean result = client.guestCart().deleteItemInCart(cartId, item.getItemId());

        final Cart cart = client.guestCart().getCart(cartId);
        final CartTotal cartTotal = client.getGuestCartManager().getCartTotal(cartId);

        log.info("result: {}", (Object) result);
        log.info("cart: \r\n{}", OBJECT_MAPPER.writeValueAsString(cart));
        log.info("cartTotal: \r\n{}", OBJECT_MAPPER.writeValueAsString(cartTotal));
    }


}
