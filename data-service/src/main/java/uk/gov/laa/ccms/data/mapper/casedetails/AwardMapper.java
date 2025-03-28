package uk.gov.laa.ccms.data.mapper.casedetails;

import java.util.Collections;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.award.AwardXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.award.CostAwardXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.award.FinancialAwardXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.award.LandAwardXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.award.LiablePartyXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.award.OtherAssetXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.award.RecoveryXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.otherparty.OtherPartyXml;
import uk.gov.laa.ccms.data.model.Award;
import uk.gov.laa.ccms.data.model.CostAward;
import uk.gov.laa.ccms.data.model.FinancialAward;
import uk.gov.laa.ccms.data.model.LandAward;
import uk.gov.laa.ccms.data.model.OtherAsset;
import uk.gov.laa.ccms.data.model.Recovery;

/**
 * Mapper interface for transforming XML award related objects into their corresponding domain
 *  objects. This interface utilizes MapStruct for mapping properties.
 *
 * @author Jamie Briggs
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AwardMapper {

  @Mapping(target = "financialAward", source = "awardDetails.financialAward")
  @Mapping(target = "costAward", source = "awardDetails.costAward")
  @Mapping(target = "landAward", source = "awardDetails.landAward")
  @Mapping(target = "otherAsset", source = "awardDetails.otherAsset")
  @Mapping(target = "awardCategory", source = "awardDetails.awardCategory")
  Award mapToAward(AwardXml awardXml);

  FinancialAward mapToFinancialAward(FinancialAwardXml awardXml);

  /**
   * Transforms a list of LiablePartyXml objects into a list of their corresponding OtherPartyId
   *     values.
   *
   * @param liablePartyXmls a list of {@link LiablePartyXml} objects representing the liable
   *                        parties. If null, an empty list is returned.
   * @return a list of strings containing the OtherPartyId values from the input list of
   *     {@link LiablePartyXml} objects. If the input list is null, an empty list is returned.
   */
  default List<String> mapToLiableParties(List<LiablePartyXml> liablePartyXmls) {
    if (liablePartyXmls == null) {
      return Collections.emptyList();
    }
    return liablePartyXmls.stream().map(LiablePartyXml::getOtherPartyId).toList();
  }

  LandAward mapToLandAward(LandAwardXml awardXml);

  /**
   * Transforms a list of {@link OtherPartyXml} objects into a list of their corresponding
   * OtherPartyId values.
   *
   * @param otherProprietors a list of {@link OtherPartyXml} objects. If null, an
   *                         empty list is returned.
   * @return a list of strings containing the OtherPartyId values from the input
   *         list of {@link OtherPartyXml} objects. If the input list is null, an
   *         empty list is returned.
   */
  default List<String> mapToOtherProprietors(List<OtherPartyXml> otherProprietors) {
    if (otherProprietors == null) {
      return Collections.emptyList();
    }
    return otherProprietors.stream().map(OtherPartyXml::getOtherPartyId).toList();
  }

  CostAward mapToCostAward(CostAwardXml costAwardXml);

  @Mapping(target = "leaveOfCourtReqdInd", source = "leaveOfCourtRequiredIndicator")
  Recovery mapToRecovery(RecoveryXml recoveryXml);

  OtherAsset mapToOtherAsset(OtherAssetXml otherAssetXml);
}
