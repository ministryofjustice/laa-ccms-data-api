package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import uk.gov.laa.ccms.data.entity.Proceeding;
import uk.gov.laa.ccms.data.model.ProceedingDetail;
import uk.gov.laa.ccms.data.model.ProceedingDetails;

/**
 * Mapper interface for converting between {@link Proceeding} entities and their detailed DTO
 * representations.
 *
 * <p>This interface provides methods for transforming {@code Proceeding} entities to detailed
 * data transfer objects(DTOs) like {@code ProceedingDetail}.
 * It leverages the MapStruct library, with the Spring component model, for automatic generation
 * of the implementation at compile-time.</p>
 *
 * @see Mapper
 * @see Proceeding
 * @see ProceedingDetail
 */
@Mapper(componentModel = "spring")
public interface ProceedingMapper {
  ProceedingDetails toProceedingDetails(Page<Proceeding> proceedings);

  ProceedingDetail toProceedingDetail(Proceeding proceeding);
}