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
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

@Getter
public enum EBackendType {

    VARCHAR("varchar");

    private final String magentoRepresentation;

    EBackendType(@NonNull final String magentoRepresentation) {
        this.magentoRepresentation = magentoRepresentation;
    }

    private static final Map<String, EBackendType> LOOKUP;

    static {
        final ImmutableMap.Builder<String, EBackendType> builder = ImmutableMap.builder();

        for (final EBackendType value : EBackendType.values()) {
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
    public static EBackendType lookup(@NonNull final String candidate, @NonNull final EBackendType defaultValue) {
        return lookup(candidate).orElse(defaultValue);
    }

    @NonNull
    public static Optional<EBackendType> lookup(@NonNull final String candidate) {
        final String key = key(candidate);
        return Optional.ofNullable(
            LOOKUP.get(key)
        );
    }


    @Nonnull
    private static String key(@NonNull final EBackendType item) {
        return key(item.magentoRepresentation);
    }

    @Nonnull
    private static String key(@NonNull final String candidate) {
        return lowerCase(trimToEmpty(candidate));
    }

    @Nonnull
    public static EBackendType lookupFailing(final String candidate) {
        final String key = key(candidate);
        final EBackendType item = LOOKUP.get(key);

        if (null == item) {
            throw enumNotFoundException(candidate, EBackendType.class, EBackendType::allowedKeys);
        } else {
            return item;
        }
    }

}
