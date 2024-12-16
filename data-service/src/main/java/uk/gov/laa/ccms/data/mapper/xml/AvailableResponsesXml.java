package uk.gov.laa.ccms.data.mapper.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

public record AvailableResponsesXml(@JacksonXmlElementWrapper(useWrapping = false)
                                    @JacksonXmlProperty(localName = "Response")
                                    List<String> responses) {

}
