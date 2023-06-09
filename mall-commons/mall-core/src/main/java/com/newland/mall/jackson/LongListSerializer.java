package com.newland.mall.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/**
 * @author leell
 * @date 2023/6/9 08:08:53
 */
public class LongListSerializer extends JsonSerializer<List<Long>> {
    @Override
    public void serialize(List<Long> list, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (Long value : list) {
            jsonGenerator.writeNumber(value);
        }
        jsonGenerator.writeEndArray();
    }
}
