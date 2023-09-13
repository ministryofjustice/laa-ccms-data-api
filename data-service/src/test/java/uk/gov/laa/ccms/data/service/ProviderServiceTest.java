package uk.gov.laa.ccms.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
        Provider provider = new Provider();
        provider.setId(123);

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
}