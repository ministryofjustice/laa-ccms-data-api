package uk.gov.laa.ccms.data.mapper.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

/**
 * Represents a collection of available responses in XML format.
 *
 * <p>This record maps to an XML structure where the "Response" elements are provided as a list
 * without any additional wrapping element. Each entry in the list corresponds to an individual
 * response.</p>
 *
 * <p>It is primarily used for mapping XML data to Java objects
 * using the Jackson data format library.</p>
 *
 * @param responses A list of responses available
 * @author Jamie Briggs
 */
public record AvailableResponsesXml(@JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Response")
    List<String> responses) {

}
