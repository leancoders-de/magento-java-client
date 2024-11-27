package de.leancoders.magento.client.model.internal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.annotation.Nonnull;

@AllArgsConstructor(staticName = "of")
@Data
public class MageAuthContext {

    private final boolean authenticated;

    @NonNull
    private final String username;
    @NonNull
    private final String password;
    @NonNull
    private final String token;

    @Nonnull
    public static MageAuthContext success(@NonNull final String username,
                                          @NonNull final String password,
                                          @NonNull final String token) {
        return MageAuthContext.of(true, username, password, token);
    }

    @Nonnull
    public static MageAuthContext empty() {
        return MageAuthContext.of(false, "", "", "");
    }
}
