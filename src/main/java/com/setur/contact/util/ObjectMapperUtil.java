package com.setur.contact.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;

import java.io.IOException;
import java.lang.reflect.Type;

@Slf4j
@UtilityClass
public class ObjectMapperUtil {

    public static final ObjectMapper OBJECT_MAPPER =
            new ObjectMapper().setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    public static <T> T extractTreeValue(T value, Type type) {
        try {
            JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructType(type);
            return OBJECT_MAPPER.treeToValue((TreeNode) value, javaType);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }
}
