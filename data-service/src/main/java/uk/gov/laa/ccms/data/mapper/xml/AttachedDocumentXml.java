package uk.gov.laa.ccms.data.mapper.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Represents a single attached document in XML format.
 *
 * <p>This record is primarily utilized for mapping XML data to Java objects
 * using the Jackson data format library for XML deserialization/serialization.</p>
 *
 * @param documentId A unique identifier for the document.
 * @param attachmentTitle The title of the attached document.
 * @param text Text describing the document.
 * @see AttachedDocumentsXml
 * @author Jamie Briggs
 */
public record AttachedDocumentXml(
    @JacksonXmlProperty(localName = "document_id") String documentId,
    @JacksonXmlProperty(localName = "ATTACHMENT_TITLE") String attachmentTitle,
    @JacksonXmlProperty(localName = "Text") String text) {

}
