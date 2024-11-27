package de.leancoders.magento.client;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import de.leancoders.magento.common.model.account.Account;
import de.leancoders.magento.client.services.v1.BasicHttpComponent;
import de.leancoders.magento.client.services.v1.HttpComponent;
import de.leancoders.magento.client.services.v1.MagentoCategoryManager;
import de.leancoders.magento.client.services.v1.MagentoGuestCartManager;
import de.leancoders.magento.client.services.v1.MagentoHttpComponent;
import de.leancoders.magento.client.services.v1.MagentoInventoryStockManager;
import de.leancoders.magento.client.services.v1.MagentoMyCartManager;
import de.leancoders.magento.client.services.v1.MagentoProductManager;
import de.leancoders.magento.client.services.v1.MagentoProductMediaManager;
import de.leancoders.magento.client.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 *
 */
@Getter
@Setter
@Log4j2
public class MagentoClient extends MagentoHttpComponent implements Serializable {

    private static final String relativePath4LoginAsClient = "rest/V1/integration/customer/token";
    private static final String relativePath4LoginAsAdmin = "rest/V1/integration/admin/token";

    private String token = null;
    private String baseUri = "";
    private boolean admin = false;
    private boolean authenticated = false;

    private MagentoProductManager productManager;
    private MagentoCategoryManager categoryManager;
    private MagentoInventoryStockManager inventoryStockManager;
    private MagentoProductMediaManager productMediaManager;
    private MagentoGuestCartManager guestCartManager;
    private MagentoMyCartManager myCartManager;

    public MagentoClient(String baseUri, HttpComponent httpComponent) {
        super(httpComponent);

        this.baseUri = baseUri;
        this.productManager = new MagentoProductManager(this);
        this.categoryManager = new MagentoCategoryManager(this);
        this.inventoryStockManager = new MagentoInventoryStockManager(this);
        this.productMediaManager = new MagentoProductMediaManager(this);
        this.guestCartManager = new MagentoGuestCartManager(this);
        this.myCartManager = new MagentoMyCartManager(this);
    }

    public MagentoClient(String baseUri) {
        super(new BasicHttpComponent());

        this.baseUri = baseUri;
        this.productManager = new MagentoProductManager(this);
        this.categoryManager = new MagentoCategoryManager(this);
        this.inventoryStockManager = new MagentoInventoryStockManager(this);
        this.productMediaManager = new MagentoProductMediaManager(this);
        this.guestCartManager = new MagentoGuestCartManager(this);
        this.myCartManager = new MagentoMyCartManager(this);
    }

    public Account getMyAccount() throws JsonProcessingException {
        if (admin) {
            log.warn("my account access api is not supported for admin rest call");
            return null;
        }

        //"http://magento.ll/index.php/rest/V1/customers/me" -H "Authorization: Bearer asdf3hjklp5iuytre"
        String uri = this.baseUri + "/rest/V1/customers/me";
        String json = getSecured(uri);

        if (!validate(json)) {
            return null;
        }

        return OBJECT_MAPPER.readValue(json, Account.class);
    }

    public Map<String, Object> getAccountById(long id) throws JsonProcessingException {
        if (!admin) {
            log.warn("other account access api is not supported for client rest call");
            return new HashMap<>();
        }

        final String uri = this.baseUri + "/rest/V1/customers/" + id;
        final String json = getSecured(uri);
        final Map<String, Object> data = OBJECT_MAPPER.readValue(json, new TypeReference<>() {
        });
        return data;
    }

    public String loginAsClient(String username, String password) throws JsonProcessingException {
        final String uri = baseUri + relativePath4LoginAsClient;
        final Map<String, String> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);
        this.token = StringUtils.stripQuotation(httpComponent.jsonPost(uri, data));
        log.info("loginAsClient returns: {}", token);

        if (token.contains("You did not sign in correctly or your account is temporarily disabled") || token.contains("Invalid login or password")) {
            this.token = "";
            return token;
        }
        authenticated = true;
        return token;
    }

    public void logout() {
        //String uri = baseUri + "/rest/V1/integration/customer/revoke";
        authenticated = false;
        token = null;

    }

    public String loginAsAdmin(String username, String password) throws JsonProcessingException {
        String uri = baseUri + relativePath4LoginAsAdmin;
        Map<String, String> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);
        token = StringUtils.stripQuotation(httpComponent.jsonPost(uri, data));
        log.info("loginAsClient returns: {}", token);
        if (token.contains("You did not sign in correctly or your account is temporarily disabled") || token.contains("Invalid login or password")) {
            this.token = "";
            return token;
        }
        authenticated = true;
        return token;
    }

    public MagentoCategoryManager categories() {
        return categoryManager;
    }

    public MagentoProductManager products() {
        return productManager;
    }

    public MagentoInventoryStockManager inventory() {
        return inventoryStockManager;
    }

    public MagentoProductMediaManager media() {
        return productMediaManager;
    }

    public MagentoGuestCartManager guestCart() {
        return guestCartManager;
    }

    public MagentoMyCartManager myCart() {
        return myCartManager;
    }


    @Override
    public String token() {
        return this.token;
    }


    @Override
    public String baseUri() {
        return this.baseUri;
    }
}
