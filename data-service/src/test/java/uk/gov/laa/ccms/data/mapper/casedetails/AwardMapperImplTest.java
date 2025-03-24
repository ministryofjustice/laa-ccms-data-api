package uk.gov.laa.ccms.data.mapper.casedetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.award.ServiceAddressXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.award.AmountXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.award.AwardDetailsXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.award.AwardXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.award.CostAwardXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.award.FinancialAwardXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.award.LandAwardXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.award.LiablePartyXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.award.OfferedAmountXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.award.OtherAssetXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.award.RecoveredAmountXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.award.RecoveryXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.award.TimeRelatedAwardXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.award.ValuationXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.otherparty.OtherPartyXml;
import uk.gov.laa.ccms.data.model.Award;
import uk.gov.laa.ccms.data.model.CostAward;
import uk.gov.laa.ccms.data.model.FinancialAward;
import uk.gov.laa.ccms.data.model.LandAward;
import uk.gov.laa.ccms.data.model.OfferedAmount;
import uk.gov.laa.ccms.data.model.OtherAsset;
import uk.gov.laa.ccms.data.model.RecoveredAmount;
import uk.gov.laa.ccms.data.model.Recovery;
import uk.gov.laa.ccms.data.model.ServiceAddress;
import uk.gov.laa.ccms.data.model.TimeRelatedAward;
import uk.gov.laa.ccms.data.model.Valuation;

@DisplayName("Award mapper implementation test")
public class AwardMapperImplTest {

  AwardMapper mapper = new AwardMapperImpl();

