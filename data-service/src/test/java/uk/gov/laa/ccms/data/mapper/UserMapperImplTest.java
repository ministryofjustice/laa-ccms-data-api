package uk.gov.laa.ccms.data.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.laa.ccms.data.entity.Firm;
import uk.gov.laa.ccms.data.entity.Provider;
import uk.gov.laa.ccms.data.entity.User;
import uk.gov.laa.ccms.data.model.BaseProvider;
import uk.gov.laa.ccms.data.model.UserDetail;

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
        userDetail.setProvider(mapper.providerToBaseProvider(user.getProvider()));
        userDetail.setFirms(mapper.firmListToBaseProviderList(user.getFirms()));
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

        BaseProvider expectedProvider = new BaseProvider();
        expectedProvider.setId(firm.getId());
        expectedProvider.setName(firm.getName());

        BaseProvider actualProvider = mapper.toBaseProvider(firm);

        assertEquals(expectedProvider, actualProvider);
    }

    @Test
    void firmListToBaseProviderList() {
        List<Firm> firms = new ArrayList<>();
        firms.add(new Firm());

        List<BaseProvider> expectedDetails = new ArrayList<>();
        expectedDetails.add(mapper.toBaseProvider(firms.get(0)));

        List<BaseProvider> actualDetails = mapper.firmListToBaseProviderList(firms);

        assertEquals(expectedDetails, actualDetails);
    }
}