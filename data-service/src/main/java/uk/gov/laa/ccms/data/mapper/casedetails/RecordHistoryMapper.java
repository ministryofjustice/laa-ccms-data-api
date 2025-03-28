package uk.gov.laa.ccms.data.mapper.casedetails;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.casedetail.RecordHistoryXml;
import uk.gov.laa.ccms.data.mapper.casedetails.xml.common.UserXml;
import uk.gov.laa.ccms.data.model.RecordHistory;
import uk.gov.laa.ccms.data.model.UserDetail;

/**
 * Mapper interface for transforming XML record history objects to their associated domain classes.
 * This interface utilizes MapStruct for mapping properties.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RecordHistoryMapper {

  RecordHistory mapToRecordHistory(RecordHistoryXml recordHistoryXml);

  @Mapping(target = "userId", source = "userLoginId")
  @Mapping(target = "username", source = "userName")
  @Mapping(target = "provider", ignore = true)
  @Mapping(target = "firms", ignore = true)
  @Mapping(target = "functions", ignore = true)
  @Mapping(target = "loginId", ignore = true)
  UserDetail mapToUserDetail(UserXml userId);
}