  @Test
  @DisplayName("Should map root level award values")
  void shouldMapRootLevelAwardValues(){
    // Given
    AwardXml awardXml = AwardXml.builder()
        .awardId("123")
        .awardType("Type")
        .deleteAllowed(true)
        .updateAllowed(true)
        .awardDetails(AwardDetailsXml.builder().awardCategory("Category").build())
        .build();
    // When
    Award result = mapper.mapToAward(awardXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getAwardId()).isEqualTo("123");
      softly.assertThat(result.getAwardType()).isEqualTo("Type");
      softly.assertThat(result.getDeleteAllowed()).isTrue();
      softly.assertThat(result.getUpdateAllowed()).isTrue();
      softly.assertThat(result.getAwardCategory()).isEqualTo("Category");
    });
  }

  @Test
  @DisplayName("Should map financial award details")
  void shouldMapFinancialAwardDetails(){
    // Given
    FinancialAwardXml financialAwardXml = getFinancialAwardXml();
    // When
    FinancialAward result = mapper.mapToFinancialAward(financialAwardXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getOrderDate()).isEqualTo(LocalDate.of(2010, 2, 3));
      softly.assertThat(result.getAmount()).isEqualTo(BigDecimal.valueOf(20l));
      softly.assertThat(result.getInterimAward()).isEqualTo(BigDecimal.valueOf(15l));
      softly.assertThat(result.getAwardedBy()).isEqualTo("Awarded By");
      softly.assertThat(result.getAwardJustifications()).isEqualTo("Award Justifications");
      softly.assertThat(result.getStatutoryChangeReason()).isEqualTo("Statutory Change Reason");
      softly.assertThat(result.getOtherDetails()).isEqualTo("Other Details");
      softly.assertThat(result.getLiableParties()).containsExactlyInAnyOrder("Party 1", "Party 2");
      softly.assertThat(result.getOrderDateServed()).isEqualTo(LocalDate.of(2011, 3, 4));
      ServiceAddress serviceAddress = result.getServiceAddress();
      softly.assertThat(serviceAddress.getAddressLine1()).isEqualTo("Line 1");
      softly.assertThat(serviceAddress.getAddressLine2()).isEqualTo("Line 2");
      softly.assertThat(serviceAddress.getAddressLine3()).isEqualTo("Line 3");
      softly.assertThat(result.getRecovery()).isNotNull();
    });
  }
  
  @Test
  @DisplayName("Should map land award values")
  void shouldMapLandAwardValues(){
    // Given
    LandAwardXml landAwardXml = LandAwardXml.builder()
        .orderDate(LocalDate.of(2015, 5, 5))
        .description("Description")
        .titleNo("Title No")
        .disputedPercentage("50")
        .awardedPercentage("45")
        .mortgageAmountDue("100000")
        .equity("Equity")
        .awardedBy("Awarded By")
        .recovery("Recovery")
        .noRecoveryDetails("No recovery details")
        .statChargeExemptReason("Stat charge exempt reason")
        .landChargeRegistration("Land charge registration")
        .registrationRef("Registration ref")
        .otherProprietors(Arrays.asList(
            OtherPartyXml.builder().otherPartyId("Proprietor 1").build(),
            OtherPartyXml.builder().otherPartyId("Proprietor 2").build()
        ))
        .propertyAddress(ServiceAddressXml.builder()
            .addressLine1("Line 1")
            .addressLine2("Line 2")
            .addressLine3("Line 3").build())
        .valuation(ValuationXml.builder()
            .amount("100")
            .criteria("Criteria")
            .date(LocalDate.of(2015, 5, 5))
            .build())
        .timeRelatedAward(TimeRelatedAwardXml.builder()
            .awardType("Type")
            .description("Description")
            .amount("50")
            .awardTriggeringEvent("Triggering event")
            .awardDate(LocalDate.of(2015, 10, 10))
            .otherDetails("Other details")
            .build())
        .build();
    // When
    LandAward result = mapper.mapToLandAward(landAwardXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getOrderDate()).isEqualTo(LocalDate.of(2015, 5, 5));
      softly.assertThat(result.getDescription()).isEqualTo("Description");
      softly.assertThat(result.getTitleNo()).isEqualTo("Title No");
      softly.assertThat(result.getDisputedPercentage()).isEqualTo(BigDecimal.valueOf(50l));
      softly.assertThat(result.getAwardedPercentage()).isEqualTo(BigDecimal.valueOf(45l));
      softly.assertThat(result.getMortgageAmountDue()).isEqualTo(BigDecimal.valueOf(100000l));
      softly.assertThat(result.getEquity()).isEqualTo("Equity");
      softly.assertThat(result.getAwardedBy()).isEqualTo("Awarded By");
      softly.assertThat(result.getRecovery()).isEqualTo("Recovery");
      softly.assertThat(result.getNoRecoveryDetails()).isEqualTo("No recovery details");
      softly.assertThat(result.getStatChargeExemptReason()).isEqualTo("Stat charge exempt reason");
      softly.assertThat(result.getLandChargeRegistration()).isEqualTo("Land charge registration");
      softly.assertThat(result.getRegistrationRef()).isEqualTo("Registration ref");
      softly.assertThat(result.getOtherProprietors()).containsExactlyInAnyOrder("Proprietor 1", "Proprietor 2");
      ServiceAddress propertyAddress = result.getPropertyAddress();
      softly.assertThat(propertyAddress.getAddressLine1()).isEqualTo("Line 1");
      softly.assertThat(propertyAddress.getAddressLine2()).isEqualTo("Line 2");
      softly.assertThat(propertyAddress.getAddressLine3()).isEqualTo("Line 3");
      Valuation valuation = result.getValuation();
      softly.assertThat(valuation.getAmount()).isEqualTo(BigDecimal.valueOf(100l));
      softly.assertThat(valuation.getCriteria()).isEqualTo("Criteria");
      softly.assertThat(valuation.getDate()).isEqualTo(LocalDate.of(2015, 5, 5));
      TimeRelatedAward timeRelatedAward = result.getTimeRelatedAward();
      softly.assertThat(timeRelatedAward.getAwardType()).isEqualTo("Type");
      softly.assertThat(timeRelatedAward.getDescription()).isEqualTo("Description");
      softly.assertThat(timeRelatedAward.getAmount()).isEqualTo(BigDecimal.valueOf(50l));
      softly.assertThat(timeRelatedAward.getAwardTriggeringEvent()).isEqualTo("Triggering event");
      softly.assertThat(timeRelatedAward.getAwardDate()).isEqualTo(LocalDate.of(2015, 10, 10));
      softly.assertThat(timeRelatedAward.getOtherDetails()).isEqualTo("Other details");
    });
  }

  @Test
  @DisplayName("Should map cost award values")
  void shouldMapCostAwardValues(){
    // Given
    CostAwardXml costAwardXml = getCostAwardXml();
    // When
    CostAward result = mapper.mapToCostAward(costAwardXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getOrderDate()).isEqualTo(LocalDate.of(2020, 3, 3));
      softly.assertThat(result.getCourtAssessmentStatus()).isEqualTo("Court assessment status");
      softly.assertThat(result.getPreCertificateAwardLsc()).isEqualTo(BigDecimal.valueOf(100L));
      softly.assertThat(result.getPreCertificateAwardOth()).isEqualTo(BigDecimal.valueOf(120L));
      softly.assertThat(result.getCertificateCostRateLsc()).isEqualTo(BigDecimal.valueOf(140L));
      softly.assertThat(result.getCertificateCostRateMarket()).isEqualTo(BigDecimal.valueOf(160L));
      softly.assertThat(result.getAwardedBy()).isEqualTo("Awarded By");
      softly.assertThat(result.getInterestAwardedRate()).isEqualTo(BigDecimal.valueOf(180L));
      softly.assertThat(result.getInterestAwardedStartDate()).isEqualTo(LocalDate.of(2020, 5, 3));
      softly.assertThat(result.getOtherDetails()).isEqualTo("Other Details");
      softly.assertThat(result.getLiableParties()).containsExactlyInAnyOrder("Party 1", "Party 2");
      softly.assertThat(result.getOrderDateServed()).isEqualTo(LocalDate.of(2020, 6, 3));
      ServiceAddress propertyAddress = result.getServiceAddress();
      softly.assertThat(propertyAddress.getAddressLine1()).isEqualTo("Line 1");
      softly.assertThat(propertyAddress.getAddressLine2()).isEqualTo("Line 2");
      softly.assertThat(propertyAddress.getAddressLine3()).isEqualTo("Line 3");
      softly.assertThat(result.getRecovery()).isNotNull();
    });
  }


  @Test
  @DisplayName("Should map cost award details recovery values")
  void shouldMapCostAwardDetailsRecoveryValues(){
    // Given
    RecoveryXml recoveryXml = getRecoveryXml();
    // When
    Recovery result = mapper.mapToRecovery(recoveryXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getAwardValue()).isEqualTo(BigDecimal.valueOf(20l));
      softly.assertThat(result.getUnRecoveredAmount()).isEqualTo(BigDecimal.valueOf(10l));
      softly.assertThat(result.getLeaveOfCourtReqdInd()).isTrue();
      OfferedAmount offeredAmount = result.getOfferedAmount();
      softly.assertThat(offeredAmount.getAmount()).isEqualTo(BigDecimal.valueOf(40l));
      softly.assertThat(offeredAmount.getConditionsOfOffer()).isEqualTo("Conditions of offer");
      RecoveredAmount recoveredAmount = result.getRecoveredAmount();
      softly.assertThat(recoveredAmount.getSolicitor().getAmount()).isEqualTo(BigDecimal.valueOf(10l));
      softly.assertThat(recoveredAmount.getSolicitor().getPaidToLsc()).isEqualTo(BigDecimal.valueOf(20l));
      softly.assertThat(recoveredAmount.getSolicitor().getDateReceived()).isEqualTo(LocalDate.of(2020, 1, 1));
      softly.assertThat(recoveredAmount.getCourt().getAmount()).isEqualTo(BigDecimal.valueOf(20l));
      softly.assertThat(recoveredAmount.getCourt().getPaidToLsc()).isEqualTo(BigDecimal.valueOf(30l));
      softly.assertThat(recoveredAmount.getCourt().getDateReceived()).isEqualTo(LocalDate.of(2020, 2, 1));
      softly.assertThat(recoveredAmount.getClient().getAmount()).isEqualTo(BigDecimal.valueOf(30l));
      softly.assertThat(recoveredAmount.getClient().getPaidToLsc()).isEqualTo(BigDecimal.valueOf(40l));
      softly.assertThat(recoveredAmount.getClient().getDateReceived()).isEqualTo(LocalDate.of(2020, 3, 1));
    });
  }

  @Test
  @DisplayName("Should map cost award details valuation values")
  void shouldMapOtherAssetValues(){
    // Given
    OtherAssetXml otherAssetXml = OtherAssetXml.builder()
        .orderDate(LocalDate.of(2020, 4, 4))
        .description("Description")
        .valuation(ValuationXml.builder()
            .amount("100")
            .criteria("Criteria")
            .date(LocalDate.of(2020, 3, 3))
            .build())
        .awardedAmount("100")
        .awardedPercentage("50")
        .recoveredAmount("150")
        .recoveredPercentage("70")
        .disputedAmount("200")
        .disputedPercentage("80")
        .awardedBy("Awarded By")
        .recovery("Recovery")
        .noRecoveryDetails("No recovery details")
        .heldBy(Arrays.asList(
            OtherPartyXml.builder().otherPartyId("Held By 1").build(),
            OtherPartyXml.builder().otherPartyId("Held By 2").build()
        ))
        .statChargeExemptReason("Stat charge exempt reason")
        .timeRelatedAward(TimeRelatedAwardXml.builder()
            .awardType("Award type")
            .description("Description")
            .amount("50")
            .awardDate(LocalDate.of(2020, 10, 10))
            .otherDetails("Other details")
            .build())
        .build();
    // When
    OtherAsset result = mapper.mapToOtherAsset(otherAssetXml);
    // Then
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(result.getOrderDate()).isEqualTo(LocalDate.of(2020, 4, 4));
      softly.assertThat(result.getDescription()).isEqualTo("Description");
      Valuation valuation = result.getValuation();
      softly.assertThat(valuation.getAmount()).isEqualTo(BigDecimal.valueOf(100l));
      softly.assertThat(valuation.getCriteria()).isEqualTo("Criteria");
      softly.assertThat(valuation.getDate()).isEqualTo(LocalDate.of(2020, 3, 3));
      softly.assertThat(result.getAwardedAmount()).isEqualTo(BigDecimal.valueOf(100l));
      softly.assertThat(result.getAwardedPercentage()).isEqualTo(BigDecimal.valueOf(50l));
      softly.assertThat(result.getRecoveredAmount()).isEqualTo(BigDecimal.valueOf(150l));
      softly.assertThat(result.getRecoveredPercentage()).isEqualTo(BigDecimal.valueOf(70l));
      softly.assertThat(result.getDisputedAmount()).isEqualTo(BigDecimal.valueOf(200l));
      softly.assertThat(result.getDisputedPercentage()).isEqualTo(BigDecimal.valueOf(80l));
      softly.assertThat(result.getAwardedBy()).isEqualTo("Awarded By");
      softly.assertThat(result.getRecovery()).isEqualTo("Recovery");
      softly.assertThat(result.getNoRecoveryDetails()).isEqualTo("No recovery details");
      softly.assertThat(result.getHeldBy()).containsExactlyInAnyOrder("Held By 1", "Held By 2");
      TimeRelatedAward timeRelatedAward = result.getTimeRelatedAward();
      softly.assertThat(timeRelatedAward.getAwardType()).isEqualTo("Award type");
      softly.assertThat(timeRelatedAward.getDescription()).isEqualTo("Description");
      softly.assertThat(timeRelatedAward.getAmount()).isEqualTo(BigDecimal.valueOf(50l));
      softly.assertThat(timeRelatedAward.getAwardDate()).isEqualTo(LocalDate.of(2020, 10, 10));
      softly.assertThat(timeRelatedAward.getOtherDetails()).isEqualTo("Other details");

      softly.assertThat(result.getStatChargeExemptReason()).isEqualTo("Stat charge exempt reason");
    });
  }

  private static RecoveryXml getRecoveryXml() {
    return RecoveryXml.builder()
        .awardValue("20")
        .unRecoveredAmount("10")
        .leaveOfCourtRequiredIndicator(true)
        .offeredAmount(OfferedAmountXml.builder().amount("40")
            .conditionsOfOffer("Conditions of offer").build())
        .recoveredAmount(RecoveredAmountXml.builder()
            .solicitor(AmountXml.builder().amount("10").paidToLsc(20l)
                .dateReceived(LocalDate.of(2020, 1, 1)).build())
            .court(AmountXml.builder().amount("20").paidToLsc(30l)
                .dateReceived(LocalDate.of(2020, 2, 1)).build())
            .client(AmountXml.builder().amount("30").paidToLsc(40l)
                .dateReceived(LocalDate.of(2020, 3, 1)).build())
            .build())
        .build();
  }



  private static FinancialAwardXml getFinancialAwardXml() {
    return FinancialAwardXml.builder()
        .orderDate(LocalDate.of(2010, 2, 3))
        .amount("20")
        .interimAward("15")
        .awardedBy("Awarded By")
        .awardJustifications("Award Justifications")
        .statutoryChangeReason("Statutory Change Reason")
        .otherDetails("Other Details")
        .liableParties(Arrays.asList(
            LiablePartyXml.builder().otherPartyId("Party 1").build(),
            LiablePartyXml.builder().otherPartyId("Party 2").build()
        ))
        .orderDateServed(LocalDate.of(2011, 3, 4))
        .serviceAddress(ServiceAddressXml.builder()
            .addressLine1("Line 1")
            .addressLine2("Line 2")
            .addressLine3("Line 3").build())
        .recovery(getRecoveryXml())
        .build();
  }



  private static CostAwardXml getCostAwardXml() {
    return CostAwardXml.builder()
        .orderDate(LocalDate.of(2020, 3, 3))
        .courtAssessmentStatus("Court assessment status")
        .preCertificateAwardLsc("100")
        .preCertificateAwardOth("120")
        .certificateCostRateLsc("140")
        .certificateCostRateMarket("160")
        .awardedBy("Awarded By")
        .interestAwardedRate("180")
        .interestAwardedStartDate(LocalDate.of(2020, 5, 3))
        .otherDetails("Other Details")
        .liableParties(Arrays.asList(
            LiablePartyXml.builder().otherPartyId("Party 1").build(),
            LiablePartyXml.builder().otherPartyId("Party 2").build()
        ))
        .orderDateServed(LocalDate.of(2020, 6, 3))
        .serviceAddress(ServiceAddressXml.builder()
            .addressLine1("Line 1")
            .addressLine2("Line 2")
            .addressLine3("Line 3")
            .build())
        .recovery(getRecoveryXml())
        .build();
  }


}
