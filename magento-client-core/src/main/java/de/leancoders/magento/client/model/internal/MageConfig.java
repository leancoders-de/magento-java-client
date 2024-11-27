package de.leancoders.magento.client.model.internal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.annotation.Nonnull;

@AllArgsConstructor(staticName = "of")
@Data
public class MageConfig {

    @NonNull
    private final String baseUrl;
    private final int port;

    @Nonnull
    public static MageConfig empty() {
        return MageConfig.of("", 0);
    }
}
