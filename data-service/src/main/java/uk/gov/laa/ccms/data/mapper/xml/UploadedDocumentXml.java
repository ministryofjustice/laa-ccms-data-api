package uk.gov.laa.ccms.data.mapper.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Represents a single uploaded document in XML format.
 *
 * <p>This record is used for mapping XML data to Java objects when handling uploaded documents.
 * Each field in this record corresponds to a specific element or attribute in the XML data.</p>
 *
 * <p>This record is typically part of a collection represented
 * by {@link UploadedDocumentsXml}, which aggregates multiple uploaded documents.</p>
 *
 * @param documentId A unique identifier for the document.
 * @param documentType The type or category of the document.
 * @param documentChannel The source in which the document was uplaoded from.
 * @param text Text describing the document.
 * @see UploadedDocumentsXml
 */
public record UploadedDocumentXml(
    @JacksonXmlProperty(localName = "document_id") String documentId,
    @JacksonXmlProperty(localName = "document_type") String documentType,
    @JacksonXmlProperty(localName = "document_channel") String documentChannel,
    @JacksonXmlProperty(localName = "Text") String text) {

}
