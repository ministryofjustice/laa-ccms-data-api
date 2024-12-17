package uk.gov.laa.ccms.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import uk.gov.laa.ccms.data.entity.Notification;

@DataJpaTest
@ActiveProfiles("h2-test")
@DisplayName("Notification Repository Integration Test")
public class NotificationRepositoryIntegrationTest {
  
  @Autowired
  private NotificationRepository notificationRepository;
  
  @PersistenceContext
  private EntityManager entityManager;
  

  @BeforeEach
  void setUp() {
    // Insert test data into the in-memory database
    Notification n1 = Notification.builder().notificationId(1L).build();
    Notification n2 = Notification.builder().notificationId(2L).build();
    // Use entityManager as NotificationRepository extends ReadOnlyRepository.
    entityManager.persist(n1);
    entityManager.persist(n2);
  }

  @Test
  void tempTest(){
    Optional<Notification> all = notificationRepository.findByNotificationId(1L);

    assertEquals(true, all.isPresent());
  }
}
