package uk.gov.laa.ccms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import uk.gov.laa.ccms.data.model.TransactionStatus;

/**
 * Interface responsible for mapping {@link uk.gov.laa.ccms.data.entity.TransactionStatus} entities
 * to {@link TransactionStatus} objects. This interface utilizes MapStruct for transformation.
 *
 * @see uk.gov.laa.ccms.data.entity.TransactionStatus
 * @see TransactionStatus
 * @author Jamie Briggs
 */
@Mapper(componentModel = "spring")
public interface TransactionStatusMapper {

  /**
   * Maps a {@link uk.gov.laa.ccms.data.entity.TransactionStatus} to a {@link TransactionStatus}
   * object.
   *
   * @param entity the source {@link uk.gov.laa.ccms.data.entity.TransactionStatus} object.
   * @return a {@link TransactionStatus} object mapped from the source
   *     {@link uk.gov.laa.ccms.data.entity.TransactionStatus} object.
   */
  @Mapping(target = "submissionStatus", source = "status", qualifiedByName = "toUpperCase")
  @Mapping(target = "referenceNumber", source = "recordRefValue")
  TransactionStatus toTransactionStatus(uk.gov.laa.ccms.data.entity.TransactionStatus entity);

  /**
   * Converts the given string to uppercase. If the input string is null, the method returns null.
   *
   * @param value the input string to be converted to uppercase; may be null.
   * @return the uppercase version of the input string, or null if the input was null.
   */
  @Named("toUpperCase")
  static String toUpperCase(String value) {
    return value == null ? null : value.toUpperCase();
  }
}
