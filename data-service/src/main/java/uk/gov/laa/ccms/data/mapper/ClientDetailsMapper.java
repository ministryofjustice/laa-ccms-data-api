package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uk.gov.laa.ccms.data.entity.ClientDetail;
import uk.gov.laa.ccms.data.model.ClientDetails;
import uk.gov.laa.ccms.data.model.ClientSummary;

/**
 * Interface responsible for mapping objects from {@link ClientDetail} to {@link ClientSummary}
 *     and {@link ClientDetails} objects. This interface utilizes MapStruct
 *     or mapping properties.
 *
 * @see ClientDetail
 * @see ClientSummary
 * @see ClientDetails
 *
 * @author Jamie Briggs
 */
@Mapper(componentModel = "spring")
public interface ClientDetailsMapper {


  /**
   * Maps a {@link ClientDetail} object to a {@link ClientSummary} object.
   *
   * @param clientDetail the {@link ClientDetail} instance to be mapped
   * @return a {@link ClientSummary} object with mapped properties
   */
  @Mapping(target = "postalCode",
      expression = "java(clientDetail.getAddress() != null ? "
          + "clientDetail.getAddress().replaceAll(\".*<PostalCode>(.*?)</PostalCode>"
          + ".*\", \"$1\") : \"\")")
  @Mapping(target = "homeOfficeReference", source = "homeOfficeNumber")
  @Mapping(target = "fullName",
      expression = "java(clientDetail.getFirstName() + ' ' + clientDetail.getSurname())")
  ClientSummary mapToClientSummary(ClientDetail clientDetail);

  /**
   * Maps the given pageable search results of {@link ClientDetail} entities
   *     into a {@link ClientDetails} object.
   *
   * @param searchResults the paginated list of {@link ClientDetail} instances to be mapped
   * @return a {@link ClientDetails} object containing transformed search results
   *     along with pagination metadata.
   */
  ClientDetails mapToClientDetails(Page<ClientDetail> searchResults);

}
