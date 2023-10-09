package uk.gov.laa.ccms.data.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import uk.gov.laa.ccms.data.entity.PriorAuthority;
import uk.gov.laa.ccms.data.entity.PriorAuthorityType;
import uk.gov.laa.ccms.data.model.PriorAuthorityDetail;
import uk.gov.laa.ccms.data.model.PriorAuthorityTypeDetail;
import uk.gov.laa.ccms.data.model.PriorAuthorityTypeDetails;

@ExtendWith(MockitoExtension.class)
class PriorAuthorityMapperImplTest {

    PriorAuthorityMapperImpl mapper = new PriorAuthorityMapperImpl();
    
    // Tests
    @Test
    void toPriorAuthorityTypeDetails() {
        List<PriorAuthorityType> priorAuthorityTypes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            priorAuthorityTypes.add(createPriorAuthorityType(String.valueOf(i)));
        }

        Page<PriorAuthorityType> priorAuthorityTypePage = new PageImpl<>(priorAuthorityTypes);
        PriorAuthorityTypeDetails expected =
            createPriorAuthorityTypeDetails(priorAuthorityTypePage);

        PriorAuthorityTypeDetails actual =
            mapper.toPriorAuthorityTypeDetails(priorAuthorityTypePage);

        assertEquals(expected, actual);
    }


    // Helper methods to create objects
    private PriorAuthorityType createPriorAuthorityType(String suffix) {
        PriorAuthorityType priorAuthorityType = new PriorAuthorityType();
        priorAuthorityType.setCode("code" + suffix);
        priorAuthorityType.setDescription("description" + suffix);
        priorAuthorityType.setValueRequired(Boolean.TRUE);
        priorAuthorityType.setPriorAuthorities(new ArrayList<>());
        priorAuthorityType.getPriorAuthorities().add(createPriorAuthority(suffix));
        return priorAuthorityType;
    }

    private PriorAuthority createPriorAuthority(String suffix) {
        PriorAuthority priorAuthority = new PriorAuthority();
        priorAuthority.setCode("code" + suffix);
        priorAuthority.setDescription("description" + suffix);
        priorAuthority.setDataType("datatype" + suffix);
        priorAuthority.setLovCode("lov" + suffix);
        return priorAuthority;
    }

    private PriorAuthorityTypeDetails createPriorAuthorityTypeDetails(
        Page<PriorAuthorityType> priorAuthorityTypes) {
        PriorAuthorityTypeDetails priorAuthorityTypeDetails = new PriorAuthorityTypeDetails()
            .number(priorAuthorityTypes.getNumber())
            .size(priorAuthorityTypes.getSize())
            .totalPages(priorAuthorityTypes.getTotalPages())
            .totalElements((int) priorAuthorityTypes.getTotalElements());

        priorAuthorityTypes.getContent().forEach(
            priorAuthorityType -> priorAuthorityTypeDetails.addContentItem(
                createPriorAuthorityTypeDetail(priorAuthorityType)));

        return priorAuthorityTypeDetails;
    }

    private PriorAuthorityTypeDetail createPriorAuthorityTypeDetail(
        PriorAuthorityType priorAuthorityType) {
        return new PriorAuthorityTypeDetail()
            .code(priorAuthorityType.getCode())
            .description(priorAuthorityType.getDescription())
            .valueRequired(priorAuthorityType.getValueRequired())
            .addPriorAuthoritiesItem(
                createPriorAuthorityDetail(priorAuthorityType.getPriorAuthorities().get(0)));
    }

    private PriorAuthorityDetail createPriorAuthorityDetail(PriorAuthority priorAuthority) {
        return new PriorAuthorityDetail()
            .code(priorAuthority.getCode())
            .description(priorAuthority.getDescription())
            .dataType(priorAuthority.getDataType())
            .lovCode(priorAuthority.getLovCode());
    }
}