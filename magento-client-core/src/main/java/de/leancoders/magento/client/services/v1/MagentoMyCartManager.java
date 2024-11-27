package de.leancoders.magento.client.services.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.leancoders.magento.client.MagentoClient;
import de.leancoders.magento.client.services.MagePaths;
import de.leancoders.magento.common.model.account.Account;
import de.leancoders.magento.common.model.cart.Cart;
import de.leancoders.magento.common.model.cart.CartItem;
import de.leancoders.magento.common.model.cart.CartTotal;
import de.leancoders.magento.client.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class MagentoMyCartManager extends MagentoHttpComponent implements MagePaths {
    private static final String MY_CART_ID = "mine";
    protected final MagentoClient client;
    private long customerId = -1L;
    private long storeId = -1L;

    public MagentoMyCartManager(MagentoClient client) {
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

    public String newQuote() {
        final String json = postSecure(baseUri() + CARTS_V1 + MY_CART_ID, "");

        if (!validate(json)) {
            return null;
        }

        return StringUtils.stripQuotation(json);
    }

    public Cart getCart() throws JsonProcessingException {

        String json = getSecured(baseUri() + CARTS_V1 + MY_CART_ID);

        if (!validate(json)) {
            return null;
        }


        Cart cart = OBJECT_MAPPER.readValue(json, Cart.class);
        return cart;
    }

    public CartTotal getCartTotal() throws JsonProcessingException {
        String json = getSecured(baseUri() + CARTS_V1 + MY_CART_ID + "/totals");

        if (!validate(json)) {
            return null;
        }


        CartTotal cartTotal = OBJECT_MAPPER.readValue(json, CartTotal.class);
        return cartTotal;
    }

    public CartItem addItemToCart(String quoteId, CartItem item) throws JsonProcessingException {
        Map<String, Map<String, Object>> request = new HashMap<>();
        Map<String, Object> cartItem = new HashMap<>();
        cartItem.put("qty", item.getQty());
        cartItem.put("sku", item.getSku());
        cartItem.put("quote_id", quoteId);
        request.put("cartItem", cartItem);
        String json = OBJECT_MAPPER.writeValueAsString(request);
        json = postSecure(baseUri() + CARTS_V1 + MY_CART_ID + "/items", json);

        if (!validate(json)) {
            return null;
        }


        CartItem saved = OBJECT_MAPPER.readValue(json, CartItem.class);

        return saved;
    }

    public CartItem updateItemInCart(String quoteId, CartItem item) throws JsonProcessingException {
        Map<String, Map<String, Object>> request = new HashMap<>();
        Map<String, Object> cartItem = new HashMap<>();
        cartItem.put("qty", item.getQty());
        cartItem.put("sku", item.getSku());
        cartItem.put("item_id", item.getItemId());
        cartItem.put("quote_id", quoteId);
        request.put("cartItem", cartItem);
        String json = OBJECT_MAPPER.writeValueAsString(request);
        json = putSecure(baseUri() + CARTS_V1 + MY_CART_ID + "/items/" + item.getItemId(), json);

        if (!validate(json)) {
            return null;
        }


        CartItem saved = OBJECT_MAPPER.readValue(json, CartItem.class);

        return saved;
    }

    public boolean deleteItemInCart(int itemId) {

        String json = deleteSecure(baseUri() + CARTS_V1 + MY_CART_ID + "/items/" + itemId);

        if (!validate(json)) {
            return false;
        }


        return json.equalsIgnoreCase("true");
    }

    public boolean transferGuestCartToMyCart(String guestCartId) throws JsonProcessingException {
        if (customerId == -1L) {
            Account account = client.getMyAccount();
            customerId = account.getId();
            storeId = account.getStoreId();
        }
        Map<String, Object> request = new HashMap<>();
        request.put("customerId", customerId);
        request.put("storeId", storeId);
        String json = OBJECT_MAPPER.writeValueAsString(request);
        json = putSecure(baseUri() + "/rest/V1/guest-carts/" + guestCartId, json);

        return validate(json);
    }

}
