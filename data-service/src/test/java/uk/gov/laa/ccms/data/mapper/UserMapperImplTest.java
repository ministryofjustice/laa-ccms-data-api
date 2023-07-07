package uk.gov.laa.ccms.data.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccms.data.entity.Firm;
import uk.gov.laa.ccms.data.entity.Office;
import uk.gov.laa.ccms.data.entity.Provider;
import uk.gov.laa.ccms.data.entity.User;
import uk.gov.laa.ccms.data.model.ProviderDetails;
import uk.gov.laa.ccms.data.model.OfficeDetails;
import uk.gov.laa.ccms.data.model.UserDetails;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserMapperImplTest {

    UserMapperImpl mapper = new UserMapperImpl();

    // Helper methods to create objects

    private User createUser(String suffix) {
        User user = new User();
        user.setUserId(1);
        user.setLoginId("loginId" + suffix);
        user.setUsername("username" + suffix);
        user.setUserType("userType" + suffix);
        user.setProvider(new Provider());
        user.setFirms(new ArrayList<>());
        user.setFunctions(new ArrayList<>());
        return user;
    }

    private UserDetails createUserDetails(User user) {
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(user.getUserId());
        userDetails.setLoginId(user.getLoginId());
        userDetails.setUsername(user.getUsername());
        userDetails.setUserType(user.getUserType());
        userDetails.setProvider(mapper.providerToProviderDetails(user.getProvider()));
        userDetails.setFirms(mapper.firmListToProviderDetailsList(user.getFirms()));
        userDetails.setFunctions(new ArrayList<>(user.getFunctions()));
        return userDetails;
    }

    // Tests

    @Test
    void toUserDetails() {
        User user = createUser("");
        UserDetails expectedDetails = createUserDetails(user);
        UserDetails actualDetails = mapper.toUserDetails(user);
        assertEquals(expectedDetails, actualDetails);
    }

    @Test
    void toProviderDetails() {
        Firm firm = new Firm();
        firm.setId(1);
        firm.setName("name");

        ProviderDetails expectedDetails = new ProviderDetails();
        expectedDetails.setId(firm.getId());
        expectedDetails.setName(firm.getName());

        ProviderDetails actualDetails = mapper.toProviderDetails(firm);

        assertEquals(expectedDetails, actualDetails);
    }

    @Test
    void officeToOfficeDetails() {
        Office office = new Office();
        office.setId(1);
        office.setName("name");

        OfficeDetails expectedDetails = new OfficeDetails();
        expectedDetails.setId(office.getId());
        expectedDetails.setName(office.getName());

        OfficeDetails actualDetails = mapper.officeToOfficeDetails(office);

        assertEquals(expectedDetails, actualDetails);
    }

    @Test
    void officeListToOfficeDetailsList() {
        List<Office> offices = new ArrayList<>();
        offices.add(new Office());

        List<OfficeDetails> expectedDetails = new ArrayList<>();
        expectedDetails.add(mapper.officeToOfficeDetails(offices.get(0)));

        List<OfficeDetails> actualDetails = mapper.officeListToOfficeDetailsList(offices);

        assertEquals(expectedDetails, actualDetails);
    }

    @Test
    void providerToProviderDetails() {
        Provider provider = new Provider();
        provider.setId(1);
        provider.setName("name");
        provider.setOffices(new ArrayList<>());

        ProviderDetails expectedDetails = new ProviderDetails();
        expectedDetails.setId(provider.getId());
        expectedDetails.setName(provider.getName());
        expectedDetails.setOffices(mapper.officeListToOfficeDetailsList(provider.getOffices()));

        ProviderDetails actualDetails = mapper.providerToProviderDetails(provider);

        assertEquals(expectedDetails, actualDetails);
    }

    @Test
    void firmListToProviderDetailsList() {
        List<Firm> firms = new ArrayList<>();
        firms.add(new Firm());

        List<ProviderDetails> expectedDetails = new ArrayList<>();
        expectedDetails.add(mapper.toProviderDetails(firms.get(0)));

        List<ProviderDetails> actualDetails = mapper.firmListToProviderDetailsList(firms);

        assertEquals(expectedDetails, actualDetails);
    }
}