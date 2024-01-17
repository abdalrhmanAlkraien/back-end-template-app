package com.marinabits.energietechs.repository.entity.convertor;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * List to string converter.
 *
 * @author Omar Ismail
 * @since 08/11/2023
 */
@Convert
public class ListToStringConverter implements AttributeConverter<List<Integer>, String> {

    private static final String SPLIT_CHAR = ";";

    /**
     * Convert List of integers To String.
     *
     * @param integers integers
     * @return String
     */
    @Override
    public String convertToDatabaseColumn(final List<Integer> integers) {

        return !CollectionUtils.isEmpty(integers) ? integers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(SPLIT_CHAR)) : "";
    }

    /**
     * Convert To Entity Attribute.
     *
     * @param string string
     * @return Set Of String
     */
    @Override
    public List<Integer> convertToEntityAttribute(final String string) {

        return StringUtils.hasText(string)
                ? Arrays.stream(string.split(SPLIT_CHAR))
                .map(Integer::parseInt)
                .collect(Collectors.toList())
                : Collections.emptyList();
    }
}
