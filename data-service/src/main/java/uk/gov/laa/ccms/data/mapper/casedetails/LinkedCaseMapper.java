package uk.gov.laa.ccms.data.mapper.casedetails;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.LinkedCaseXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.applicationdetails.ClientXml;
import uk.gov.laa.ccms.data.model.BaseClient;
import uk.gov.laa.ccms.data.model.LinkedCase;

/**
 * Mapper interface for transforming XML linked case objects to their associated domain classes.
 * This interface utilizes MapStruct for mapping properties.
 *
 * @author Jamie Briggs
 */
@Mapper(componentModel = ComponentModel.SPRING)
public interface LinkedCaseMapper {

  @Mapping(target = "categoryOfLawDesc", source = "categoryOfLawDescription")
  @Mapping(target = "publicFundingAppliedInd", source = "publicFundingAppliedIndicator")
  LinkedCase mapToLinkedCase(LinkedCaseXml linkedCase);

  BaseClient mapToBaseClient(ClientXml client);
}
