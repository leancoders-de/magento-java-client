package de.leancoders.magento.common.model.exception;

import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import static java.lang.String.format;


@EqualsAndHashCode(callSuper = true)
@Data
public class EnumNotFoundException extends RuntimeException {

    private final String rejectedValue;
    private final Iterable<Object> allowedValues;

    public EnumNotFoundException(@NonNull final String message,
                                 @NonNull final String rejectedValue,
                                 @NonNull final Iterable<Object> allowedValues) {
        super(message);
        this.rejectedValue = rejectedValue;
        this.allowedValues = allowedValues;
    }

    @Nonnull
    public static EnumNotFoundException enumNotFoundException(@NonNull final String candidate,
                                                              @NonNull final Class<?> clazz,
                                                              @NonNull final Supplier<Collection<?>> allowedKeysSupplier) {
        final List<Object> allowedKeys = ImmutableList.copyOf(allowedKeysSupplier.get());
        final String message = format(
            "Value: %s for Enum Type: %s cannot be found, expecting one of: %s",
            candidate,
            clazz,
            allowedKeys
        );

        return new EnumNotFoundException(
            message, candidate, allowedKeys
        );
    }

}
