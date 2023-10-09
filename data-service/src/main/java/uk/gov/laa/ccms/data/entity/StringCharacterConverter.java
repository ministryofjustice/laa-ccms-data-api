package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * AttributeConverter class to handle the conversion to/from a Boolean value and 'Y' or 'N'.
 */
@Converter
public class StringCharacterConverter implements AttributeConverter<String, Character> {

  @Override
  public Character convertToDatabaseColumn(String attribute) {
    if (attribute == null) {
      return null;
    }

    switch (attribute.toLowerCase()) {
      case "true":
        return 'Y';
      case "false":
        return 'N';
      default:
        throw new IllegalArgumentException("Invalid boolean string value");
    }
  }

  @Override
  public String convertToEntityAttribute(Character dbData) {
    if (dbData == null) {
      return null;
    }

    switch (dbData) {
      case 'Y':
        return "true";
      case 'N':
        return "false";
      default:
        throw new IllegalArgumentException("Invalid boolean character value");
    }
  }

}
