package uk.gov.laa.ccms.data.mapper.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

/**
 * Represents a collection of attached documents in XML format.
 *     Each document is represented as an instance of the AttachedDocumentXml record.
 *
 * <p>The XML structure for this record is defined such that the "Documents" element contains
 * a list of attached documents without an additional wrapper element.</p>
 *
 * <p>This class is primarily used for mapping XML data to Java objects
 * using the Jackson data format library.</p>
 *
 * <p>An attached document typically includes properties such as:</p>
 * <ul>
 *   <li>Document ID: A unique identifier for the document.</li>
 *   <li>Attachment title: The title of the attached document.</li>
 *   <li>Text: Text describing the document</li>
 * </ul>
 *
 * @param attachedDocuments A list of metadata for documents which were attached
 * @see AttachedDocumentXml
 * @author Jamie Briggs
 */
public record AttachedDocumentsXml(@JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Documents")
    List<AttachedDocumentXml> attachedDocuments) {

}
