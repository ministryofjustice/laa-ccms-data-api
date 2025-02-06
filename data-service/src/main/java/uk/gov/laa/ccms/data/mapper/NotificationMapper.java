package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.laa.ccms.data.entity.NotificationInfo;
import uk.gov.laa.ccms.data.model.Notification;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

  @Mapping(target = "user.loginId", source = "userLoginId")
  @Mapping(target = "user.username", source = "assignedTo")
  @Mapping(target = "assignDate", source = "dateAssigned")
  @Mapping(target = "providerCaseReferenceNumber", source = "providerCaseReference")
  @Mapping(target = "caseReferenceNumber", source = "lscCaseRefReference")
  @Mapping(target = "notificationType", source = "actionNotificationInd")
  @Mapping(target = "notificationOpenIndicator", source = "isOpen")
  Notification mapToNotification(NotificationInfo notification);

}
