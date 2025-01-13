package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import uk.gov.laa.ccms.data.entity.CaseSearch;
import uk.gov.laa.ccms.data.model.CaseDetails;
import uk.gov.laa.ccms.data.model.CaseSummary;

/**
 * Interface responsible for mapping {@link CaseSearch} objects to {@link CaseSummary}, or
 * {@link CaseDetails} when paginated. This interface utilizes MapStruct for transformation.
 *
 * @see CaseSearch
 * @see CaseDetails
 * @see CaseSummary
 *
 * @author Jamie Briggs
 */
@Mapper(componentModel = "spring")
public interface CaseSearchMapper {

  /**
   * Maps a {@link CaseSearch} object to a {@link CaseSummary} object.
   *
   * @param search the source {@link CaseSearch} object to be mapped.
   *
   * @return a {@link CaseSummary} object containing the mapped case.
   */
  @Mapping(target = "caseReferenceNumber", source = "lscCaseReference")
  @Mapping(target = "providerCaseReferenceNumber", source = "cisCaseReference")
  @Mapping(target = "feeEarnerName", source = "feeEarner")
  @Mapping(target = "caseStatusDisplay", source = "displayCaseStatus")
  @Mapping(target = "categoryOfLaw", source = "categoryOfLawDescription")
  @Mapping(target = "client.clientReferenceNumber", source = "clientPartyId")
  @Mapping(target = "client.firstName", source = "personFirstName")
  @Mapping(target = "client.surname", source = "personLastName")
  CaseSummary toCaseSummary(CaseSearch search);

  /**
   * Maps a {@link Page} of {@link CaseSearch} objects to a {@link CaseDetails} object.
   *
   * @param searchResults a {@link Page} containing {@link CaseSearch} entities to be mapped.
   *
   * @return a {@link CaseDetails} object containing the mapped case details along with pagination
   *     details.
   */
  CaseDetails toCaseDetails(Page<CaseSearch> searchResults);
}
