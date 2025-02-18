package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import uk.gov.laa.ccms.data.entity.NotificationAction;
import uk.gov.laa.ccms.data.entity.NotificationInfo;
import uk.gov.laa.ccms.data.model.Notification;

/**
 * Interface responsible for mapping objects to {@link Notification} objects. This interface
 *     utilizes MapStruct for mapping properties. Also utilizes {@link NoteMapper} for mapping
 *     notes, and {@link DocumentMapper} for mapping documents and attachments.
 *
 * @see Notification
 * @see NotificationInfo
 * @see NoteMapper
 * @see DocumentMapper
 *
 * @author Jamie Briggs
 */
@Mapper(componentModel = "spring",
    uses = {NoteMapper.class, DocumentMapper.class},
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface NotificationMapper {

  /**
   * Maps a {@link NotificationInfo} to a {@link Notification} object.
   *
   * @param notification the source {@link NotificationInfo} object.
   * @return a {@link Notification} object containing the mapped notification.
   */
  @Mapping(target = "user.loginId", source = "userLoginId")
  @Mapping(target = "user.username", source = "assignedTo")
  @Mapping(target = "assignDate", source = "dateAssigned")
  @Mapping(target = "providerCaseReferenceNumber", source = "providerCaseReference")
  @Mapping(target = "caseReferenceNumber", source = "lscCaseRefReference")
  @Mapping(target = "notificationType", source = "actionNotificationInd")
  @Mapping(target = "notificationOpenIndicator", source = "isOpen")
  @Mapping(target = "notes", source = "notes")
  @Mapping(target = "uploadedDocuments", source = "documents")
  @Mapping(target = "attachedDocuments", source = "attachments")
  @Mapping(target = "availableResponses", source = "actions", qualifiedByName = "action")
  Notification mapToNotification(NotificationInfo notification);

  /**
   * Converts a {@link NotificationAction} object to its corresponding action
   *     description as a string.
   *
   * @param action the {@link NotificationAction} object to be converted.
   * @return a string representing the action description from
   *     the {@link NotificationAction} object.
   */
  @Named("action")
  static String actionToString(NotificationAction action) {
    return action.getNextAction();
  }

}
