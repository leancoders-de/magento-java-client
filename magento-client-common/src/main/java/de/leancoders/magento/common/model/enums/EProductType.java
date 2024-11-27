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
public enum EProductType {

    SIMPLE("simple"),
    CONFIGURABLE("configurable"),
    VIRTUAL("virtual"),
    GROUPED("grouped"),
    BUNDLE("bundle"),
    DOWNLOADABLE("Downloadable");

    private final String magentoRepresentation;

    EProductType(final String magentoRepresentation) {
        this.magentoRepresentation = magentoRepresentation;
    }

    private static final Map<String, EProductType> LOOKUP;

    static {
        final ImmutableMap.Builder<String, EProductType> builder = ImmutableMap.builder();

        for (final EProductType value : EProductType.values()) {
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
    public static EProductType lookup(@NonNull final String candidate, @NonNull final EProductType defaultValue) {
        return lookup(candidate).orElse(defaultValue);
    }

    @NonNull
    public static Optional<EProductType> lookup(@NonNull final String candidate) {
        final String key = key(candidate);
        return Optional.ofNullable(
            LOOKUP.get(key)
        );
    }

    @Nonnull
    private static String key(@NonNull final EProductType item) {
        return key(item.magentoRepresentation);
    }

    @Nonnull
    private static String key(@NonNull final String candidate) {
        return lowerCase(trimToEmpty(candidate));
    }

    @Nonnull
    public static EProductType lookupFailing(final String candidate) {
        final String key = key(candidate);
        final EProductType item = LOOKUP.get(key);

        if (null == item) {
            throw enumNotFoundException(candidate, EProductType.class, EProductType::allowedKeys);
        } else {
            return item;
        }
    }

}

