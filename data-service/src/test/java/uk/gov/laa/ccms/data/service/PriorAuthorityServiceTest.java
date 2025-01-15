package uk.gov.laa.ccms.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import uk.gov.laa.ccms.data.entity.PriorAuthority;
import uk.gov.laa.ccms.data.entity.PriorAuthorityType;
import uk.gov.laa.ccms.data.mapper.PriorAuthorityMapper;
import uk.gov.laa.ccms.data.model.PriorAuthorityTypeDetails;
import uk.gov.laa.ccms.data.repository.PriorAuthorityRepository;

@ExtendWith(MockitoExtension.class)
class PriorAuthorityServiceTest {

    @Mock
    private PriorAuthorityRepository priorAuthorityRepository;

    @Mock
    private PriorAuthorityMapper priorAuthorityMapper;

    @InjectMocks
    private PriorAuthorityService priorAuthorityService;

    @Test
    void getPriorAuthorityTypes_returnsData() {
        PriorAuthorityType priorAuthorityType = buildPriorAuthorityType();

        PriorAuthorityType exampleType = new PriorAuthorityType();
        exampleType.setCode(priorAuthorityType.getCode());
        exampleType.setValueRequired(priorAuthorityType.getValueRequired());

        Example<PriorAuthorityType> example = Example.of(exampleType);
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<PriorAuthorityType> expectedPage = new PageImpl<>(
            Collections.singletonList(priorAuthorityType));
        PriorAuthorityTypeDetails expectedResponse = new PriorAuthorityTypeDetails();

        when(priorAuthorityRepository.findAll(example, pageable))
            .thenReturn(expectedPage);
        when(priorAuthorityMapper.toPriorAuthorityTypeDetails(expectedPage)).thenReturn(
            expectedResponse);

        PriorAuthorityTypeDetails actualResponse = priorAuthorityService.getPriorAuthorityTypes(
            priorAuthorityType.getCode(),
            priorAuthorityType.getValueRequired(),
            pageable);

        assertEquals(expectedResponse, actualResponse);

    }

    // Helper methods to create objects
    private PriorAuthorityType buildPriorAuthorityType() {
        PriorAuthorityType priorAuthorityType = new PriorAuthorityType();
        priorAuthorityType.setCode("code1");
        priorAuthorityType.setDescription("desc1");
        priorAuthorityType.setValueRequired(Boolean.TRUE);
        priorAuthorityType.setPriorAuthorities(new ArrayList<>());
        priorAuthorityType.getPriorAuthorities().add(buildPriorAuthority());
        return priorAuthorityType;
    }

    private PriorAuthority buildPriorAuthority() {
        PriorAuthority priorAuthority = new PriorAuthority();
        priorAuthority.setCode("priorCode1");
        priorAuthority.setDescription("priorDesc1");
        priorAuthority.setLovCode("lovCode1");
        priorAuthority.setDataType("dataType1");
        return priorAuthority;
    }
}