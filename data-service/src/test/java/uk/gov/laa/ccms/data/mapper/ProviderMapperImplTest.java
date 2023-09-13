package uk.gov.laa.ccms.data.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccms.data.entity.FeeEarner;
import uk.gov.laa.ccms.data.entity.Office;
import uk.gov.laa.ccms.data.entity.Provider;
import uk.gov.laa.ccms.data.model.ProviderDetail;

@ExtendWith(MockitoExtension.class)
class ProviderMapperImplTest {

    ProviderMapperImpl mapper = new ProviderMapperImpl();

    // Tests
    @Test
    void providerToProviderDetails() {
        Provider provider = buildProvider();

        ProviderDetail result = mapper.toProviderDetail(provider);

        assertNotNull(result);
        assertEquals(provider.getId(), result.getId());
        assertEquals(provider.getName(), result.getName());
        assertNotNull(result.getOffices());
        assertEquals(1, result.getOffices().size());
        assertEquals(provider.getOffices().get(0).getId(), result.getOffices().get(0).getId());
        assertEquals(provider.getOffices().get(0).getName(), result.getOffices().get(0).getName());
        assertNotNull(provider.getOffices().get(0).getFeeEarners());
        assertEquals(1, provider.getOffices().get(0).getFeeEarners().size());
        assertEquals(provider.getOffices().get(0).getFeeEarners().get(0).getId(), provider.getOffices().get(0).getFeeEarners().get(0).getId());
        assertEquals(provider.getOffices().get(0).getFeeEarners().get(0).getName(), provider.getOffices().get(0).getFeeEarners().get(0).getName());
    }

    // Helper methods to create objects
    private FeeEarner buildFeeEarner() {
        FeeEarner feeEarner = new FeeEarner();
        feeEarner.setId(10);
        feeEarner.setName("feeearnername");
        return feeEarner;
    }

    private Provider buildProvider() {
        Provider provider = new Provider();
        provider.setId(20);
        provider.setName("provname");
        provider.setOffices(new ArrayList<>());
        provider.getOffices().add(buildOffice());
        return provider;
    }

    private Office buildOffice() {
        Office office = new Office();
        office.setId(30);
        office.setName("officename");
        office.setFeeEarners(new ArrayList<>());
        office.getFeeEarners().add(buildFeeEarner());

        return office;
    }

}