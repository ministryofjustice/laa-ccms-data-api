package uk.gov.laa.ccms.data.repository;

import jakarta.persistence.EntityManager;
import java.util.StringJoiner;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

/**
 * {@link BaseEntityManagerRepository} provides a foundational repository setup for working
 * with {@link EntityManager} in the persistence layer of applications. This abstract class
 * can be extended by specific repository implementations that require customized
 * database queries using the EntityManager API. It includes utility methods to
 * facilitate common operations such as query sanitization and sorting SQL fragments.
 *
 * <p>This class is designed to handle use cases where repositories require direct
 * interaction with the database using JPQL or native SQL queries. It supports
 * reusable methods for ensuring query safety and building sort clauses.</p>
 *
 * @see EntityManager
 *
 * @author Jamie Briggs
 */
@RequiredArgsConstructor
public abstract class BaseEntityManagerRepository {

  protected final EntityManager entityManager;

  protected static boolean stringNotEmpty(String value) {
    return value != null && !value.isEmpty();
  }

  /**
   * Generates an SQL fragment for sorting based on the given {@link Pageable} object.
   * Constructs an "ORDER BY" clause from the sorting information provided in the pageable.
   * If no sorting is specified in the pageable, an empty string is returned.
   *
   * @param pageable the {@link Pageable} object containing sorting information
   * @return a string representing the "ORDER BY" SQL clause or an empty string if no
   *     sorting is specified
   */
  protected static String getSortSql(Pageable pageable) {
    if (pageable.getSort().isEmpty()) {
      return " ";
    }

    StringJoiner sortJoiner = new StringJoiner(", ", " ORDER BY ", " ");
    pageable.getSort().forEach(order ->
        sortJoiner.add(order.getProperty() + " " + order.getDirection().name()));
    return sortJoiner.toString();
  }

  /**
   * Ensures that a string is query safe. For example, if the string "O'Neil" is entered, it
   *     will get transformed into "O''Neil" to work correctly in a SQL statement.
   *
   * @param input the string which requires sanitizing.
   *
   * @return a sanitized string.
   */
  protected static String sanitizeForSql(String input) {
    return input.replace("'",
        "''");
  }

}
