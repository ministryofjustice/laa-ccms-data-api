package uk.gov.laa.ccms.data.mapper;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.laa.ccms.data.entity.NotificationNote;
import uk.gov.laa.ccms.data.model.Note;

@DisplayName("Note mapper implementation test")
class NoteMapperImplTest {

  NoteMapper mapper = new NoteMapperImpl();

  @Test
  @DisplayName("Should map to note")
  void shouldMapToNote(){
    // Given
    NotificationNote note = NotificationNote.builder()
        .noteId(1L)
        .noteBy("User Name")
        .noteDate(LocalDate.of(2025, 1, 1))
        .notificationId(2L)
        .noteText("Note text")
        .build();
    // When
    Note result = mapper.mapToNote(note);
    // Then
    assertEquals("1", result.getNotesId());
    assertEquals("User Name", result.getUser().getUsername());
    assertEquals(LocalDate.of(2025, 1, 1), result.getDate());
    assertEquals("Note text", result.getMessage());
  }
}
