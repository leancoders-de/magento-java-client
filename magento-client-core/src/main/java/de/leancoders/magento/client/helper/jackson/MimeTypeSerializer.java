package de.leancoders.magento.client.helper.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import de.leancoders.magento.common.model.enums.EMimeType;

import java.io.IOException;

public class MimeTypeSerializer extends StdSerializer<EMimeType> {

    public MimeTypeSerializer() {
        this(null);
    }

    public MimeTypeSerializer(Class<EMimeType> t) {
        super(t);
    }

    @Override
    public void serialize(EMimeType value, JsonGenerator gen, SerializerProvider arg2) throws IOException {
        gen.writeString(value.getMagentoRepresentation());
    }

}