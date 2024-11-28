package de.leancoders.magento.client.helper.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.leancoders.magento.common.model.enums.EBackendType;
import de.leancoders.magento.common.model.enums.EProductType;

import java.io.IOException;

public class BackendTypeDeserializer extends StdDeserializer<EBackendType> {

    public BackendTypeDeserializer() {
        this(null);
    }

    public BackendTypeDeserializer(Class<EProductType> t) {
        super(t);
    }

    @Override
    public EBackendType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final String text = jsonParser.getText();

        return EBackendType.lookupFailing(text);
    }
}