package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class BooleanConverter implements AttributeConverter<Boolean, Character> {
 
    @Override
    public Character convertToDatabaseColumn(Boolean attribute) {
        return Optional.ofNullable(attribute)
            .map(a -> a ? 'Y' : 'N')
            .orElse(null);
    }
 
    @Override
    public Boolean convertToEntityAttribute(Character dbData) {
        return Optional.ofNullable(dbData)
            .map(d -> d.equals('Y'))
            .orElse(null);
    }
     
}