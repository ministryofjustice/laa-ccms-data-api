package uk.gov.laa.ccms.data.mapper.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record AttachedDocumentXml(
    @JacksonXmlProperty(localName = "document_id") String documentId,
    @JacksonXmlProperty(localName = "ATTACHMENT_TITLE") String attachmentTitle,
    @JacksonXmlProperty(localName = "Text") String text
    ) {

}
