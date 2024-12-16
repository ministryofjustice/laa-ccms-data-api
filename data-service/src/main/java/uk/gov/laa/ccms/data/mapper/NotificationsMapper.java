package uk.gov.laa.ccms.data.mapper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.Collections;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;
import uk.gov.laa.ccms.data.entity.Notification;
import uk.gov.laa.ccms.data.mapper.xml.AttachedDocumentXml;
import uk.gov.laa.ccms.data.mapper.xml.AttachedDocumentsXml;
import uk.gov.laa.ccms.data.mapper.xml.AvailableResponsesXml;
import uk.gov.laa.ccms.data.mapper.xml.NoteXml;
import uk.gov.laa.ccms.data.mapper.xml.NotesXml;
import uk.gov.laa.ccms.data.mapper.xml.UploadedDocumentXml;
import uk.gov.laa.ccms.data.mapper.xml.UploadedDocumentsXml;
import uk.gov.laa.ccms.data.model.Document;
import uk.gov.laa.ccms.data.model.Note;
import uk.gov.laa.ccms.data.model.Notifications;
import uk.gov.laa.ccms.data.model.UserDetail;

/**
 * Interface responsible for mapping notification-related data
 * entities and models to one another. This interface utilizes MapStruct
 * for transformation and supports advanced mappings with custom conversion logic.
 *
 * @see Notifications
 * @see Notification
 * @see Page
 * @author Jamie Briggs
 */
@Mapper(componentModel = "spring")
public interface NotificationsMapper {

  /**
   * Maps a Page of Notification objects to a Notifications object.
   *
   * @param notificationPage a Page containing Notification entities to be mapped
   * @return a Notifications object containing the mapped notifications along with pagination details
   */
  Notifications mapToNotificationsList(Page<Notification> notificationPage);

  /**
   * Maps a Notification object to a {@link uk.gov.laa.ccms.data.model.Notification} object.
   *
   * @param notification the source Notification object to be mapped
   * @return the mapped uk.gov.laa.ccms.data.model.Notification object
   */
  @Mapping(target = "notes", source = "notes", qualifiedByName = "formatNotes")
  @Mapping(target = "uploadedDocuments", source = "uploadedDocuments", qualifiedByName = "formatUploadedDocuments")
  @Mapping(target = "attachedDocuments", source = "attachedDocuments", qualifiedByName = "formatAttachedDocuments")
  @Mapping(target = "availableResponses", source = "availableResponses", qualifiedByName = "formatResponses")
  @Mapping(target = "user.loginId", source = "userLoginId")
  @Mapping(target = "user.username", source = "assignedTo")
  @Mapping(target = "subject", source = "notificationSubject")
  @Mapping(target = "assignDate", source = "dateAssigned")
  @Mapping(target = "providerCaseReferenceNumber", source = "providerCaseReference")
  @Mapping(target = "notificationType", source = "actionNotificationInd")
  @Mapping(target = "notificationOpenIndicator", source = "isOpen")
  @Mapping(target = "evidenceAllowed", source = "evidenceAllowedInd")
  uk.gov.laa.ccms.data.model.Notification mapToNotification(Notification notification);

  /**
   * Maps a String containing XML representation of notes to a List of Note objects.
   *
   * @param notesString the XML String representing the notes
   * @return a List of Note objects derived from the XML String or
   * an empty list if the input is null or empty
   * @throws JsonProcessingException if an error occurs during the processing of the XML String
   */
  @Named("formatNotes")
  static List<Note> mapToNoteList(String notesString) throws JsonProcessingException {
    XmlMapper xmlMapper = getXmlMapper();
    if (notesString == null || notesString.isEmpty()) {
      return Collections.emptyList();
    }
    List<NoteXml> noteXmls = xmlMapper.readValue(notesString, NotesXml.class).noteXmls();

    // Return empty list if noteXmls is null
    if(noteXmls == null) return Collections.emptyList();

    return noteXmls.stream().map(
        x -> new Note().notesId(x.noteId()).user(new UserDetail().username(x.noteBy()))
            .date(x.date()).message(x.message())).toList();
  }

  /**
   * Maps a String containing the XML representation of uploaded documents to
   *    a List of {@link Document} objects.
   *
   * @param uploadedDocuments the XML String representing the uploaded documents
   * @return a List of Document objects derived from the XML String or an empty list if the input is null or empty
   * @throws JsonProcessingException if an error occurs during the processing of the XML String
   */
  @Named("formatUploadedDocuments")
  static List<Document> mapToFormattedDocuments(String uploadedDocuments) throws JsonProcessingException {
    XmlMapper xmlMapper = getXmlMapper();
    if (uploadedDocuments == null || uploadedDocuments.isEmpty()) {
      return Collections.emptyList();
    }
    List<UploadedDocumentXml> uploadedDocumentsXmls = xmlMapper.readValue(uploadedDocuments,
        UploadedDocumentsXml.class).uploadedDocument();

    // Return empty list if uploadedDocumentsXmls is null
    if(uploadedDocumentsXmls == null) return Collections.emptyList();

    return uploadedDocumentsXmls.stream().map(
        x -> new Document().documentId(x.documentId()).documentType(x.documentType())
            .channel(x.documentChannel()).text(x.text())).toList();
  }

  /**
   * Maps a String containing the XML representation of attached documents to a
   *    List of {@link Document} objects.
   *
   * @param attachedDocuments the XML String representing the attached documents
   * @return a List of Document objects derived from the XML String or an empty list if the input is null or empty
   * @throws JsonProcessingException if an error occurs during the processing of the XML String
   */
  @Named("formatAttachedDocuments")
  static List<Document> mapToAttachedDocuments(String attachedDocuments) throws JsonProcessingException {
    XmlMapper xmlMapper = getXmlMapper();
    if (attachedDocuments == null || attachedDocuments.isEmpty()) {
      return Collections.emptyList();
    }
    List<AttachedDocumentXml> attachedDocumentXmls = xmlMapper.readValue(attachedDocuments,
        AttachedDocumentsXml.class).attachedDocuments();

    // Return empty list if uploadedDocumentsXmls is null
    if(attachedDocumentXmls == null) return Collections.emptyList();

    return attachedDocumentXmls.stream().map(
        x -> new Document().documentId(x.documentId()).title(x.attachmentTitle()).text(x.text())).toList();
  }

  /**
   * Maps a String containing XML representation of available responses
   *  to a {@link List} of String objects.
   *
   * @param availableResponses the XML String representing the available responses
   * @return a List of String objects derived from the XML String or an empty list if the input is null or empty
   * @throws JsonProcessingException if an error occurs during the processing of the XML String
   */
  @Named("formatResponses")
  static List<String> mapToAvailableResponses(String availableResponses)
      throws JsonProcessingException {
    XmlMapper xmlMapper = getXmlMapper();
    if (availableResponses == null || availableResponses.isEmpty()) {
      return Collections.emptyList();
    }
    List<String> responses = xmlMapper.readValue(availableResponses,
        AvailableResponsesXml.class).responses();
    return responses == null ? Collections.emptyList() : responses;
  }

  /**
   * Creates and configures an instance of XmlMapper.
   *
   * @return an XmlMapper instance configured with the Java Time Module
   */
  private static XmlMapper getXmlMapper() {
    XmlMapper xmlMapper = new XmlMapper();
    xmlMapper.registerModule(new JavaTimeModule());
    return xmlMapper;
  }
}
