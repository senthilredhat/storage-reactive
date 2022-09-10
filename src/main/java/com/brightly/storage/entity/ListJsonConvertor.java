package com.brightly.storage.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import java.util.List;

@SuppressWarnings("unchecked")
public class ListJsonConvertor implements AttributeConverter<List<String>, String> {

    private static final Logger logger = LoggerFactory.getLogger(ListJsonConvertor.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> values) {
        String columnValue = null;
        try {
            columnValue = objectMapper.writeValueAsString(values);
        } catch (final JsonProcessingException e) {
            logger.error("JSON writing error", e);
        }
        return columnValue;
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        List<String> values = null;
        try {
            values = objectMapper.readValue(dbData, List.class);
        } catch (final Exception e) {
            logger.error("JSON reading error", e);
        }
        return values;
    }
}
