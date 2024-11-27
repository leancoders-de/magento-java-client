package de.leancoders.magento.client.helper.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import de.leancoders.magento.common.model.enums.EMediaType;
import de.leancoders.magento.common.model.enums.EProductType;

import java.io.IOException;

public class ProductTypeSerializer extends StdSerializer<EProductType> {

    public ProductTypeSerializer() {
        this(null);
    }

    public ProductTypeSerializer(Class<EProductType> t) {
        super(t);
    }

    @Override
    public void serialize(EProductType value, JsonGenerator gen, SerializerProvider arg2) throws IOException {
        gen.writeString(value.getMagentoRepresentation());
    }

}