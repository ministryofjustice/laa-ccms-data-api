package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.laa.ccms.data.entity.NotificationAttachment;
import uk.gov.laa.ccms.data.entity.NotificationDocument;
import uk.gov.laa.ccms.data.model.Document;

/**
 * Interface responsible for mapping objects to {@link Document} objects. This interface
 *     utilizes Mapstruct for mapping properties.
 *
 * @see Document
 * @see NotificationDocument
 * @see NotificationAttachment
 *
 * @author Jamie Briggs
 */
@Mapper(componentModel = "spring")
public interface DocumentMapper {

  /**
   * Maps a {@link NotificationDocument} to a {@link Document} object. Some fields have been
   *     set to an empty string due to not existing in {@link NotificationDocument} such as:
   * <ul>
   *   <li>documentLink</li>
   *   <li>fileData</li>
   *   <li>statusDescription</li>
   *   <li>fileExtension</li>
   *   <li>title</li>
   * </ul>
   *
   * @param notificationDocument the source {@link NotificationDocument} object to be mapped.
   * @return a {@link Document} object containing the mapped document.
   */
  @Mapping(target = "text", source = "documentDescription")
  @Mapping(target = "channel", source = "documentChannel")
  @Mapping(target = "status", source = "documentStatus")
  @Mapping(target = "documentLink", constant = "")
  @Mapping(target = "fileData",  constant = "")
  @Mapping(target = "statusDescription",  constant = "")
  @Mapping(target = "fileExtension",  constant = "")
  @Mapping(target = "title", constant = "")
  Document mapToNotification(NotificationDocument notificationDocument);

  /**
   * Maps a {@link NotificationAttachment} to a {@link Document}. Some fields have been set to an
   *     empty string due to not existing in {@link NotificationDocument} such as:
   * <ul>
   *   <li>documentLink</li>
   *   <li>fileData</li>
   *   <li>statusDescription</li>
   *   <li>fileExtension</li>
   *   <li>title</li>
   *   <li>status</li>
   *   <li>documentType</li>
   * </ul>
   *
   * @param notificationAttachment the source {@link NotificationAttachment} object to be mapped.
   * @return a {@link Document} object containing the mapped document.
   */
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
