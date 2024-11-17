package de.leancoders.magento.client.services;


import de.leancoders.magento.client.MagentoClient;
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
public class MagentoMyCartManager extends MagentoHttpComponent {
    private static final String relativePath = "rest/V1/carts";
    private static final String cartId = "mine";
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
        String json = postSecure(baseUri() + "/" + relativePath + "/" + cartId, "");

        if (!validate(json)) {
            return null;
        }

        return StringUtils.stripQuotation(json);
    }

    public Cart getCart() {

        String json = getSecured(baseUri() + "/" + relativePath + "/" + cartId);

        if (!validate(json)) {
            return null;
        }


        Cart cart = JSON.parseObject(json, Cart.class);
        return cart;
    }

    public CartTotal getCartTotal() {
        String json = getSecured(baseUri() + "/" + relativePath + "/" + cartId + "/totals");

        if (!validate(json)) {
            return null;
        }


        CartTotal cartTotal = JSON.parseObject(json, CartTotal.class);
        return cartTotal;
    }

    public CartItem addItemToCart(String quoteId, CartItem item) {
        Map<String, Map<String, Object>> request = new HashMap<>();
        Map<String, Object> cartItem = new HashMap<>();
        cartItem.put("qty", item.getQty());
        cartItem.put("sku", item.getSku());
        cartItem.put("quote_id", quoteId);
        request.put("cartItem", cartItem);
        String json = JSON.toJSONString(request, SerializerFeature.BrowserCompatible);
        json = postSecure(baseUri() + "/" + relativePath + "/" + cartId + "/items", json);

        if (!validate(json)) {
            return null;
        }


        CartItem saved = JSON.parseObject(json, CartItem.class);

        return saved;
    }

    public CartItem updateItemInCart(String quoteId, CartItem item) {
        Map<String, Map<String, Object>> request = new HashMap<>();
        Map<String, Object> cartItem = new HashMap<>();
        cartItem.put("qty", item.getQty());
        cartItem.put("sku", item.getSku());
        cartItem.put("item_id", item.getItemId());
        cartItem.put("quote_id", quoteId);
        request.put("cartItem", cartItem);
        String json = JSON.toJSONString(request, SerializerFeature.BrowserCompatible);
        json = putSecure(baseUri() + "/" + relativePath + "/" + cartId + "/items/" + item.getItemId(), json);

        if (!validate(json)) {
            return null;
        }


        CartItem saved = JSON.parseObject(json, CartItem.class);

        return saved;
    }

    public boolean deleteItemInCart(int itemId) {

        String json = deleteSecure(baseUri() + "/" + relativePath + "/" + cartId + "/items/" + itemId);

        if (!validate(json)) {
            return false;
        }


        return json.equalsIgnoreCase("true");
    }

    public boolean transferGuestCartToMyCart(String guestCartId) {
        if (customerId == -1L) {
            Account account = client.getMyAccount();
            customerId = account.getId();
            storeId = account.getStoreId();
        }
        Map<String, Object> request = new HashMap<>();
        request.put("customerId", customerId);
        request.put("storeId", storeId);
        String json = JSON.toJSONString(request, SerializerFeature.BrowserCompatible);
        json = putSecure(baseUri() + "/rest/V1/guest-carts/" + guestCartId, json);

        return validate(json);
    }

}
