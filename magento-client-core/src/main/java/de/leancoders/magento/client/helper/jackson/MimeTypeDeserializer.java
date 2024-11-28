package de.leancoders.magento.client.helper.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.leancoders.magento.common.model.enums.EMimeType;

import java.io.IOException;

public class MimeTypeDeserializer extends StdDeserializer<EMimeType> {

    public MimeTypeDeserializer() {
        this(null);
    }

    public MimeTypeDeserializer(Class<EMimeType> t) {
        super(t);
    }

    @Override
    public EMimeType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final String text = jsonParser.getText();

        return EMimeType.lookupFailing(text);
    }
}