package uk.gov.laa.ccms.data.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccms.data.entity.Firm;
import uk.gov.laa.ccms.data.entity.Office;
import uk.gov.laa.ccms.data.entity.Provider;
import uk.gov.laa.ccms.data.entity.User;
import uk.gov.laa.ccms.data.model.ProviderDetail;
import uk.gov.laa.ccms.data.model.OfficeDetail;
import uk.gov.laa.ccms.data.model.UserDetail;

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

    private UserDetail createUserDetails(User user) {
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(user.getUserId());
        userDetail.setLoginId(user.getLoginId());
        userDetail.setUsername(user.getUsername());
        userDetail.setUserType(user.getUserType());
        userDetail.setProvider(mapper.providerToProviderDetail(user.getProvider()));
        userDetail.setFirms(mapper.firmListToProviderDetailList(user.getFirms()));
        userDetail.setFunctions(new ArrayList<>(user.getFunctions()));
        return userDetail;
    }

    // Tests

    @Test
    void toUserDetails() {
        User user = createUser("");
        UserDetail expectedDetail = createUserDetails(user);
        UserDetail actualDetail = mapper.toUserDetail(user);
        assertEquals(expectedDetail, actualDetail);
    }

    @Test
    void toProviderDetails() {
        Firm firm = new Firm();
        firm.setId(1);
        firm.setName("name");

        ProviderDetail expectedDetail = new ProviderDetail();
        expectedDetail.setId(firm.getId());
        expectedDetail.setName(firm.getName());

        ProviderDetail actualDetail = mapper.toProviderDetail(firm);

        assertEquals(expectedDetail, actualDetail);
    }

    @Test
    void officeToOfficeDetails() {
        Office office = new Office();
        office.setId(1);
        office.setName("name");

        OfficeDetail expectedDetail = new OfficeDetail();
        expectedDetail.setId(office.getId());
        expectedDetail.setName(office.getName());

        OfficeDetail actualDetail = mapper.officeToOfficeDetail(office);

        assertEquals(expectedDetail, actualDetail);
    }

    @Test
    void officeListToOfficeDetailsList() {
        List<Office> offices = new ArrayList<>();
        offices.add(new Office());

        List<OfficeDetail> expectedDetails = new ArrayList<>();
        expectedDetails.add(mapper.officeToOfficeDetail(offices.get(0)));

        List<OfficeDetail> actualDetails = mapper.officeListToOfficeDetailList(offices);

        assertEquals(expectedDetails, actualDetails);
    }

    @Test
    void providerToProviderDetails() {
        Provider provider = new Provider();
        provider.setId(1);
        provider.setName("name");
        provider.setOffices(new ArrayList<>());

        ProviderDetail expectedDetail = new ProviderDetail();
        expectedDetail.setId(provider.getId());
        expectedDetail.setName(provider.getName());
        expectedDetail.setOffices(mapper.officeListToOfficeDetailList(provider.getOffices()));

        ProviderDetail actualDetail = mapper.providerToProviderDetail(provider);

        assertEquals(expectedDetail, actualDetail);
    }

    @Test
    void firmListToProviderDetailsList() {
        List<Firm> firms = new ArrayList<>();
        firms.add(new Firm());

        List<ProviderDetail> expectedDetails = new ArrayList<>();
        expectedDetails.add(mapper.toProviderDetail(firms.get(0)));

        List<ProviderDetail> actualDetails = mapper.firmListToProviderDetailList(firms);

        assertEquals(expectedDetails, actualDetails);
    }
}