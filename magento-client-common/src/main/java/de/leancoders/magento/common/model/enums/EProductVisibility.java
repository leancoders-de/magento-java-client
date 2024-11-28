package de.leancoders.magento.common.model.enums;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.NonNull;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static de.leancoders.magento.common.model.exception.EnumNotFoundException.enumNotFoundException;

/*
 */
@Getter
public enum EProductVisibility {

    VISIBILITY_NOT_VISIBLE(1),
    VISIBILITY_IN_CATALOG(2),
    VISIBILITY_IN_SEARCH(3),
    VISIBILITY_BOTH(4);

    private final int magentoRepresentation;

    EProductVisibility(final int magentoRepresentation) {
        this.magentoRepresentation = magentoRepresentation;
    }

    private static final Map<Integer, EProductVisibility> LOOKUP;

    static {
        final ImmutableMap.Builder<Integer, EProductVisibility> builder = ImmutableMap.builder();

        for (final EProductVisibility value : EProductVisibility.values()) {
            final Integer key = key(value);
            builder.put(key, value);
        }

        LOOKUP = builder.build();
    }

    @Nonnull
    public static Collection<Integer> allowedKeys() {
        return LOOKUP.keySet();
    }

    @NonNull
    public static EProductVisibility lookup(@NonNull final Integer candidate, @NonNull final EProductVisibility defaultValue) {
        return lookup(candidate).orElse(defaultValue);
    }

    @NonNull
    public static Optional<EProductVisibility> lookup(@NonNull final Integer candidate) {
        return Optional.ofNullable(
            LOOKUP.get(candidate)
        );
    }


    @Nonnull
    private static Integer key(@NonNull final EProductVisibility item) {
        return item.magentoRepresentation;
    }


    @Nonnull
    public static EProductVisibility lookupFailing(final Integer key) {
        final EProductVisibility item = LOOKUP.get(key);

        if (null == item) {
            throw enumNotFoundException(key + "", EProductVisibility.class, EProductVisibility::allowedKeys);
        }
        else {
            return item;
        }
    }

}
