package uk.gov.laa.ccms.data.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccms.data.entity.Proceeding;
import uk.gov.laa.ccms.data.model.ProceedingDetail;

@ExtendWith(MockitoExtension.class)
class ProceedingMapperImplTest {

    ProceedingMapperImpl mapper = new ProceedingMapperImpl();

    // Tests
    @Test
    void proceedingToProceedingDetail() {
        Proceeding proceeding = buildProceeding();

        ProceedingDetail result = mapper.toProceedingDetail(proceeding);

        assertNotNull(result);
        assertEquals(proceeding.getCode(), result.getCode());
        assertEquals(proceeding.getName(), result.getName());
        assertEquals(proceeding.getDescription(), result.getDescription());
        assertEquals(proceeding.getEnabled(), result.getEnabled());
        assertEquals(proceeding.getLarScope(), result.getLarScope());
        assertEquals(proceeding.getMatterType(), result.getMatterType());
        assertEquals(proceeding.getStageEndLov(), result.getStageEndLov());
        assertEquals(proceeding.getAmendmentOnly(), result.getAmendmentOnly());
        assertEquals(proceeding.getOutcomeResultLov(), result.getOutcomeResultLov());
        assertEquals(proceeding.getOrderTypeRequired(), result.getOrderTypeRequired());
        assertEquals(proceeding.getCategoryOfLawCode(), result.getCategoryOfLawCode());
    }

    // Helper methods to create objects
    private Proceeding buildProceeding() {
        Proceeding proceeding = new Proceeding();
        proceeding.setCode("thecode");
        proceeding.setName("aname");
        proceeding.setDescription("adesc");
        proceeding.setStageEndLov("stageend");
        proceeding.setOrderTypeRequired(true);
        proceeding.setOutcomeResultLov("result");
        proceeding.setLarScope("thelarscope");
        proceeding.setCategoryOfLawCode("CAT1");
        proceeding.setMatterType("MAT1");
        proceeding.setEnabled(true);
        proceeding.setAmendmentOnly(Boolean.TRUE);
        return proceeding;
    }

}