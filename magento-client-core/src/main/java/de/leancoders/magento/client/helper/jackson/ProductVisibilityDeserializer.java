package de.leancoders.magento.client.helper.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.leancoders.magento.common.model.enums.EMediaType;
import de.leancoders.magento.common.model.enums.EProductVisibility;

import java.io.IOException;

public class ProductVisibilityDeserializer extends StdDeserializer<EProductVisibility> {

    public ProductVisibilityDeserializer() {
        this(null);
    }

    public ProductVisibilityDeserializer(Class<EMediaType> t) {
        super(t);
    }

    @Override
    public EProductVisibility deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final Integer value = jsonParser.getIntValue();
        return EProductVisibility.lookupFailing(value);
    }
}