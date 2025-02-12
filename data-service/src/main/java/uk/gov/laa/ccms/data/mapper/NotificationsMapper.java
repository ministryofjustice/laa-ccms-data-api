package uk.gov.laa.ccms.data.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uk.gov.laa.ccms.data.entity.NotificationInfo;
import uk.gov.laa.ccms.data.model.Notifications;

/**
 * Interface responsible for mapping notification-related data
 * entities and models to one another. This interface utilizes MapStruct
 * for transformation and supports advanced mappings with custom conversion logic.
 *
 * @see Notifications
 * @see NotificationInfo
 * @see Page
 * @author Jamie Briggs
 */
@Mapper(componentModel = "spring")
public interface NotificationsMapper {

  /**
   * Maps a {@link Page} of {@link NotificationInfo} objects to a {@link Notifications} object.
   *
   * @param notificationPage a {@link Page} containing {@link NotificationInfo} entities to be
*        mapped
   * @return a {@link Notifications} object containing the mapped notifications along
   *     with pagination details
   */
  Notifications mapToNotificationsList(Page<NotificationInfo> notificationPage);

  /**
   * Maps a NotificationInfo object to a {@link uk.gov.laa.ccms.data.model.NotificationInfo} object.
   *
   * @param notificationInfo the source NotificationInfo object to be mapped
   * @return the mapped {@link uk.gov.laa.ccms.data.model.NotificationInfo} object
   */
  @Mapping(target = "user.loginId", source = "userLoginId")
  @Mapping(target = "user.username", source = "assignedTo")
  @Mapping(target = "assignDate", source = "dateAssigned")
  @Mapping(target = "providerCaseReferenceNumber", source = "providerCaseReference")
  @Mapping(target = "caseReferenceNumber", source = "lscCaseRefReference")
  @Mapping(target = "notificationType", source = "actionNotificationInd")
  @Mapping(target = "notificationOpenIndicator", source = "isOpen")
  uk.gov.laa.ccms.data.model.NotificationInfo mapToNotification(NotificationInfo notificationInfo);


}
