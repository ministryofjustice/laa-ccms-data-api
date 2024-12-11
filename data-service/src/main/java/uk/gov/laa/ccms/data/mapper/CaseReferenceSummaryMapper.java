package uk.gov.laa.ccms.data.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.laa.ccms.data.model.CaseReferenceSummary;

/**
 * Mapper interface for converting a {@link String} value into a {@link CaseReferenceSummary}
 * object.
 *
 * @author Jamie Briggs
 * @see Mapper
 * @see CaseReferenceSummary
 * @see String
 */
@Mapper(componentModel = "spring")
public interface CaseReferenceSummaryMapper {

  /**
   * Maps a {@link String} value to a {@link CaseReferenceSummary} object by assigning the value to
   * the caseReferenceNumber field.
   *
   * @param value the input string to be mapped to the caseReferenceNumber field
   * @return a {@link CaseReferenceSummary} object containing the given value in the
   *     caseReferenceNumber field
   */
  @Mapping(target = "caseReferenceNumber", source = "value")
  CaseReferenceSummary map(String value);
}
