package uk.gov.laa.ccms.data.mapper.casedetails.xml;

import lombok.NoArgsConstructor;

/**
 * Provides namespace constants used across XML elements in the CaseDetail context.
 *
 * <p>The defined namespaces include:
 *
 * <ul>
 *   <li><b>caseNamespace</b>: Used for case-related XML data structures.
 *   <li><b>commonNamespace</b>: Used for common enterprise-wide XML data structures.
 * </ul>
 *
 * <p>This class cannot be initialized.
 *
 * @author Jamie Briggs
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class CaseDetailXmlNamespaces {

  public static final String CASE_NAMESPACE =
      "http://legalservices.gov.uk/CCMS/CaseManagement/Case/1.0/CaseBIM";
  public static final String COMMON_NAMESPACE =
      "http://legalservices.gov.uk/Enterprise/Common/1.0/Common";
}
