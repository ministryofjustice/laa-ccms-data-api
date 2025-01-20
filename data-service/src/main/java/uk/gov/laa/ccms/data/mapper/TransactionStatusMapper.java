package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import uk.gov.laa.ccms.data.model.TransactionStatus;

@Mapper(componentModel = "spring")
public interface TransactionStatusMapper {

  @Mapping(target = "submissionStatus", source = "status", qualifiedByName = "toUpperCase")
  @Mapping(target = "referenceNumber", source = "recordRefValue")
  TransactionStatus toTransactionStatus(uk.gov.laa.ccms.data.entity.TransactionStatus entity);

  @Named("toUpperCase")
  static String toUpperCase(String value) {
    return value == null ? null : value.toUpperCase();
  }
}
