package uk.gov.laa.ccms.data.mapper.casedetails.xml.common;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Message object, which contains fields to capture the status of a case returned from EBS.
 *
 * @param code The code returned from EBS (200, 404, 500).
 * @param status The status of the case.
 * @param text Description of the message.
 */
public record MessageXml(
    @JacksonXmlProperty(localName = "Code")
    String code,
    @JacksonXmlProperty(localName = "Status")
    String status,
    @JacksonXmlProperty(localName = "Text")
    String text) { }
