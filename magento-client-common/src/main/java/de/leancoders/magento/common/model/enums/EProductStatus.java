package de.leancoders.magento.common.model.enums;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.NonNull;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static de.leancoders.magento.common.model.exception.EnumNotFoundException.enumNotFoundException;

@Getter
public enum EProductStatus {

    STATUS_DISABLED(2),
    STATUS_ENABLED(1);

    private final int magentoRepresentation;

    EProductStatus(final int magentoRepresentation) {
        this.magentoRepresentation = magentoRepresentation;
    }

    private static final Map<Integer, EProductStatus> LOOKUP;

    static {
        final ImmutableMap.Builder<Integer, EProductStatus> builder = ImmutableMap.builder();

        for (final EProductStatus value : EProductStatus.values()) {
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
    public static EProductStatus lookup(@NonNull final Integer candidate, @NonNull final EProductStatus defaultValue) {
        return lookup(candidate).orElse(defaultValue);
    }

    @NonNull
    public static Optional<EProductStatus> lookup(@NonNull final Integer candidate) {
        return Optional.ofNullable(
            LOOKUP.get(candidate)
        );
    }


    @Nonnull
    private static Integer key(@NonNull final EProductStatus item) {
        return item.magentoRepresentation;
    }


    @Nonnull
    public static EProductStatus lookupFailing(final Integer key) {
        final EProductStatus item = LOOKUP.get(key);

        if (null == item) {
            throw enumNotFoundException(key + "", EProductStatus.class, EProductStatus::allowedKeys);
        } else {
            return item;
        }
    }

}
