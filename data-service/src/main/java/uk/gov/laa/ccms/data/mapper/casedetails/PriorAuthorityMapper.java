package uk.gov.laa.ccms.data.mapper.casedetails;

import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.priorauthority.PriorAuthorityAttributeXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.priorauthority.PriorAuthorityXml;
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

  /**
   * Ensures strings are not empty, which helps support mapping to parsed values such as
   *     {@code BigDecimal}.
   *
   * @param value The string which is currently being mapped.
   * @return Returns true if the string is not empty or null.
   */
  @Condition
  default boolean isNotEmpty(String value) {
    return value != null && !value.isEmpty();
  }
}
