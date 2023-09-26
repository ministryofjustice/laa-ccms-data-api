package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uk.gov.laa.ccms.data.entity.ScopeLimitation;
import uk.gov.laa.ccms.data.model.ScopeLimitationDetail;
import uk.gov.laa.ccms.data.model.ScopeLimitationDetails;

/**
 * Mapper interface for converting between {@link uk.gov.laa.ccms.data.entity.ScopeLimitation}
 * entities and their detailed DTO representations.
 *
 * <p>This interface provides methods for transforming {@code Proceeding} entities to detailed
 * data transfer objects(DTOs) like {@code ProceedingDetail}.
 * It leverages the MapStruct library, with the Spring component model, for automatic generation
 * of the implementation at compile-time.</p>
 *
 * @see Mapper
 * @see ScopeLimitation
 * @see ScopeLimitationDetail
 */
@Mapper(componentModel = "spring")
public interface ScopeLimitationMapper {
  ScopeLimitationDetails toScopeLimitationDetails(Page<ScopeLimitation> scopeLimitations);

  @Mapping(target = "id.categoryOfLawCode", source = "categoryOfLaw")
  @Mapping(target = "id.levelOfServiceCode", source = "levelOfService")
  @Mapping(target = "id.scopeLimitations", source = "scopeLimitations")
  @Mapping(target = "id.matterType", source = "matterType")
  @Mapping(target = "id.proceedingCode", source = "proceedingCode")
  ScopeLimitation toScopeLimitation(ScopeLimitationDetail scopeLimitationDetail);

  @InheritInverseConfiguration
  ScopeLimitationDetail toScopeLimitationDetail(ScopeLimitation scopeLimitation);
}