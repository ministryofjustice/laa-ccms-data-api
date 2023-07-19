package uk.gov.laa.ccms.data.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import uk.gov.laa.ccms.data.entity.FeeEarner;
import uk.gov.laa.ccms.data.model.ContactDetail;
import uk.gov.laa.ccms.data.model.FeeEarnerDetail;

@ExtendWith(MockitoExtension.class)
class FeeEarnerMapperImplTest {

    FeeEarnerMapper mapper = new FeeEarnerMapperImpl();

    // Helper methods to create objects
    private FeeEarner createFeeEarner(Integer id) {
        FeeEarner feeEarner = new FeeEarner();
        feeEarner.setId(id);
        feeEarner.setName("FeeEarner " + id);
        return feeEarner;
    }

    // Tests
    @Test
    void testFeeEarnerDetailMapsCorrectly() {
        List<FeeEarner> feeEarners = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            feeEarners.add(createFeeEarner(i));
        }
        Page<FeeEarner> feeEarnerPage = new PageImpl<>(feeEarners);

        FeeEarnerDetail result = mapper.toFeeEarnerDetail(feeEarnerPage);

        assertNotNull(result);
        assertEquals(feeEarners.size(), result.getSize());
        assertNotNull(result.getContent());
        assertEquals(feeEarners.size(), result.getContent().size());

        int idx = 0;
        for(ContactDetail c: result.getContent()) {
            assertEquals(idx, c.getId());
            assertEquals("FeeEarner " + idx++, c.getName());
        }
    }

}