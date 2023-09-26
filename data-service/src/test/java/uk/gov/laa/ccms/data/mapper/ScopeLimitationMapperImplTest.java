package uk.gov.laa.ccms.data.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import uk.gov.laa.ccms.data.entity.ScopeLimitation;
import uk.gov.laa.ccms.data.entity.ScopeLimitationId;
import uk.gov.laa.ccms.data.model.ScopeLimitationDetail;
import uk.gov.laa.ccms.data.model.ScopeLimitationDetails;

@ExtendWith(MockitoExtension.class)
class ScopeLimitationMapperImplTest {

    ScopeLimitationMapperImpl mapper = new ScopeLimitationMapperImpl();

    // Helper methods to create objects
    private ScopeLimitation createScopeLimitation(String suffix) {
        ScopeLimitation scopeLimitation = new ScopeLimitation();
        scopeLimitation.setId(new ScopeLimitationId());
        scopeLimitation.getId().setScopeLimitations("scopelimits" + suffix);
        scopeLimitation.getId().setCategoryOfLawCode("categoryoflaw" + suffix);
        scopeLimitation.getId().setMatterType("mattertype" + suffix);
        scopeLimitation.getId().setProceedingCode("proccode" + suffix);
        scopeLimitation.getId().setLevelOfServiceCode("levelofservice" + suffix);
        scopeLimitation.setDescription("description" + suffix);
        scopeLimitation.setCostLimitation(BigDecimal.TEN);
        scopeLimitation.setEmergencyCostLimitation(BigDecimal.TEN);
        scopeLimitation.setScopeDefault(Boolean.TRUE);
        scopeLimitation.setEmergencyScopeDefault(Boolean.TRUE);
        scopeLimitation.setStage(Integer.valueOf(suffix));
        scopeLimitation.setEmergency(Boolean.TRUE);
        scopeLimitation.setDefaultCode(Boolean.TRUE);
        return scopeLimitation;
    }

    private ScopeLimitationDetail createScopeLimitationDetail(String suffix) {
        return new ScopeLimitationDetail()
            .scopeLimitations("scopelimits" + suffix)
            .categoryOfLaw("categoryoflaw" + suffix)
            .matterType("mattertype" + suffix)
            .proceedingCode("proccode" + suffix)
            .levelOfService("levelofservice" + suffix)
            .description("description" + suffix)
            .costLimitation(BigDecimal.TEN)
            .emergencyCostLimitation(BigDecimal.TEN)
            .scopeDefault(Boolean.TRUE)
            .emergencyScopeDefault(Boolean.TRUE)
            .stage(Integer.valueOf(suffix))
            .emergency(Boolean.TRUE)
            .defaultCode(Boolean.TRUE);
    }

    // Tests
    @Test
    void testToScopeLimitationDetail() {
        List<ScopeLimitation> scopeLimitations = new ArrayList<>();
        List<ScopeLimitationDetail> expectedContent = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            scopeLimitations.add(createScopeLimitation(String.valueOf(i)));
            expectedContent.add(createScopeLimitationDetail(String.valueOf(i)));
        }

        Page<ScopeLimitation> scopeLimitationPage = new PageImpl<>(scopeLimitations);


        ScopeLimitationDetails expected = new ScopeLimitationDetails();
        expected.setTotalPages(scopeLimitationPage.getTotalPages());
        expected.setTotalElements((int) scopeLimitationPage.getTotalElements());
        expected.setNumber(scopeLimitationPage.getNumber());
        expected.setSize(scopeLimitationPage.getSize());
        expected.setContent(expectedContent);

        ScopeLimitationDetails actual = mapper.toScopeLimitationDetails(scopeLimitationPage);

        assertEquals(expected, actual);
    }

    @Test
    void testToScopeLimitation() {
        ScopeLimitationDetail scopeLimitationDetail = createScopeLimitationDetail("1");
        ScopeLimitation expected = createScopeLimitation("1");


        ScopeLimitation actual = mapper.toScopeLimitation(scopeLimitationDetail);

        assertEquals(expected, actual);
    }
}