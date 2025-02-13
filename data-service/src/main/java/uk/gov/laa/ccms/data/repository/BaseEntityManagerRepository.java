package uk.gov.laa.ccms.data.repository;

import jakarta.persistence.EntityManager;
import java.util.StringJoiner;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public abstract class BaseEntityManagerRepository {

  protected final EntityManager entityManager;

  protected static boolean stringNotEmpty(String value) {
    return value != null && !value.isEmpty();
  }

  protected static String getSortSql(Pageable pageable) {
    if (pageable.getSort().isEmpty()) {
      return " ";
    }

    StringJoiner sortJoiner = new StringJoiner(", ", " ORDER BY ", " ");
    pageable.getSort().forEach(order ->
        sortJoiner.add(order.getProperty() + " " + order.getDirection().name()));
    return sortJoiner.toString();
  }

}
