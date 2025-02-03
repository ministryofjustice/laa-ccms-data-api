package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Optional;

@Converter
public class BooleanStringConverter implements AttributeConverter<Boolean, String> {

  @Override
  public String convertToDatabaseColumn(Boolean aBoolean) {
    return Optional.ofNullable(aBoolean)
        .map(x -> x ? "true" : "false")
        .orElse(null);
  }

  @Override
  public Boolean convertToEntityAttribute(String value) {
    return Optional.ofNullable(value)
        .map(d -> d.equalsIgnoreCase("true"))
        .orElse(null);
  }
}
