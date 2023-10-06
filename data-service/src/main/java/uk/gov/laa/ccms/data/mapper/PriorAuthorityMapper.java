package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import uk.gov.laa.ccms.data.entity.PriorAuthority;
import uk.gov.laa.ccms.data.entity.PriorAuthorityType;
import uk.gov.laa.ccms.data.model.PriorAuthorityDetail;
import uk.gov.laa.ccms.data.model.PriorAuthorityTypeDetail;
import uk.gov.laa.ccms.data.model.PriorAuthorityTypeDetails;
import uk.gov.laa.ccms.data.model.ProviderDetail;

/**
 * Mapper interface for converting between {@link PriorAuthorityType} entities and their detailed
 * DTO representations.
 *
 * <p>This interface provides methods for transforming {@code PriorAuthorityType} entities to
 * detailed data transfer objects(DTOs) like {@code PriorAuthorityTypeDetail}.
 * It leverages the MapStruct library, with the Spring component model, for automatic generation
 * of the implementation at compile-time.</p>
 *
 * @see Mapper
 * @see PriorAuthorityType
 * @see ProviderDetail
 */
@Mapper(componentModel = "spring")
public interface PriorAuthorityMapper {

  PriorAuthorityTypeDetails toPriorAuthorityTypeDetails(
      Page<PriorAuthorityType> priorAuthorityTypes);

  PriorAuthorityTypeDetail toPriorAuthorityTypeDetail(PriorAuthorityType priorAuthorityType);

  PriorAuthorityDetail toPriorAuthorityDetail(PriorAuthority priorAuthority);
}