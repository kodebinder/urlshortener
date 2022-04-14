package com.company.configuration;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.internal.InheritingConfiguration;
import org.modelmapper.internal.TypeResolvingList;
import org.modelmapper.spi.NameTokenizer;
import org.modelmapper.spi.ValueReader;
import org.modelmapper.spi.ValueWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {UrlConfigurationImpl.class})
@ExtendWith(SpringExtension.class)
class UrlConfigurationImplTest {

    @Autowired
    private UrlConfigurationImpl urlConfigurationImpl;

    @Test
    void testGetProperties() {
        assertEquals(2, this.urlConfigurationImpl.getProperties().size());
    }

    @Test
    void testModelMapper() {
        ModelMapper actualModelMapperResult = (new UrlConfigurationImpl()).modelMapper();
        assertTrue(actualModelMapperResult.getTypeMaps().isEmpty());
        Configuration configuration = actualModelMapperResult.getConfiguration();
        assertEquals(14, configuration.getConverters().size());
        assertNull(configuration.getPropertyCondition());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getFieldAccessLevel());
        NameTokenizer expectedSourceNameTokenizer = configuration.getDestinationNameTokenizer();
        assertSame(expectedSourceNameTokenizer, configuration.getSourceNameTokenizer());
        List<ValueWriter<?>> valueWriters = configuration.getValueWriters();
        assertTrue(valueWriters instanceof TypeResolvingList);
        List<ValueReader<?>> valueReaders = configuration.getValueReaders();
        assertTrue(valueReaders instanceof TypeResolvingList);
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getMethodAccessLevel());
        assertSame(valueReaders, ((InheritingConfiguration) configuration).valueAccessStore.getValueReaders());
        assertSame(valueWriters, ((InheritingConfiguration) configuration).valueMutateStore.getValueWriters());
    }

    @Test
    void testJsonMapper() {
        ObjectMapper actualJsonMapperResult = (new UrlConfigurationImpl()).jsonMapper();
        assertTrue(actualJsonMapperResult
                .getPolymorphicTypeValidator() instanceof com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator);
        VisibilityChecker<?> visibilityChecker = actualJsonMapperResult.getVisibilityChecker();
        assertTrue(visibilityChecker instanceof VisibilityChecker.Std);
        assertNull(actualJsonMapperResult.getPropertyNamingStrategy());
        assertTrue(actualJsonMapperResult
                .getDeserializationContext() instanceof com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.Impl);
        assertTrue(actualJsonMapperResult
                .getSerializerFactory() instanceof com.fasterxml.jackson.databind.ser.BeanSerializerFactory);
        assertTrue(actualJsonMapperResult
                .getSerializerProvider() instanceof com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.Impl);
        assertTrue(actualJsonMapperResult
                .getSerializerProviderInstance() instanceof com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.Impl);
        assertTrue(actualJsonMapperResult
                .getSubtypeResolver() instanceof com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver);
        DeserializationConfig deserializationConfig = actualJsonMapperResult.getDeserializationConfig();
        assertNull(deserializationConfig.getActiveView());
        assertEquals(237020304, deserializationConfig.getDeserializationFeatures());
        assertTrue(deserializationConfig
                .getClassIntrospector() instanceof com.fasterxml.jackson.databind.introspect.BasicClassIntrospector);
        assertSame(actualJsonMapperResult.getDateFormat(), deserializationConfig.getDateFormat());
        assertNull(deserializationConfig.getDefaultMergeable());
        assertSame(visibilityChecker, deserializationConfig.getDefaultVisibilityChecker());
        assertNull(deserializationConfig.getFullRootName());
        assertNull(deserializationConfig.getHandlerInstantiator());
        assertTrue(deserializationConfig.getAttributes() instanceof ContextAttributes.Impl);
        assertTrue(deserializationConfig
                .getAnnotationIntrospector() instanceof com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector);
    }
}

