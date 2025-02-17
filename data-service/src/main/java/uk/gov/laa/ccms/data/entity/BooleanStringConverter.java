package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Optional;

/**
 * Attribute converter class which handles the conversion of to and from a boolean value, and "true"
 *     ot "false".
 *
 * @author Jamie Briggs
 */
@Converter
public class BooleanStringConverter implements AttributeConverter<Boolean, String> {

  /**
   * Converts a Boolean value to its database column representation as a String.
   * For non-null values, the method returns "true" or "false" based on the Boolean value.
   * Null values are handled gracefully and returned as null.
   *
   * @param value the Boolean value to be converted; can be null
   * @return the String representation of the Boolean value ("true", "false"),
   *     or null if the input is null
   */
  @Override
  public String convertToDatabaseColumn(Boolean value) {
    return Optional.ofNullable(value)
        .map(x -> Boolean.TRUE.equals(x) ? "true" : "false")
        .orElse(null);
  }

  /**
   * Converts a database column value (String) to its corresponding entity attribute (Boolean).
   *
   * @param value the String value retrieved from the database column; typically "true" or "false".
   *              If null or not a valid Boolean string, it will evaluate to false by default.
   * @return the Boolean equivalent of the input String.
   */
  @Override
  public Boolean convertToEntityAttribute(String value) {
    return Boolean.parseBoolean(value);
  }
}
