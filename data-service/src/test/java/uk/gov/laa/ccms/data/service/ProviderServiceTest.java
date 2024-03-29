package uk.gov.laa.ccms.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccms.data.entity.FeeEarner;
import uk.gov.laa.ccms.data.entity.Office;
import uk.gov.laa.ccms.data.entity.Provider;
import uk.gov.laa.ccms.data.mapper.ProviderMapper;
import uk.gov.laa.ccms.data.model.ProviderDetail;
import uk.gov.laa.ccms.data.repository.ProviderRepository;

@ExtendWith(MockitoExtension.class)
class ProviderServiceTest {

    @Mock
    private ProviderRepository providerRepository;

    @Mock
    private ProviderMapper providerMapper;

    @InjectMocks
    private ProviderService providerService;

    @Test
    void getProvider_returnsProviderDetail() {
        Provider provider = buildProvider();

        ProviderDetail providerDetail = new ProviderDetail();

        when(providerRepository.findById(provider.getId())).thenReturn(Optional.of(provider));
        when(providerMapper.toProviderDetail(provider)).thenReturn(providerDetail);

        Optional<ProviderDetail> result = providerService.getProvider(provider.getId());

        verify(providerMapper).toProviderDetail(provider);

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(providerDetail, result.get());
    }

    @Test
    void getProvider_handlesNotFound() {
        Provider provider = new Provider();
        provider.setId(123);

        when(providerRepository.findById(provider.getId())).thenReturn(Optional.empty());

        Optional<ProviderDetail> result = providerService.getProvider(provider.getId());

        verifyNoInteractions(providerMapper);

        assertNotNull(result);
        assertFalse(result.isPresent());
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