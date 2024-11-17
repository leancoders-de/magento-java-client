package de.leancoders.magento.client.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.leancoders.magento.client.helper.jackson.ObjectMapperFactory;

public abstract class MagentoMapper<T> {

    protected static final ObjectMapper OBJECT_MAPPER = ObjectMapperFactory.createDefaultObjectMapper();


}
