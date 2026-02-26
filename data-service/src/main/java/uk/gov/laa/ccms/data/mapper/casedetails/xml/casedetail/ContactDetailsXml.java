package uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Represents the contact details of an entity in the XML context. This record captures various
 * contact information fields to facilitate serialization or deserialization of XML data.
 *
 * <p>The fields in this record include:
 *
 * <ul>
 *   <li>Home telephone number.
 *   <li>Work telephone number.
 *   <li>Mobile number.
 *   <li>Email address.
 *   <li>Fax number.
 * </ul>
 *
 * @author Jamie Briggs
 */
public record ContactDetailsXml(
    @JacksonXmlProperty(localName = "TelephoneHome") String telephoneHome,
    @JacksonXmlProperty(localName = "TelephoneWork") String telephoneWork,
    @JacksonXmlProperty(localName = "MobileNumber") String mobileNumber,
    @JacksonXmlProperty(localName = "EmailAddress") String emailAddress,
    @JacksonXmlProperty(localName = "Fax") String fax) {}
