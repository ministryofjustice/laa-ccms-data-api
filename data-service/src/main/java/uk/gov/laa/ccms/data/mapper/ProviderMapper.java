package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import uk.gov.laa.ccms.data.entity.FeeEarner;
import uk.gov.laa.ccms.data.entity.Office;
import uk.gov.laa.ccms.data.entity.Provider;
import uk.gov.laa.ccms.data.model.ContactDetail;
import uk.gov.laa.ccms.data.model.OfficeDetail;
import uk.gov.laa.ccms.data.model.ProviderDetail;

/**
 * Mapper interface for converting between {@link Provider} entities and their detailed DTO
 * representations.
 *
 * <p>This interface provides methods for transforming {@code Provider} entities to detailed
 * data transfer objects(DTOs) like {@code ProviderDetail}. It leverages the MapStruct library, with
 * the Spring component model, for automatic generation of the implementation at compile-time.</p>
 *
 * @see Mapper
 * @see uk.gov.laa.ccms.data.entity.Provider
 * @see uk.gov.laa.ccms.data.model.ProviderDetail
 */
@Mapper(componentModel = "spring")
public interface ProviderMapper {

  ProviderDetail toProviderDetail(Provider provider);

  OfficeDetail toOfficeDetail(Office office);

  ContactDetail toContactDetail(FeeEarner feeEarner);
}