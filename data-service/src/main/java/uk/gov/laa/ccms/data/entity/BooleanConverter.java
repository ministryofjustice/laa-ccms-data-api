package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Optional;

/**
 * AttributeConverter class to handle the conversion to/from a Boolean value and 'Y' or 'N'
 */
@Converter(autoApply = true)
public class BooleanConverter implements AttributeConverter<Boolean, Character> {

    /**
     * Handle the conversion from a boolean value, to 'Y' or 'N'.
     *
     * @param attribute  the entity attribute value to be converted
     * @return 'Y' or 'N' based on attribute value, or null if the attribute is null
     */
    @Override
    public Character convertToDatabaseColumn(Boolean attribute) {
        return Optional.ofNullable(attribute)
            .map(a -> a ? 'Y' : 'N')
            .orElse(null);
    }

    /**
     * Handle the conversion from 'Y' or 'N' to a Boolean value.
     *
     * @param dbData  the data from the database column to be
     *                converted
     * @return equivalent Boolean value, or null if the dbData is null
     */
    @Override
    public Boolean convertToEntityAttribute(Character dbData) {
        return Optional.ofNullable(dbData)
            .map(d -> d.equals('Y'))
            .orElse(null);
    }
     
}