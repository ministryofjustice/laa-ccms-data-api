package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.laa.ccms.data.entity.NotificationAttachment;
import uk.gov.laa.ccms.data.entity.NotificationDocument;
import uk.gov.laa.ccms.data.model.Document;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

  @Mapping(target = "text", source = "documentDescription")
  @Mapping(target = "channel", source = "documentChannel")
  @Mapping(target = "status", source = "documentStatus")
  @Mapping(target = "documentLink", constant = "")
  @Mapping(target = "fileData",  constant = "")
  @Mapping(target = "statusDescription",  constant = "")
  @Mapping(target = "fileExtension",  constant = "")
  @Mapping(target = "title", constant = "")
  Document mapToNotification(NotificationDocument notificationDocument);

  @Mapping(target = "channel",  constant = "")
  @Mapping(target = "documentLink",  constant = "")
  @Mapping(target = "fileData",  constant = "")
  @Mapping(target = "statusDescription",  constant = "")
  @Mapping(target = "fileExtension",  constant = "")
  @Mapping(target = "title", constant = "")
  @Mapping(target = "status", constant = "")
  @Mapping(target = "documentType", constant = "")
  @Mapping(target = "text", source = "attachmentDescription")
  @Mapping(target = "documentId", source = "attachmentId")
  Document mapToNotification(NotificationAttachment notificationAttachment);
}
