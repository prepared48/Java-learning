package com.geniu.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Maps;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {
    static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public JsonUtil() {
    }

    public static String toJson(Object object) {
        try {
            return object == null ? "" : mapper.writeValueAsString(object);
        } catch (IOException var2) {
            logger.warn("write to json string error:" + object, var2);
            return null;
        }
    }

    public static Map<String, Object> parseToMap(String json) {
        Map map = (Map)parseJson(json, Map.class, String.class, Object.class);
        if (map == null) {
            map = Maps.newHashMap();
        }

        return (Map)map;
    }

    public static <T> T parseJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        } else {
            try {
                return mapper.readValue(jsonString, clazz);
            } catch (IOException var3) {
                logger.warn("parse json string error:" + jsonString, var3);
                return null;
            }
        }
    }

    public static <T> T parseJson(File jsonFile, Class<T> clazz) {
        if (jsonFile != null && jsonFile.exists()) {
            try {
                return mapper.readValue(jsonFile, clazz);
            } catch (IOException var3) {
                logger.warn("parse json string error:", var3);
                return null;
            }
        } else {
            return null;
        }
    }

    public static JsonNode parseJsonNode(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        } else {
            try {
                return mapper.readTree(jsonString);
            } catch (Exception var2) {
                logger.warn("parse json string error:", var2);
                return null;
            }
        }
    }

    public static <T> T parseJson(String jsonString, Class<?> collectionClass, Class<?>... elementClasses) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        } else {
            try {
                return mapper.readValue(jsonString, getCollectionType(collectionClass, elementClasses));
            } catch (IOException var4) {
                logger.warn("parse json string error:" + jsonString, var4);
                return null;
            }
        }
    }

    public static <T> T parseJson(File jsonFile, Class<?> collectionClass, Class<?>... elementClasses) {
        if (jsonFile != null && jsonFile.exists()) {
            try {
                return mapper.readValue(jsonFile, getCollectionType(collectionClass, elementClasses));
            } catch (IOException var4) {
                logger.warn("parse json file error:", var4);
                return null;
            }
        } else {
            return null;
        }
    }

    public static <T> T parseJson(String jsonString, JavaType clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        } else {
            try {
                return mapper.readValue(jsonString, clazz);
            } catch (IOException var3) {
                logger.warn("parse json string error:" + jsonString, var3);
                return null;
            }
        }
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static <T> T parseJsonToList(String json, TypeReference<T> typeReference) {
        if (json != null && !"".equals(json.trim())) {
            try {
                return mapper.readValue(json, typeReference);
            } catch (Exception var3) {
                throw new RuntimeException("Deserialize from JSON failed.", var3);
            }
        } else {
            return null;
        }
    }

    public static <T> T convertValue(Object fromValue, TypeReference<?> toValueTypeRef) {
        return mapper.convertValue(fromValue, toValueTypeRef);
    }

    public static void main(String[] args) {
        Map<String, Object> stringObjectMap = parseToMap("{'a':'a','b':null}");
        System.out.println(stringObjectMap);
    }

    static {
        mapper.configure(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        mapper.configure(Feature.ALLOW_COMMENTS, true);
        mapper.configure(Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
        mapper.configure(Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.setSerializationInclusion(Include.NON_NULL);
    }
}
