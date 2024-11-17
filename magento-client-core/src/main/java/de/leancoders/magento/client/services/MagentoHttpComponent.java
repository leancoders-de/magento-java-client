package de.leancoders.magento.client.services;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.leancoders.magento.client.helper.jackson.ObjectMapperFactory;
import de.leancoders.magento.client.utils.StringUtils;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import javax.annotation.Nonnull;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


/**
 *
 */
@Log4j2
public abstract class MagentoHttpComponent {

    protected static final ObjectMapper OBJECT_MAPPER = ObjectMapperFactory.createDefaultObjectMapper();
    protected HttpComponent httpComponent;

    public MagentoHttpComponent(HttpComponent httpComponent) {
        this.httpComponent = httpComponent;
    }

    public abstract String token();

    public abstract String baseUri();

    public HttpComponent getHttpComponent() {
        return httpComponent;
    }

    public void setHttpComponent(HttpComponent httpComponent) {
        this.httpComponent = httpComponent;
    }

    @Nonnull
    public String postSecure(@NonNull final String url, @NonNull final String body) {
        final Map<String, String> headers = new HashMap<>();
        if (!StringUtils.isEmpty(this.token())) {
            headers.put("Authorization", "Bearer " + this.token());
        }
        headers.put("Content-Type", "application/json");
        return httpComponent.post(url, body, headers);
    }

    @Nonnull
    public String putSecure(@NonNull final String url, @NonNull final String body) {
        final Map<String, String> headers = new HashMap<>();
        if (!StringUtils.isEmpty(this.token())) {
            headers.put("Authorization", "Bearer " + this.token());
        }
        headers.put("Content-Type", "application/json");
        return httpComponent.put(url, body, headers);
    }

    @Nonnull
    public String deleteSecure(@NonNull final String url) {
        Map<String, String> headers = new HashMap<>();
        if (!StringUtils.isEmpty(this.token())) {
            headers.put("Authorization", "Bearer " + this.token());
        }
        headers.put("Content-Type", "application/json");
        return httpComponent.delete(url, headers);
    }

    @Nonnull
    public String getSecured(@NonNull final String uri) {
        final Map<String, String> headers = new HashMap<>();
        if (!StringUtils.isEmpty(this.token())) {
            headers.put("Authorization", "Bearer " + this.token());
        }
        headers.put("Content-Type", "application/json");
        return httpComponent.get(uri, headers);
    }

    @Nonnull
    public String encode(@NonNull final String text) {
        return URLEncoder.encode(text, StandardCharsets.UTF_8);
    }

    protected boolean validate(String json) {
        try {
            Map<String, Object> data = JSON.parseObject(json, new TypeReference<Map<String, Object>>() {
            }.getType());

            if (data.containsKey("message")) {
                log.error("query failed: {}", data.get("message"));
                log.warn("trace: {}", data.get("trace"));
                return false;
            }
        }
        catch (JSONException exception) {
            return true;
        }
        return true;
    }
}
