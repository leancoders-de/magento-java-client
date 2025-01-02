package de.leancoders.magento.client.services;

import de.leancoders.magento.client.helper.jackson.ObjectMapperFactory;
import de.leancoders.magento.client.model.internal.MageAuthContext;
import de.leancoders.magento.client.model.internal.MageConfig;
import de.leancoders.magento.common.model.auth.UserLoginRequest;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import lombok.NonNull;

import javax.annotation.Nonnull;

public class MageClientService {

    private final MageConfig config;
    private MageAuthContext mageAuthContext;

    @Nonnull
    public MageClientService(@Nonnull final MageConfig config) {
        this.config = config;
        configureRestAssured();
    }

    //
    protected void configureRestAssured() {
        RestAssured.port = config.getPort();
        RestAssured.baseURI = config.getBaseUrl();
        RestAssured.config =
            RestAssuredConfig.config()
                .objectMapperConfig(
                    new ObjectMapperConfig().jackson2ObjectMapperFactory(
                        (type, s) -> ObjectMapperFactory.createDefaultObjectMapper()
                    ));
    }

    @Nonnull
    public MageClientService loginAsAdmin(String username, String password) {
        obtainAccessToken(username, password, MagePaths.ADMIN_AUTH_V1);
        return this;
    }

    @Nonnull
    public MageClientService loginAsClient(String username, String password) {
        obtainAccessToken(username, password, MagePaths.CUSTOMER_AUTH_V1);
        return this;
    }

    @Nonnull
    public CategoryClientService categories() {
        return new CategoryClientService(config, mageAuthContext);
    }

    @Nonnull
    public CustomerClientService customers() {
        return new CustomerClientService(config, mageAuthContext);
    }

    @Nonnull
    public ProductClientService products() {
        return new ProductClientService(config, mageAuthContext);
    }

    @Nonnull
    public ProductMediaClientService productMedia() {
        return new ProductMediaClientService(config, mageAuthContext);
    }

    @Nonnull
    protected String obtainAccessToken(@NonNull final String username,
                                       @NonNull final String password,
                                       @NonNull final String path) {

        final UserLoginRequest userLoginRequest =
            UserLoginRequest.of(
                username, password
            );

        final String token = RestAssured.given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(userLoginRequest)
            .log().all()
            .expect().statusCode(200)
            .log().all()
            .when()
            .post(path)
            .as(String.class);

        this.mageAuthContext = MageAuthContext.success(username, password, token);

        return token;
    }

}
