package de.leancoders.magento.common.model.auth;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class UserLoginRequest {

    private final String username;
    private final String password;

}
