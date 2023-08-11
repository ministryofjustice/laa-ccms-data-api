package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import uk.gov.laa.ccms.data.entity.FeeEarner;
import uk.gov.laa.ccms.data.model.FeeEarnerDetail;

/**
 * Mapper interface for converting between {@link FeeEarner} entities and their detailed DTO
 * representations.
 *
 * <p>This interface provides methods for transforming {@code FeeEarner} entities to detailed
 * data transfer objects(DTOs) like {@code FeeEarnerDetail}. It leverages the MapStruct library,
 * with the Spring component model, for automatic generation of the implementation at
 * compile-time.</p>
 *
 * @see Mapper
 * @see FeeEarner
 * @see FeeEarnerDetail
 */
@Mapper(componentModel = "spring")
public interface FeeEarnerMapper {
  FeeEarnerDetail toFeeEarnerDetail(Page<FeeEarner> page);

}