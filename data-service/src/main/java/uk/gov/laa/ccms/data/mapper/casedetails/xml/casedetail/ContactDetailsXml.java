package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Represents the contact details of an entity in the XML context. This record captures
 * various contact information fields to facilitate serialization or deserialization
 * of XML data.
 *
 * The fields in this record include:
 * - Home telephone number.
 * - Work telephone number.
 * - Mobile number.
 * - Email address.
 * - Fax number.
 *
 * Each field is annotated with {@code @JacksonXmlProperty} for XML mapping.
 */
public record ContactDetailsXml(
    @JacksonXmlProperty(localName = "TelephoneHome")
    String telephoneHome,
    @JacksonXmlProperty(localName = "TelephoneWork")
    String telephoneWork,
    @JacksonXmlProperty(localName = "MobileNumber")
    String mobileNumber,
    @JacksonXmlProperty(localName = "EmailAddress")
    String emailAddress,
    @JacksonXmlProperty(localName = "Fax")
    String fax) { }
