package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uk.gov.laa.ccms.data.entity.ClientDetail;
import uk.gov.laa.ccms.data.model.ClientDetails;
import uk.gov.laa.ccms.data.model.ClientSummary;

@Mapper(componentModel = "spring")
public interface ClientDetailsMapper {


  @Mapping(target = "postalCode",
      expression = "java(clientDetail.getAddress() != null ? "
          + "clientDetail.getAddress().replaceAll(\".*<PostalCode>(.*?)</PostalCode>"
          + ".*\", \"$1\") : \"\")")
  @Mapping(target = "homeOfficeReference", source = "homeOfficeNumber")
  @Mapping(target = "fullName",
      expression = "java(clientDetail.getFirstName() + ' ' + clientDetail.getSurname())")
  ClientSummary mapToClientSummary(ClientDetail clientDetail);

  ClientDetails mapToClientDetails(Page<ClientDetail> searchResults);

}
