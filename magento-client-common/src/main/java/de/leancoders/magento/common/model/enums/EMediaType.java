package de.leancoders.magento.common.model.enums;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import de.leancoders.magento.common.model.exception.EnumNotFoundException;
import lombok.Getter;
import lombok.NonNull;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static de.leancoders.magento.common.model.exception.EnumNotFoundException.enumNotFoundException;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.lowerCase;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

@Getter
public enum EMediaType {

    IMAGE("image"),
    SMALL_IMAGE("small_image"),
    SWATCH_IMAGE("swatch_image"),
    THUMBNAIL("thumbnail");

    private final String magentoRepresentation;

    EMediaType(@NonNull final String magentoRepresentation) {
        this.magentoRepresentation = magentoRepresentation;
    }

    private static final Map<String, EMediaType> LOOKUP;

    static {
        final ImmutableMap.Builder<String, EMediaType> builder = ImmutableMap.builder();

        for (final EMediaType value : EMediaType.values()) {
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
    public static EMediaType lookup(@NonNull final String candidate, @NonNull final EMediaType defaultValue) {
        return lookup(candidate).orElse(defaultValue);
    }

    @NonNull
    public static Optional<EMediaType> lookup(@NonNull final String candidate) {
        final String key = key(candidate);
        return Optional.ofNullable(
            LOOKUP.get(key)
        );
    }


    @Nonnull
    private static String key(@NonNull final EMediaType item) {
        return key(item.magentoRepresentation);
    }

    @Nonnull
    private static String key(@NonNull final String candidate) {
        return lowerCase(trimToEmpty(candidate));
    }

    @Nonnull
    public static EMediaType lookupFailing(final String candidate) {
        final String key = key(candidate);
        final EMediaType item = LOOKUP.get(key);

        if (null == item) {
            throw enumNotFoundException(candidate, EMediaType.class, EMediaType::allowedKeys);
        } else {
            return item;
        }
    }

}
