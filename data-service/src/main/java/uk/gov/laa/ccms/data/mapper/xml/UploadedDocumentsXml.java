package uk.gov.laa.ccms.data.mapper.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

/**
 * Represents a collection of uploaded documents in XML format.
 *
 * <p>This record is used for deserializing and serializing XML data where the "Documents" element
 * contains a list of uploaded documents. Each uploaded document is represented as an instance
 * of the UploadedDocumentXml record.</p>
 *
 * <p>The Jackson XML data format library is utilized to map XML structures to Java objects
 * and vice versa. The "useWrapping" property is set to false, indicating
 * that the "Documents" elements are provided as a flat list without an extra wrapper.</p>
 *
 * <p>An uploaded document typically includes properties such as:</p>
 * <ul>
 *   <li>Document ID: A unique identifier for the document.</li>
 *   <li>Document Type: The type or category of the document.</li>
 *   <li>Document Channel: The source in which the document was uploaded from</li>
 *   <li>Text: Text describing the document.</li>
 * </ul>
 *
 * @param uploadedDocuments A list of metadata for documents which were uploaded.
 * @see UploadedDocumentXml
 * @author Jamie Briggs
 */
public record UploadedDocumentsXml(@JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Documents")
    List<UploadedDocumentXml> uploadedDocuments) {

}
