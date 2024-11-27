package de.leancoders.magento.client.helper.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import de.leancoders.magento.common.model.enums.EMediaType;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public class MediaTypeSerializer extends StdSerializer<EMediaType> {

    public MediaTypeSerializer() {
        this(null);
    }

    public MediaTypeSerializer(Class<EMediaType> t) {
        super(t);
    }

    @Override
    public void serialize(EMediaType value, JsonGenerator gen, SerializerProvider arg2) throws IOException {
        gen.writeString(value.getMagentoRepresentation());
    }

}