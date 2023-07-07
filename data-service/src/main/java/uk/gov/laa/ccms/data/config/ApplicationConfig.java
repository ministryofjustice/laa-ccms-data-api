package uk.gov.laa.ccms.data.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.laa.ccms.data.mapper.CommonLookupValueMapper;
import uk.gov.laa.ccms.data.mapper.UserMapper;

@Configuration
public class ApplicationConfig {

    @Bean
    public UserMapper userMapper() {
        return Mappers.getMapper(UserMapper.class);
    }

    @Bean
    public CommonLookupValueMapper commonLookupValueMapper() {
        return Mappers.getMapper(CommonLookupValueMapper.class);
    }
}
