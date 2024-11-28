package de.leancoders.magento.client.helper.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import de.leancoders.magento.common.model.enums.EBackendType;
import de.leancoders.magento.common.model.enums.EProductType;

import java.io.IOException;

public class BackendTypeSerializer extends StdSerializer<EBackendType> {

    public BackendTypeSerializer() {
        this(null);
    }

    public BackendTypeSerializer(Class<EBackendType> t) {
        super(t);
    }

    @Override
    public void serialize(EBackendType value, JsonGenerator gen, SerializerProvider arg2) throws IOException {
        gen.writeString(value.getMagentoRepresentation());
    }

}