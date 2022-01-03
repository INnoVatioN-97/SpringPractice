package com.practice.spring.youngil.songjava.lecture.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.practice.spring.youngil.songjava.lecture.mvc.domain.BaseCodeLabelEnum;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseCodeLabelEnumJsonSerializer extends JsonSerializer<BaseCodeLabelEnum> {
    @Override
    public void serialize(BaseCodeLabelEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("code", value.code());
        map.put("label", value.label());
        gen.writeObject(map);
    }
}
