package uk.gov.laa.ccms.data.service;

import java.lang.reflect.Field;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;

/**
 * Abstract Service Class to hold any common functionality across the EbsApi Services.
 */
public abstract class AbstractEbsDataService {

  private static final String QUERY_PARAM_WILDCARD = "*";

  /**
   * Method to dynamically build a custom ExampleMatcher based on the field values
   * of the supplied example object. By default, the ExampleMatcher will perform an exact match
   * on all fields.
   * <p/>
   * If a field value starts AND ends with the wildcard character, a 'contains' Matcher will be
   * added for the field.
   * Alternatively, if a field value starts with the wildcard character, an 'endsWith' Matcher
   * will be added.
   * Or if a field value ends with the wildcard character, a 'startsWith' Matcher will be added for
   * that field.
   *
   * @param example - the object to interrogate for wildcarded field values
   * @return an ExampleMatcher that does an exact match by default, with modifications for any
   *     wildcarded field values
   */
  protected ExampleMatcher getWildcardMatcher(Object example) {
    ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll();
    String fieldValue;
    for (Field field : example.getClass().getDeclaredFields()) {
      field.setAccessible(true);

      try {
        // Ignore any non-String or null fields
        if (!field.getType().equals(String.class)
            || field.get(example) == null) {
          continue;
        }

        fieldValue = (String) field.get(example);
        // If the query param has wildcard at start and end, set the matcher to 'contains'
        if (fieldValue.startsWith(QUERY_PARAM_WILDCARD)
            && fieldValue.endsWith(QUERY_PARAM_WILDCARD)) {
          exampleMatcher = exampleMatcher.withMatcher(field.getName(),
              GenericPropertyMatchers.contains());
          // Update the field with the wildcard-removed value
          field.set(example, fieldValue.substring(QUERY_PARAM_WILDCARD.length(),
              fieldValue.length() - QUERY_PARAM_WILDCARD.length()));
        } else {
          // If the query param has wildcard at start, set the matcher to 'endsWith'
          if (fieldValue.startsWith(QUERY_PARAM_WILDCARD)) {
            exampleMatcher = exampleMatcher.withMatcher(field.getName(),
                GenericPropertyMatchers.endsWith());
            // Update the field with the wildcard-removed value
            field.set(example, fieldValue.substring(QUERY_PARAM_WILDCARD.length()));
          } else if (fieldValue.endsWith(QUERY_PARAM_WILDCARD)) {
            exampleMatcher = exampleMatcher.withMatcher(field.getName(),
                GenericPropertyMatchers.startsWith());
            // Update the field with the wildcard-removed value
            field.set(example, fieldValue.substring(0,
                fieldValue.length() - QUERY_PARAM_WILDCARD.length()));
          }
        }
      } catch (IllegalAccessException e) {
        throw new RuntimeException("Failed to access example property: %s".formatted(
            field.getName()), e);
      }
    }

    return exampleMatcher;
  }
}
