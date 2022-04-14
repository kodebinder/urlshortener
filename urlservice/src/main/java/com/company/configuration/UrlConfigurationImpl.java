package com.company.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author pushkar.chauhan@wissen.com
 */
@Configuration
@Slf4j
public class UrlConfigurationImpl implements UrlConfiguration {

    @Override
    public Properties getProperties() {
        final Properties properties = new Properties();
        String RESOURCE_NAME = "custom.url.properties";
        loadProperties(properties, RESOURCE_NAME);
        return properties;
    }

    private void loadProperties(final Properties properties, final String resourceName) {
        try (final InputStream inputStream = UrlConfigurationImpl.class
                .getClassLoader()
                .getResourceAsStream(resourceName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            final String message = String.format("Error loading properties file at %s", resourceName);
            log.error(message);
            throw new IllegalArgumentException(message, e);
        }
    }

    /**
     * Converts DTO to Entity and Entity to DTO
     *
     * @return ModelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Converts Object to Json and Json to Object
     *
     * @return ObjectMapper
     */
    @Bean
    public ObjectMapper jsonMapper() {
        return new ObjectMapper();
    }
}