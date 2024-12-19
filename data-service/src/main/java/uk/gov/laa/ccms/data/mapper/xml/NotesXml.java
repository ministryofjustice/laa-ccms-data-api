package uk.gov.laa.ccms.data.mapper.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

/**
 * Represents a collection of notes in XML format.
 *
 * <p>The XML structure for this record is defined such that the "notes" element contains
 * a list of attached notes without an additional wrapper element.</p>
 *
 * <p>This class is primarily used for mapping XML data to Java objects
 * using the Jackson data format library.</p>
 *
 * <p>A note typically includes properties such as:</p>
 * <ul>
 *   <li>Note ID: A unique identifier for the note.</li>
 *   <li>Note By: The author of the note.</li>
 *   <li>Date: The date the note was created.</li>
 *   <li>Message: The contents of the note</li>
 * </ul>
 *
 * @see NoteXml
 * @param notes A list of notes.
 * @author Jamie Briggs
 */
public record NotesXml(
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Note")
    List<NoteXml> notes) {

}
