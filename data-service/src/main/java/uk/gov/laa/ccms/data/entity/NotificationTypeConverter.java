package uk.gov.laa.ccms.data.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converter implementation for mapping the NotificationType enumeration to a String
 * representation in the database and vice versa.
 *
 * <p>This converter is automatically applied to all entity attributes of type NotificationType
 * across the JPA context due to the use of the @Converter annotation with autoApply set to true.
 *
 * <p>The convertToDatabaseColumn method translates a NotificationType enumeration value to its
 * corresponding String representation for storage in the database. If the provided
 * NotificationType is null, the database column is set to null.
 *
 * <p>The convertToEntityAttribute method takes a String from the database and converts it back
 * to its corresponding NotificationType value. If the database value is null, it returns null
 * as the entity attribute. If the database String does not match any known NotificationType,
 * an IllegalArgumentException is thrown.
 */
@Converter(autoApply = true)
public class NotificationTypeConverter implements AttributeConverter<NotificationType, String> {

  /**
   * Converts a NotificationType enumeration value to its corresponding String representation
   * for storage in the database.
   *
   * @param notificationType the NotificationType to be converted to a database column value;
   *     may be null, in which case the method returns null.
   * @return the String representation of the given NotificationType for database storage,
   *     or null if the NotificationType is null.
   */
  @Override
  public String convertToDatabaseColumn(NotificationType notificationType) {
    return notificationType != null ? notificationType.getDbValue() : null;
  }

  /**
   * Converts a database column value (String) to the corresponding
   * NotificationType enumeration value.
   *
   * @param dbData the database column value that represents a
   *     NotificationType; may be null.
   * @return the NotificationType corresponding to the given database value,
   *     or null if the input is null.
   * @throws IllegalArgumentException if dbData does not match any known NotificationType values.
   */
  @Override
  public NotificationType convertToEntityAttribute(String dbData) {
    if (dbData == null) {
      return null;
    }

    return switch (dbData) {
      case "Notification" -> NotificationType.NOTIFICATIONS;
      case "Action" -> NotificationType.ACTION;
      case "Overdue" -> NotificationType.OVERDUE;
      default -> throw new IllegalArgumentException("Unknown database value: " + dbData);
    };
  }
}
