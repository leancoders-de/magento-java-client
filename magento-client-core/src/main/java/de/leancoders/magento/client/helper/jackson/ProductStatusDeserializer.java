package de.leancoders.magento.client.helper.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.leancoders.magento.common.model.enums.EMediaType;
import de.leancoders.magento.common.model.enums.EProductStatus;

import java.io.IOException;

public class ProductStatusDeserializer extends StdDeserializer<EProductStatus> {

    public ProductStatusDeserializer() {
        this(null);
    }

    public ProductStatusDeserializer(Class<EMediaType> t) {
        super(t);
    }

    @Override
    public EProductStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final Integer value = jsonParser.getIntValue();
        return EProductStatus.lookupFailing(value);
    }
}