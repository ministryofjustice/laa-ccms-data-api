package uk.gov.laa.ccms.data.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NotificationInfoTypeConverterTest {

  NotificationTypeConverter converter = new NotificationTypeConverter();

  @Test
  @DisplayName("Should convert NotificationInfo DB Value to Entity value")
  public void shouldConvertNotificationDbValuetoEntityValue() {
    assertEquals(NotificationType.NOTIFICATIONS, converter.convertToEntityAttribute("Notification"));
  }

  @Test
  @DisplayName("Should convert Action DB Value to Entity value")
  public void shouldConvertActionDbValuetoEntityValue() {
    assertEquals(NotificationType.ACTION, converter.convertToEntityAttribute("Action"));
  }

  @Test
  @DisplayName("Should convert Overdue DB Value to Entity value")
  public void shouldConvertOverdueDbValuetoEntityValue() {
    assertEquals(NotificationType.OVERDUE, converter.convertToEntityAttribute("Overdue"));
  }

  @Test
  @DisplayName("Should convert NotificationInfo Entity value to DB value")
  public void shouldConvertNotificationEntityValuetoDBValue() {
    assertEquals("NotificationInfo", converter.convertToDatabaseColumn(NotificationType.NOTIFICATIONS));
  }

  @Test
  @DisplayName("Should convert Action Entity value to DB value")
  public void shouldConvertActionEntityValuetoDBValue() {
    assertEquals("Action", converter.convertToDatabaseColumn(NotificationType.ACTION));
  }

  @Test
  @DisplayName("Should convert Overdue Entity value to DB value")
  public void shouldConvertOverdueEntityValuetoDBValue() {
    assertEquals("Overdue", converter.convertToDatabaseColumn(NotificationType.OVERDUE));
  }

}
