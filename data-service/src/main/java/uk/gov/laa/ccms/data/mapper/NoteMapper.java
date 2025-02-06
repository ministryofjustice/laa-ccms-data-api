package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.laa.ccms.data.entity.NotificationNote;
import uk.gov.laa.ccms.data.model.Note;

/**
 * Interface responsible for mapping objects to {@link Note} objects. This interface
 *     utilizes MapStruct for mapping properties.
 *
 * @see Note
 * @see NotificationNote
 *
 * @author Jamie Briggs
 */
@Mapper(componentModel = "spring")
public interface NoteMapper {

  /**
   * Maps a {@link NotificationNote} to a {@link Note} object.
   *
   * @param notificationNote the source {@link NotificationNote} object.
   * @return a {@link Note} object containing the mapped note.
   */
  @Mapping(target = "user.username", source = "noteBy")
  @Mapping(target = "notesId", source = "noteId")
  @Mapping(target = "message", source = "noteText")
  @Mapping(target = "date", source = "noteDate")
  Note mapToNote(NotificationNote notificationNote);
}
