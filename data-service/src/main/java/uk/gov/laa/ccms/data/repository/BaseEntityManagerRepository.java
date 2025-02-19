package uk.gov.laa.ccms.data.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.time.LocalDate;
import java.util.Map;
import java.util.StringJoiner;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

/**
 * {@link BaseEntityManagerRepository} provides a foundational repository setup for working with
 * {@link EntityManager} in the persistence layer of applications. This abstract class can be
 * extended by specific repository implementations that require customized database queries using
 * the EntityManager API. It includes utility methods to facilitate common operations such as query
 * sanitization and sorting SQL fragments.
 *
 * <p>This class is designed to handle use cases where repositories require direct
 * interaction with the database using JPQL or native SQL queries. It supports reusable methods for
 * ensuring query safety and building sort clauses.</p>
 *
 * @author Jamie Briggs
 * @see EntityManager
 */
@RequiredArgsConstructor
public abstract class BaseEntityManagerRepository {

  protected final EntityManager entityManager;

  protected static boolean stringNotEmpty(String value) {
    return value != null && !value.isEmpty();
  }

  /**
   * Adds an equality condition to the SQL WHERE clause for the specified column and value. If the
   * provided value is null, the condition is not added.
   *
   * @param whereClause the StringJoiner object used to construct the WHERE clause
   * @param queryParams the map of query parameters where the named parameters and their values are
   *                    stored
   * @param columnName  the name of the column to be included in the equality condition
   * @param value       the value to compare the column against; if null, the condition is not
   *                    added
   */
  protected static void addEqualsCondition(final StringJoiner whereClause,
      final Map<String, Object> queryParams, final String columnName, final Long value) {
    if (value != null) {
      addCondition(whereClause, queryParams, columnName, value, SqlOperand.EQUALS,
          false);
    }
  }

  /**
   * Adds an equality condition to the SQL WHERE clause for the specified column and value. If the
   * provided value is null, the condition is not added.
   *
   * @param whereClause the StringJoiner object used to construct the WHERE clause
   * @param queryParams the map of query parameters where named parameters and their corresponding
   *                    values are stored
   * @param columnName  the name of the column to be included in the equality condition
   * @param value       the integer value to compare the column against; if null, the condition is
   *                    not added
   */
  protected static void addEqualsCondition(
      final StringJoiner whereClause,
      final Map<String, Object> queryParams,
      final String columnName, final Integer value) {
    if (value != null) {
      addCondition(whereClause, queryParams, columnName, value, SqlOperand.EQUALS,
          false);
    }
  }

  /**
   * Adds an equality condition to the SQL WHERE clause for the specified column and Boolean value.
   * If the provided value is null, the condition is not added.
   *
   * @param whereClause the StringJoiner object used to construct the WHERE clause
   * @param queryParams the map of query parameters where the named parameters and their
   *                    corresponding values are stored
   * @param columnName  the name of the column to be included in the equality condition
   * @param value       the Boolean value to compare the column against; if null, the condition is
   *                    not added
   */
  protected static void addEqualsCondition(final StringJoiner whereClause,
      final Map<String, Object> queryParams,
      final String columnName, final Boolean value) {
    if (value != null) {
      // Booleans in oracle are in VARCHAR format
      addCondition(whereClause, queryParams, columnName, value.toString(), SqlOperand.EQUALS,
          true);
    }
  }


  /**
   * Adds a condition to the SQL WHERE clause with the specified parameters. If the provided value
   * is null or empty, the condition is not added.
   *
   * @param whereClause     the StringJoiner object used to construct the WHERE clause
   * @param queryParams     the map of query parameters where named parameters and their values are
   *                        stored
   * @param columnName      the name of the column to be included in the condition
   * @param operand         the SQL WHERE operand (e.g., =, LIKE, >)
   * @param value           the value to compare the column against; can be null to skip adding the
   *                        condition
   * @param caseInsensitive whether the condition should be case-insensitive
   */
  protected static void addCondition(final StringJoiner whereClause,
      final Map<String, Object> queryParams, final String columnName,
      final SqlOperand operand, final String value,
      final boolean caseInsensitive) {
    if (value != null && !value.isEmpty()) {
      addCondition(whereClause, queryParams, columnName, value
              .replace("'", "''"), operand,
          caseInsensitive);
    }
  }

  /**
   * Adds a condition to the SQL WHERE clause for the specified column, operand, and LocalDate
   * value. If the provided value is null, the condition is not included.
   *
   * @param whereClause the StringJoiner object used to construct the SQL WHERE clause
   * @param queryParams a map of query parameters where named parameters and their values are
   *                    stored
   * @param columnName  the name of the column to include in the condition
   * @param operand     the SQL operand (e.g., =, LIKE, >) to use in the condition
   * @param value       the LocalDate value to compare the column against; if null, the condition
   *                    is not added
   */
  protected static void addCondition(final StringJoiner whereClause,
      final Map<String, Object> queryParams,
      final String columnName, final SqlOperand operand, final LocalDate value) {
    if (value != null) {
      addCondition(whereClause, queryParams, columnName, value, operand,
          false);
    }
  }

  private static void addCondition(StringJoiner whereClause, Map<String, Object> queryParams,
      final String columnName, final Object value, final SqlOperand operand,
      final boolean caseInsensitive) {

    StringBuilder condition = new StringBuilder();

    // Unique param name
    String paramName =
        columnName.replaceAll("[^a-zA-Z0-9]", "") + queryParams.size();
    String conditionColumnName = columnName;
    // Make param uppercase
    if (caseInsensitive) {
      conditionColumnName = "UPPER(" + conditionColumnName + ")";
    }

    condition.append(conditionColumnName)
        .append(" ").append(operand.sql)
        .append(" :").append(paramName);

    // Put value in query params
    if (SqlOperand.LIKE.equals(operand)) {
      String paramValue = "%" + value.toString() + "%";
      queryParams.put(paramName, caseInsensitive ? paramValue.toUpperCase() : paramValue);
    } else {
      queryParams.put(paramName, caseInsensitive ? value.toString().toUpperCase() : value);
    }

    whereClause.add(condition.toString());
  }

  /**
   * Sets the query parameters for the given {@link Query} object. Iterates through the provided map
   * of query parameters and assigns each parameter to the query.
   *
   * @param query       the {@link Query} object to which parameters will be assigned
   * @param queryParams a map containing parameter names as keys and their corresponding values as
   *                    values
   */
  protected static void setQueryParameters(Query query, Map<String, Object> queryParams) {
    for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
      query.setParameter(entry.getKey(), entry.getValue());
    }
  }

  /**
   * Generates an SQL fragment for sorting based on the given {@link Pageable} object. Constructs an
   * "ORDER BY" clause from the sorting information provided in the pageable. If no sorting is
   * specified in the pageable, an empty string is returned.
   *
   * @param pageable the {@link Pageable} object containing sorting information
   * @return a string representing the "ORDER BY" SQL clause or an empty string if no sorting is
   *     specified
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
   * The SqlOperand enum represents various SQL operands that can be used in WHERE clauses. Each
   * enum constant is associated with its corresponding SQL representation.
   *
   * @author Jamie Briggs
   */
  @Getter
  public enum SqlOperand {
    EQUALS("="),
    NOT_EQUALS("!="),
    LIKE("LIKE"),
    GTE(">="),
    LTE("<="),
    ;

    private final String sql;

    SqlOperand(String sql) {
      this.sql = sql;
    }

  }
}
