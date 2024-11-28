package de.leancoders.magento.client.helper.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import de.leancoders.magento.common.model.enums.EBackendType;
import de.leancoders.magento.common.model.enums.EMediaType;
import de.leancoders.magento.common.model.enums.EProductStatus;
import de.leancoders.magento.common.model.enums.EProductType;
import de.leancoders.magento.common.model.enums.EProductVisibility;

import javax.annotation.Nonnull;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 * @author Ralf
 */
public final class ObjectMapperFactory {

    private static final Set<Module> ENABLED_MODULES =
        ImmutableSet.of(
            javaTimeModule(),
            new GuavaModule(),
            enumModule()
        );

    @Nonnull
    private static JavaTimeModule javaTimeModule() {
        final JavaTimeModule module = new JavaTimeModule();
        module.addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeserializer());
        module.addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer());
        return module;
    }

    @Nonnull
    private static SimpleModule enumModule() {
        final SimpleModule module = new SimpleModule();
        // media type
        module.addDeserializer(EBackendType.class, new BackendTypeDeserializer());
        module.addSerializer(EBackendType.class, new BackendTypeSerializer());
        // media type
        module.addDeserializer(EMediaType.class, new MediaTypeDeserializer());
        module.addSerializer(EMediaType.class, new MediaTypeSerializer());
        // product status
        module.addDeserializer(EProductStatus.class, new ProductStatusDeserializer());
        module.addSerializer(EProductStatus.class, new ProductStatusSerializer());
        // product type
        module.addDeserializer(EProductType.class, new ProductTypeDeserializer());
        module.addSerializer(EProductType.class, new ProductTypeSerializer());
        // product visibility
        module.addDeserializer(EProductVisibility.class, new ProductVisibilityDeserializer());
        module.addSerializer(EProductVisibility.class, new ProductVisibilitySerializer());
        return module;
    }

    private ObjectMapperFactory() {
    }

    @Nonnull
    public static ObjectMapperBuilder builder() {
        return new ObjectMapperBuilder();
    }

    @Nonnull
    public static ObjectMapper createDefaultObjectMapper() {
        return builder().withDefaultConfiguration().build();
    }

    public static class ObjectMapperBuilder {

        private final Map<DeserializationFeature, Boolean> deserializationFeatures = Maps.newHashMap();
        private final Map<SerializationFeature, Boolean> serializationFeatures = Maps.newHashMap();
        private JsonInclude.Include serializationInclusion;

        private ObjectMapperBuilder() {
        }

        public ObjectMapperBuilder withDefaultConfiguration() {
            deserializationFeatures.put(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            deserializationFeatures.put(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
            deserializationFeatures.put(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
            deserializationFeatures.put(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS, true);
            deserializationFeatures.put(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);

            serializationFeatures.put(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
            serializationFeatures.put(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            serializationInclusion = JsonInclude.Include.NON_NULL;

            return this;
        }

        public ObjectMapper build() {
            final ObjectMapper mapper = new ObjectMapper();

            for (final Map.Entry<DeserializationFeature, Boolean> deserializationEntry : deserializationFeatures.entrySet()) {
                mapper.configure(deserializationEntry.getKey(), deserializationEntry.getValue());
            }

            for (final Map.Entry<SerializationFeature, Boolean> serializationEntry : serializationFeatures.entrySet()) {
                mapper.configure(serializationEntry.getKey(), serializationEntry.getValue());
            }

            if (serializationInclusion != null) {
                mapper.setSerializationInclusion(serializationInclusion);
            }

            mapper.registerModules(ENABLED_MODULES);
            mapper.deactivateDefaultTyping();

            return mapper;
        }

    }

}
