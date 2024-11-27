package de.leancoders.magento.client.services.v1;


import com.fasterxml.jackson.core.JsonProcessingException;
import de.leancoders.magento.client.MagentoClient;
import de.leancoders.magento.common.model.cart.Cart;
import de.leancoders.magento.common.model.cart.CartItem;
import de.leancoders.magento.common.model.cart.CartTotal;
import de.leancoders.magento.client.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 *
 */
public class MagentoGuestCartManager extends MagentoHttpComponent {
    protected final MagentoClient client;
    protected String relativePath = "rest/V1/guest-carts";

    public MagentoGuestCartManager(MagentoClient client) {
        super(client.getHttpComponent());
        this.client = client;
    }

    @Override
    public String token() {
        return client.token();
    }


    @Override
    public String baseUri() {
        return client.baseUri();
    }

    public String newCart() {
        String json = postSecure(baseUri() + "/" + relativePath, "");

        if (!validate(json)) {
            return null;
        }

        return StringUtils.stripQuotation(json);
    }

    public Cart getCart(String cartId) throws JsonProcessingException {

        String json = getSecured(baseUri() + "/" + relativePath + "/" + cartId);

        if (!validate(json)) {
            return null;
        }

        System.out.println(json);

        final Cart cart = OBJECT_MAPPER.readValue(json, Cart.class);
        return cart;
    }

    public CartTotal getCartTotal(String cartId) throws JsonProcessingException {
        String json = getSecured(baseUri() + "/" + relativePath + "/" + cartId + "/totals");

        if (!validate(json)) {
            return null;
        }

        System.out.println(json);

        CartTotal cartTotal = OBJECT_MAPPER.readValue(json, CartTotal.class);
        return cartTotal;
    }

    public CartItem addItemToCart(String cartId, CartItem item) throws JsonProcessingException {
        Map<String, Map<String, Object>> request = new HashMap<>();
        Map<String, Object> cartItem = new HashMap<>();
        cartItem.put("qty", item.getQty());
        cartItem.put("sku", item.getSku());
        cartItem.put("quote_id", cartId);
        request.put("cartItem", cartItem);
        String json = OBJECT_MAPPER.writeValueAsString(request);
        json = postSecure(baseUri() + "/" + relativePath + "/" + cartId + "/items", json);

        if (!validate(json)) {
            return null;
        }

        System.out.println(json);

        CartItem saved = OBJECT_MAPPER.readValue(json, CartItem.class);

        return saved;
    }

    public CartItem updateItemInCart(String cartId, CartItem item) throws JsonProcessingException {
        Map<String, Map<String, Object>> request = new HashMap<>();
        Map<String, Object> cartItem = new HashMap<>();
        cartItem.put("qty", item.getQty());
        cartItem.put("sku", item.getSku());
        cartItem.put("quote_id", cartId);
        cartItem.put("item_id", item.getItemId());
        request.put("cartItem", cartItem);
        String json = OBJECT_MAPPER.writeValueAsString(request);
        json = putSecure(baseUri() + "/" + relativePath + "/" + cartId + "/items/" + item.getItemId(), json);

        if (!validate(json)) {
            return null;
        }

        System.out.println(json);

        CartItem saved = OBJECT_MAPPER.readValue(json, CartItem.class);

        return saved;
    }

    public boolean deleteItemInCart(String cartId, int itemId) {
        String json = deleteSecure(baseUri() + "/" + relativePath + "/" + cartId + "/items/" + itemId);

        if (!validate(json)) {
            return false;
        }

        System.out.println(json);

        return json.equalsIgnoreCase("true");
    }
}
