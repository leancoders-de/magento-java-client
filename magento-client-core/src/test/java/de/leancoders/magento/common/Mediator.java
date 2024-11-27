package de.leancoders.magento.common;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

/**
 *
 */
@AllArgsConstructor
@Getter
public class Mediator {

    private final String url;
    private final String username;
    private final String password;

    @NonNull
    public static Mediator localAdmin() {
        return new Mediator(
            "http://mr-hear.local/",
            "admin",
            "admin123"
        );
    }

    @NonNull
    public static Mediator localGuest() {
        return new Mediator(
            "http://mr-hear.local/",
            "",
            ""
        );
    }

    @NonNull
    public static Mediator localCustomer() {
        return new Mediator(
            "http://mr-hear.local/",
            "user",
            "user123"
        );
    }

    @NonNull
    public static Mediator design(@NonNull final String username,
                                  @NonNull final String password) {
        return new Mediator(
            "https://design.dev.mr-hear.leancoders.de/",
            username,
            password
        );
    }
}
