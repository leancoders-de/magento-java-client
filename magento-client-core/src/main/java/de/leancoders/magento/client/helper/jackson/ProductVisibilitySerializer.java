package de.leancoders.magento.client.helper.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import de.leancoders.magento.common.model.enums.EProductStatus;
import de.leancoders.magento.common.model.enums.EProductVisibility;

import java.io.IOException;

public class ProductVisibilitySerializer extends StdSerializer<EProductVisibility> {

    public ProductVisibilitySerializer() {
        this(null);
    }

    public ProductVisibilitySerializer(Class<EProductVisibility> t) {
        super(t);
    }

    @Override
    public void serialize(EProductVisibility value, JsonGenerator gen, SerializerProvider arg2) throws IOException {
        gen.writeNumber(value.getMagentoRepresentation());
    }

}