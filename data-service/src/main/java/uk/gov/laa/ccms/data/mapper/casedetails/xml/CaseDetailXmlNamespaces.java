package uk.gov.laa.ccms.data.mapper.casedetails.xml;

import lombok.NoArgsConstructor;

/**
 * Provides namespace constants used across XML elements in the CaseDetail context.
 *
 * <p>The defined namespaces include:</p>
 * <ul>
 * <li><b>caseNamespace</b>: Used for case-related XML data structures.</li>
 * <li><b>commonNamespace</b>: Used for common enterprise-wide XML data structures.</li>
 * </ul>
 *
 * <p>This class cannot be initialized.</p>
 *
 * @author Jamie Briggs
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class CaseDetailXmlNamespaces {

  public static final String CASE_NAMESPACE
      = "http://legalservices.gov.uk/CCMS/CaseManagement/Case/1.0/CaseBIM";
  public static final String COMMON_NAMESPACE
      = "http://legalservices.gov.uk/Enterprise/Common/1.0/Common";
}
