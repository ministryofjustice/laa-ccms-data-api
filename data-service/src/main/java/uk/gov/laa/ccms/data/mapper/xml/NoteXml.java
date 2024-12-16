package uk.gov.laa.ccms.data.mapper.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;

public record NoteXml(@JacksonXmlProperty(localName = "note_id") String noteId,
                      @JacksonXmlProperty(localName = "note_by") String noteBy,
                      @JacksonXmlProperty(localName = "date")
                      LocalDate date,
                      @JacksonXmlProperty(localName = "Message") String message) {

}
