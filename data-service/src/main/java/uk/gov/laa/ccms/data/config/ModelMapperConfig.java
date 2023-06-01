/**
 * Configuration class for ModelMapper.
 */
package uk.gov.laa.ccms.data.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    /**
     * Creates a new instance of ModelMapper bean.
     *
     * @return the ModelMapper instance
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
