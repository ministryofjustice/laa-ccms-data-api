package uk.gov.laa.ccms.data.repository;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlMergeMode;
import uk.gov.laa.ccms.data.entity.NotificationInfo;

@SpringBootTest
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/sql/get_notif_info_create_schema.sql")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/sql/get_notif_info_drop_schema.sql")
@DisplayName("Notification Repository Integration Tests")
public class NotificationRepositoryIntegrationTest {

  @Autowired
  private NotificationRepository repository;

  private NotificationInfo notification;

  @BeforeEach
  void setup(){
    notification = NotificationInfo.builder().notificationId(1L)
        .userId("test_user")
        .userLoginId("test_login")
        .providerFirmId(10L)
        .dateAssigned(LocalDate.of(2025, 1, 1))
        .subject("Subject")
        .dueDate(LocalDate.of(2027, 1, 1))
        .assignedTo("JBriggs")
        .status("open")
        .lscCaseRefReference("1001")
        .providerCaseReference("First Case Reference")
        .clientName("Jamie Briggs")
        .feeEarner("Fee")
        .personLastName("Briggs")
        .feeEarnerPartyId(3001L)
        .actionNotificationInd("N")
        .isOpen(true)
        .build();
  }
}
