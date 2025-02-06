package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.laa.ccms.data.entity.NotificationNote;
import uk.gov.laa.ccms.data.model.Note;

@Mapper(componentModel = "spring")
public interface NoteMapper {

  @Mapping(target = "user.username", source = "noteBy")
  @Mapping(target = "notesId", source = "noteId")
  @Mapping(target = "message", source = "noteText")
  @Mapping(target = "date", source = "noteDate")
  Note mapToNote(NotificationNote notificationNote);
}
