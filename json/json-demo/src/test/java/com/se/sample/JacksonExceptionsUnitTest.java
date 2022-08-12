package com.se.sample;


import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import com.se.sample.model.Zoo;
import org.junit.Test;


import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

/**
 * Created by Evgeniy Skiba on 19.03.21
 */
public class JacksonExceptionsUnitTest {

    @Test(expected = JsonMappingException.class)
    public void givenAbstractClass_whenDeserializing_thenException() throws IOException {
        final String json = "{\"animal\":{\"name\":\"lacy\"}}";

        ObjectMapper mapper = new ObjectMapper();

        Object zooItem = mapper.reader()
                .forType(Zoo.class)
                .readValue(json);

        int a =0;
    }
}
