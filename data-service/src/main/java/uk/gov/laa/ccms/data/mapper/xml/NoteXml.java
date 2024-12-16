package uk.gov.laa.ccms.data.mapper.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;

/**
 * Represents a single note in XML format.
 *
 * <p>This record is used for mapping between XML data and Java objects when handling notes, using
 * the Jackson XML data format library. The properties of this record correspond to fields
 * in the XML representation.</p>
 *
 * @param noteId A unique identifier for the note.
 * @param noteBy The author of the note.
 * @param date The date the note was created.
 * @param message The contents of the note.
 * @see NotesXml
 * @author Jamie Briggs
 */
public record NoteXml(@JacksonXmlProperty(localName = "note_id") String noteId,
    @JacksonXmlProperty(localName = "note_by") String noteBy,
    @JacksonXmlProperty(localName = "date")
    LocalDate date,
    @JacksonXmlProperty(localName = "Message") String message) {

}
