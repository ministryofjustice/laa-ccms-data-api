package uk.gov.laa.ccms.data.mapper.casedetails;

import java.util.Collections;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.ApplicationDetailsXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.ContactUserIdXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.CostLimitationXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.OrganisationXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.ScopeLimitationXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails.CategoryOfLawXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails.ClientXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails.CorrespondenceAddressXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails.ProceedingXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.applicationdetails.ProviderDetailsXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.otherparty.OtherPartyXml;
import uk.gov.laa.ccms.data.mapper.xml.casedetail.otherparty.PersonXml;
import uk.gov.laa.ccms.data.mapper.xml.common.AddressXml;
import uk.gov.laa.ccms.data.model.AddressDetail;
import uk.gov.laa.ccms.data.model.BaseClient;
import uk.gov.laa.ccms.data.model.CategoryOfLaw;
import uk.gov.laa.ccms.data.model.ContactDetail;
import uk.gov.laa.ccms.data.model.CostLimitation;
import uk.gov.laa.ccms.data.model.OfficeDetail;
import uk.gov.laa.ccms.data.model.OtherParty;
import uk.gov.laa.ccms.data.model.OtherPartyOrganisation;
import uk.gov.laa.ccms.data.model.OtherPartyPerson;
import uk.gov.laa.ccms.data.model.Proceeding;
import uk.gov.laa.ccms.data.model.ProviderDetail;
import uk.gov.laa.ccms.data.model.ProviderDetails;
import uk.gov.laa.ccms.data.model.ScopeLimitation;
import uk.gov.laa.ccms.data.model.SubmittedApplicationDetails;

/**
 * Mapper interface for transforming XML application details related objects to their associated
 * domain classes. This interface utilizes MapStruct for mapping properties.
 *
 * @author Jamie Briggs
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubmittedApplicationDetailsMapper {

  @Mapping(target = "fixedHearingDateInd", source = "fixedHearingDateIndicator")
  @Mapping(target = "highProfileCaseInd", source = "highProfileCaseIndicator")
  @Mapping(target = "preferredAddress", source = "preferredAddress")
  SubmittedApplicationDetails mapToSubmittedApplicationDetails(
      ApplicationDetailsXml submittedApplicationDetails);

  BaseClient mapToClientDetails(ClientXml client);

  @Mapping(target = "house", source = "houseOrTitle")
  @Mapping(target = "addressLine1", source = "addressLineOne")
  @Mapping(target = "addressLine2", source = "addressLineTwo")
  @Mapping(target = "addressLine3", source = "addressLineThree")
  @Mapping(target = "addressLine4", source = "addressLineFour")
  AddressDetail mapToAddressDetail(CorrespondenceAddressXml address);

  @Mapping(target = "house", source = "houseOrTitle")
  AddressDetail mapToAddressDetail(AddressXml address);

  @Mapping(target = "contactUserId.loginId", source = "contactUserId.userLoginId")
  @Mapping(target = "contactUserId.username", source = "contactUserId.userName")
  ProviderDetails mapToProviderDetail(ProviderDetailsXml provider);

  /**
   * Converts an integer into a list with one value relating to the office ID.
   *
   * @param providerOfficeId the provider office ID.
   *
   * @return a list containing one office detail including the provided office ID.
   */
  @Named("mapOfficeDetail")
  default List<OfficeDetail> mapPreferredAddress(Integer providerOfficeId) {
    OfficeDetail officeDetail = new OfficeDetail();
    officeDetail.setId(providerOfficeId);
    return Collections.singletonList(officeDetail);
  }

  /**
   * Maps a single {@link ContactUserIdXml} to a list containing a singular {@link ContactDetail}.
   *
   * @param contactUserId the contact user ID to map.
   *
   * @return a list containing one contact detail relating to the provider contacter user details.
   */
  @Named("mapContactDetail")
  default List<ContactDetail> mapContactDetails(ContactUserIdXml contactUserId) {
    ContactDetail contact = new ContactDetail();
    //contact.setId(contactUserId.getUserLoginId());
    //contact.setName(contactUserId.getUserName());
    return Collections.singletonList(contact);
  }

  CategoryOfLaw mapToCategoryOfLaw(CategoryOfLawXml categoryOfLaw);

  CostLimitation mapToCostLimitation(CostLimitationXml costLimitation);

  @Mapping(target = "sharedInd", source = "sharedIndicator")
  @Mapping(target = "person", source = "otherPartyDetail.person")
  @Mapping(target = "organisation", source = "otherPartyDetail.organisation")
  OtherParty mapToOtherParty(OtherPartyXml otherParty);

  @Mapping(target = "address.house", source = "address.houseOrTitle")
  OtherPartyPerson mapToOtherPartyPerson(PersonXml person);

  OtherPartyOrganisation mapToOtherPartyOrganisation(OrganisationXml organisation);


  @Mapping(target = "proceedingType", source = "proceedingDetails.proceedingType")
  @Mapping(target = "proceedingDescription", source = "proceedingDetails.proceedingDescription")
  @Mapping(target = "dateCostsValid", source = "proceedingDetails.dateCostsValid")
  @Mapping(target = "orderType", source = "proceedingDetails.orderType")
  @Mapping(target = "matterType", source = "proceedingDetails.matterType")
  @Mapping(target = "levelOfService", source = "proceedingDetails.levelOfService")
  @Mapping(target = "stage", source = "proceedingDetails.stage")
  @Mapping(target = "clientInvolvementType", source = "proceedingDetails.clientInvolvementType")
  @Mapping(target = "scopeLimitations", source = "proceedingDetails.scopeLimitations")
  Proceeding mapToProceedingDetail(ProceedingXml proceeding);

  ScopeLimitation mapToScopeLimitation(ScopeLimitationXml scopeLimitation);
}
