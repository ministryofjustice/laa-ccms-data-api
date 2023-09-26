package uk.gov.laa.ccms.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import uk.gov.laa.ccms.data.entity.ScopeLimitation;
import uk.gov.laa.ccms.data.entity.ScopeLimitationId;
import uk.gov.laa.ccms.data.mapper.ScopeLimitationMapper;
import uk.gov.laa.ccms.data.model.ScopeLimitationDetail;
import uk.gov.laa.ccms.data.model.ScopeLimitationDetails;
import uk.gov.laa.ccms.data.repository.ScopeLimitationRepository;

@ExtendWith(MockitoExtension.class)
class ScopeLimitationServiceTest {

    @Mock
    private ScopeLimitationRepository scopeLimitationRepository;

    @Mock
    private ScopeLimitationMapper scopeLimitationMapper;

    @InjectMocks
    private ScopeLimitationService scopeLimitationService;

    @Test
    void getScopeLimitations_returnsData() {
        ScopeLimitationDetail scopeLimitationDetail = new ScopeLimitationDetail();
        scopeLimitationDetail.setScopeLimitations("scopelims");
        scopeLimitationDetail.setCategoryOfLaw("cat1");

        ScopeLimitation scopeLimitation = new ScopeLimitation();
        scopeLimitation.setId(new ScopeLimitationId());
        scopeLimitation.getId().setScopeLimitations(scopeLimitationDetail.getScopeLimitations());
        scopeLimitation.getId().setCategoryOfLawCode(scopeLimitationDetail.getCategoryOfLaw());

        Example<ScopeLimitation> example = Example.of(scopeLimitation);
        Pageable pageable = Pageable.unpaged();
        Page<ScopeLimitation> expectedPage = new PageImpl<>(
            Collections.singletonList(scopeLimitation));
        ScopeLimitationDetails expectedResponse = new ScopeLimitationDetails();

        when(scopeLimitationMapper.toScopeLimitation(scopeLimitationDetail))
            .thenReturn(scopeLimitation);
        when(scopeLimitationRepository.findAll(example, pageable)).thenReturn(expectedPage);
        when(scopeLimitationMapper.toScopeLimitationDetails(expectedPage))
            .thenReturn(expectedResponse);

        ScopeLimitationDetails actualResponse = scopeLimitationService.getScopeLimitations(
            scopeLimitationDetail, pageable);

        assertEquals(expectedResponse, actualResponse);
    }
}
