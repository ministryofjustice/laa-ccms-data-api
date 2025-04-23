package uk.gov.laa.ccms.data.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import uk.gov.laa.ccms.data.entity.Firm;
import uk.gov.laa.ccms.data.entity.Office;
import uk.gov.laa.ccms.data.entity.Provider;
import uk.gov.laa.ccms.data.entity.ProviderContact;
import uk.gov.laa.ccms.data.entity.User;
import uk.gov.laa.ccms.data.model.BaseOffice;
import uk.gov.laa.ccms.data.model.BaseProvider;
import uk.gov.laa.ccms.data.model.BaseUser;
import uk.gov.laa.ccms.data.model.UserDetail;
import uk.gov.laa.ccms.data.model.UserDetails;

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

    private BaseUser createBaseUser(User user) {
        BaseUser baseUser = new BaseUser();
        baseUser.setUserId(user.getUserId());
        baseUser.setLoginId(user.getLoginId());
        baseUser.setUsername(user.getUsername());
        baseUser.setUserType(user.getUserType());
        return baseUser;
    }

    private UserDetail createUserDetail(User user) {
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(user.getUserId());
        userDetail.setLoginId(user.getLoginId());
        userDetail.setUsername(user.getUsername());
        userDetail.setUserType(user.getUserType());
        userDetail.setProvider(mapper.toBaseProvider(user.getProvider()));
        userDetail.setFirms(mapper.firmListToBaseProviderList(user.getFirms()));
        userDetail.setFunctions(new ArrayList<>(user.getFunctions()));
        return userDetail;
    }

    private Office createOffice(String suffix) {
        Office office = new Office();
        office.setId(1);
        office.setName("name" + suffix);
        return office;
    }

    private BaseOffice createBaseOffice(Office office) {
        BaseOffice baseOffice = new BaseOffice();
        baseOffice.setId(office.getId());
        baseOffice.setName(office.getName());
        return baseOffice;
    }

    private Provider createProvider() {
        Provider provider = new Provider();
        provider.setId(12345);
        provider.setOffices(List.of(new Office()));
        provider.setContactNames(List.of(new ProviderContact()));
        provider.setName("provider");
        return provider;
    }

    private BaseProvider createBaseProvider(Provider provider) {
        BaseProvider baseProvider = new BaseProvider();
        baseProvider.setId(provider.getId());
        baseProvider.setName(provider.getName());
        baseProvider.setOffices(mapper.officeListToBaseOfficeList(provider.getOffices()));
        return baseProvider;
    }

    private BaseProvider createBaseProvider(Firm firm) {
        BaseProvider baseProvider = new BaseProvider();
        baseProvider.setId(firm.getId());
        baseProvider.setName(firm.getName());
        baseProvider.setIsPrimary(firm.getIsPrimary());
        return baseProvider;
    }

    private Firm createFirm(Boolean isPrimary) {
        Firm firm = new Firm();
        firm.setId(12345);
        firm.setName("firm");
        firm.setUserEndDate(LocalDateTime.now());
        firm.setIsPrimary(isPrimary);
        return firm;
    }


    // Tests

    @Test
    void toUserDetail() {
        User user = createUser("");
        UserDetail expectedDetail = createUserDetail(user);
        UserDetail actualDetail = mapper.toUserDetail(user);
        assertEquals(expectedDetail, actualDetail);
    }

    @Test
    void toProviderDetails() {
        Firm firm = new Firm();
        firm.setId(1);
        firm.setName("name");

        BaseProvider expectedProvider = new BaseProvider();
        expectedProvider.setId(firm.getId());
        expectedProvider.setName(firm.getName());

        BaseProvider actualProvider = mapper.toBaseProvider(firm);

        assertEquals(expectedProvider, actualProvider);
    }

    @Test
    void providerToBaseProvider() {
        Provider provider = createProvider();
        BaseProvider expectedProvider = createBaseProvider(provider);
        BaseProvider actualBaseProvider = mapper.toBaseProvider(provider);
        assertEquals(expectedProvider, actualBaseProvider);
    }

    @Test
    void primaryFirmToBaseProvider() {
        Firm firm = createFirm(true);
        BaseProvider expectedProvider = createBaseProvider(firm);
        BaseProvider actualBaseProvider = mapper.toBaseProvider(firm);
        assertEquals(expectedProvider, actualBaseProvider);
    }

    @Test
    void nonPrimaryFirmToBaseProvider() {
        Firm firm = createFirm(false);
        BaseProvider expectedProvider = createBaseProvider(firm);
        BaseProvider actualBaseProvider = mapper.toBaseProvider(firm);
        assertEquals(expectedProvider, actualBaseProvider);
    }

    @Test
    void firmListToBaseProviderList() {
        List<Firm> firms = new ArrayList<>();
        firms.add(new Firm());

        List<BaseProvider> expectedDetails = new ArrayList<>();
        expectedDetails.add(mapper.toBaseProvider(firms.getFirst()));

        List<BaseProvider> actualDetails = mapper.firmListToBaseProviderList(firms);

        assertEquals(expectedDetails, actualDetails);
    }

    @Test
    void toUserDetails() {
        List<User> values = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            values.add(createUser(String.valueOf(i)));
        }

        // Create a Page object
        Page<User> page = new PageImpl<>(values);
        UserDetails expectedDetail = new UserDetails();

        expectedDetail.setTotalPages(page.getTotalPages());
        expectedDetail.setTotalElements((int) page.getTotalElements());
        expectedDetail.setNumber(page.getNumber());
        expectedDetail.setSize(page.getSize());
        List<BaseUser> expectedContent = new ArrayList<>();
        for (User value : page) {
            expectedContent.add(createBaseUser(value));
        }
        expectedDetail.setContent(expectedContent);

        UserDetails actualDetail = mapper.toUserDetails(page);

        // Assertion
        assertEquals(expectedDetail, actualDetail);
    }

    @Test
    void officeToBaseOffice() {
        Office office = createOffice("");
        BaseOffice expectedBaseOffice = createBaseOffice(office);
        BaseOffice actualBaseOffice = mapper.officeToBaseOffice(office);
        assertEquals(expectedBaseOffice, actualBaseOffice);
    }

    @Test
    void officeToBaseOffice_null() {
        BaseOffice actualBaseOffice = mapper.officeToBaseOffice(null);
        assertNull(actualBaseOffice);
    }

    @Test
    void officeListToBaseOfficeList() {
        List<Office> offices = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            offices.add(createOffice(String.valueOf(i)));
        }

        List<BaseOffice> expectedBaseOffices = new ArrayList<>();
        for (Office office : offices) {
            expectedBaseOffices.add(createBaseOffice(office));
        }

        List<BaseOffice> actualBaseOffices = mapper.officeListToBaseOfficeList(offices);

        assertEquals(expectedBaseOffices, actualBaseOffices);
    }

    @Test
    void officeListToBaseOfficeList_null() {
        List<BaseOffice> actualBaseOffices = mapper.officeListToBaseOfficeList(null);
        assertNull(actualBaseOffices);
    }
}