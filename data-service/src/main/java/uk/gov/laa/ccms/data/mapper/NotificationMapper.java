package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import uk.gov.laa.ccms.data.entity.NotificationAction;
import uk.gov.laa.ccms.data.entity.NotificationInfo;
import uk.gov.laa.ccms.data.model.Notification;

@Mapper(componentModel = "spring",
    uses = {NoteMapper.class, DocumentMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface NotificationMapper {

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

  @Named("action")
  public static String actionToString(NotificationAction action) {
    return action.getNextAction();
  }

}
