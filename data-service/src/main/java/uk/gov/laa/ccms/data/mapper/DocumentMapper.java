package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.laa.ccms.data.entity.NotificationDocument;
import uk.gov.laa.ccms.data.model.Document;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

  @Mapping(target = "text", source = "documentDescription")
  @Mapping(target = "channel", source = "documentChannel")
  @Mapping(target = "status", source = "documentStatus")
  Document mapToNotification(NotificationDocument notificationDocument);
}
