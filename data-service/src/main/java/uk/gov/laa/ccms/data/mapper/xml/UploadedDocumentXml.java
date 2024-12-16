package uk.gov.laa.ccms.data.mapper.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record UploadedDocumentXml(
    @JacksonXmlProperty(localName = "document_id") String documentId,
    @JacksonXmlProperty(localName = "document_type") String documentType,
    @JacksonXmlProperty(localName = "document_channel") String documentChannel,
    @JacksonXmlProperty(localName = "Text") String text
) {

}
