package de.leancoders.magento.common.model.enums;


import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.NonNull;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static de.leancoders.magento.common.model.exception.EnumNotFoundException.enumNotFoundException;
import static org.apache.commons.lang3.StringUtils.lowerCase;

/**
 *
 */
@Getter
public enum EMimeType {
    JPEG("image/jpeg"),
    PNG("image/png");

    private final String magentoRepresentation;

    EMimeType(final String magentoRepresentation) {
        this.magentoRepresentation = magentoRepresentation;
    }

    private static final Map<String, EMimeType> LOOKUP;

    static {
        final ImmutableMap.Builder<String, EMimeType> builder = ImmutableMap.builder();

        for (final EMimeType value : EMimeType.values()) {
            final String key = key(value);
            builder.put(key, value);
        }

        LOOKUP = builder.build();
    }

    @Nonnull
    public static Collection<String> allowedKeys() {
        return LOOKUP.keySet();
    }

    @NonNull
    public static EMimeType lookup(@NonNull final Integer candidate, @NonNull final EMimeType defaultValue) {
        return lookup(candidate).orElse(defaultValue);
    }

    @NonNull
    public static Optional<EMimeType> lookup(@NonNull final Integer candidate) {
        return Optional.ofNullable(
            LOOKUP.get(candidate)
        );
    }


    @Nonnull
    private static String key(@NonNull final EMimeType item) {
        return lowerCase(item.magentoRepresentation);
    }


    @Nonnull
    public static EMimeType lookupFailing(final String key) {
        final EMimeType item = LOOKUP.get(key);

        if (null == item) {
            throw enumNotFoundException(key, EMimeType.class, EMimeType::allowedKeys);
        }
        else {
            return item;
        }
    }

}
