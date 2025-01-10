package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uk.gov.laa.ccms.data.entity.CaseSearch;
import uk.gov.laa.ccms.data.model.CaseDetails;
import uk.gov.laa.ccms.data.model.CaseSummary;

@Mapper(componentModel = "spring")
public interface CaseSearchMapper {

  @Mapping(target = "caseReferenceNumber", source = "lscCaseReference")
  @Mapping(target = "providerCaseReferenceNumber", source = "cisCaseReference")
  @Mapping(target = "feeEarnerName", source = "feeEarner")
  @Mapping(target = "caseStatusDisplay", source = "displayCaseStatus")
  @Mapping(target = "categoryOfLaw", source = "categoryOfLawDescription")
  @Mapping(target = "client.clientReferenceNumber", source = "clientPartyId")
  @Mapping(target = "client.firstName", source = "personFirstName")
  @Mapping(target = "client.surname", source = "personLastName")
  CaseSummary toCaseSummary(CaseSearch search);

  CaseDetails toCaseDetails(Page<CaseSearch> searchResults);
}
