package uk.gov.laa.ccms.data.mapper.casedetails;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.priorauthority.PriorAuthorityAttributeXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.priorauthority.PriorAuthorityXml;
import uk.gov.laa.ccms.data.model.PriorAuthority;
import uk.gov.laa.ccms.data.model.PriorAuthorityAttribute;

/**
 * Mapper interface for transforming XML prior authority objects to their associated domain classes.
 * This interface utilizes MapStruct for mapping properties.
 *
 * @author Jamie Briggs
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PriorAuthorityMapper {

  PriorAuthority mapToPriorAuthority(PriorAuthorityXml priorAuthority);

  PriorAuthorityAttribute mapToPriorAuthorityAttribute(PriorAuthorityAttributeXml priorAuthority);
}
